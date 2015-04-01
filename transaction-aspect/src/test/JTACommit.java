/**
 * 
 */
package test;

/**
 * @author AnasR
 *
 */
import java.sql.*;

import javax.sql.*;

import java.util.*;

import javax.transaction.*;
import javax.transaction.xa.*;

import com.arjuna.ats.jta.xa.XidImple;
//import com.ibm.db2.jdbc.app.*;
import com.arjuna.ats.internal.arjuna.objectstore.jdbc.drivers.h2_driver;

import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.jdbc.*;
import org.h2.*;
import org.h2.jdbcx.JdbcDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Properties;

import javax.naming.*;
public class JTACommit {

    public static void main(java.lang.String[] args) {
        JTACommit test = new JTACommit();

        try {
			test.setup();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        test.run();
    }


    /**
     * Handle the previous cleanup run so that this test can recommence.
     * @throws ClassNotFoundException 
     * @throws SQLException 
     */
    public void setup() throws ClassNotFoundException, SQLException {

        Connection c = null;
        Statement s = null;
        try {
            //Class.forName("com.ibm.db2.jdbc.app.DB2Driver");
        	//c = DriverManager.getConnection("jdbc:db2:*local");
        	Class.forName("org.h2.Driver");
        	c= DriverManager.getConnection("jdbc:h2:mem:test","sa","sa");
        	Context ctx = getInitialContext();
        	DataSource ds = (DataSource)ctx.lookup("java:/ExampleDS");
            //JdbcConnectionPool cp = JdbcConnectionPool.create("jdbc:h2:~/test", "sa", "sa");
            //c = cp.getConnection();
        	
        	/*
        	 JdbcDataSource ds = new JdbcDataSource();
			 ds.setURL("jdbc:h2:˜/test");
			 ds.setUser("sa");
			 ds.setPassword("sa");
			 Context ctx = new InitialContext();
			 ctx.bind("jdbc/dsName", ds);
			 Context ctx = new InitialContext();
 			 DataSource ds = (DataSource) ctx.lookup("jdbc/dsName");
 			 Connection conn = ds.getConnection();
        	 */
            
            s = c.createStatement();

            try {
                s.executeUpdate("DROP TABLE test.JTATABLE");
            } catch (SQLException e) {
                // Ignore... does not exist
            }

            s.executeUpdate("CREATE TABLE test.JTATABLE (COL1 CHAR (50))");
            s.close();
        } catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public static Context getInitialContext() throws NamingException{
        Properties props=new Properties();
        props.setProperty("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
        props.setProperty("java.naming.factory.url.pkgs","org.jboss.naming");
        props.setProperty("java.naming.provider.url","localhost:1099");
        Context context= new InitialContext(props);
        return context;

    }

    /** 
     * This test uses JTA support to handle transactions.
     */
    public void run() {
        Connection c = null;

        try {
            Context ctx = new InitialContext();

            // Assume the data source is backed by a XADataSource.
            JdbcDataSource ds = (JdbcDataSource) ctx.lookup("jboss:/datasources/ExampleDS");

            // From the DataSource, obtain an XAConnection object that
            // contains an XAResource and a Connection object.
            XAConnection  xaConn = ds.getXAConnection();
            XAResource    xaRes  = xaConn.getXAResource();
            c = xaConn.getConnection();
            
            // For XA transactions, a transaction identifier is required.
            // An implementation of the XID interface is not included with the 
            // JDBC driver. See Transactions with JTA for a description of
            // this interface to build a class for it.                
            Xid xid = new XidImple();

            // The connection from the XAResource can be used as any other 
            // JDBC connection.
            Statement stmt = c.createStatement();

            // The XA resource must be notified before starting any 
            // transactional work.
            xaRes.start(xid, XAResource.TMNOFLAGS);

            // Standard JDBC work is performed.
            int count = 
              stmt.executeUpdate("INSERT INTO test.JTATABLE VALUES('JTA is pretty fun.')");

            // When the transaction work has completed, the XA resource must 
            // again be notified.
            xaRes.end(xid, XAResource.TMSUCCESS);

            // The transaction represented by the transaction ID is prepared
            // to be committed.
            int rc = xaRes.prepare(xid);

            // The transaction is committed through the XAResource.
            // The JDBC Connection object is not used to commit
            // the transaction when using JTA.
            xaRes.commit(xid, false);


        } catch (Exception e) {
            System.out.println("Something has gone wrong.");
            e.printStackTrace();
            
        } finally {
            try {
                if (c != null)
                    c.close();
            } catch (SQLException e) {
                System.out.println("Note:  Cleaup exception.");
                e.printStackTrace();
            }
        }
    }
}
