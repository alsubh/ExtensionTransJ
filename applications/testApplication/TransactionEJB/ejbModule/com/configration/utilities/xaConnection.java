package com.configration.utilities;
//package com.configration;
//
//import java.net.Inet4Address;
//
//import java.util.*;
//import java.util.concurrent.Executor;
//
//import java.sql.*;
//
//import javax.sql.XAConnection;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.sql.*;
//import javax.transaction.*;
//import javax.transaction.xa.*;
//
////to guarantee the ACID properties
//import com.arjuna.ats.jdbc.logging.*;
//import com.arjuna.ats.jdbc.TransactionalDriver;
//import com.arjuna.ats.jdbc.TransactionalDriver.*;
//import com.arjuna.ats.arjuna.common.*;
//import com.arjuna.ats.jdbc.common.jdbcPropertyManager;
//import com.arjuna.ats.jdbc.logging.jdbcLogger;
//
//import com.arjuna.ats.*;
//import com.arjuna.ats.jta.*;
//import com.arjuna.ats.jta.xa.XidImple;
//import com.arjuna.ats.jta.UserTransaction.*;
//import com.arjuna.ats.jta.TransactionManager;
//import com.arjuna.ats.jta.recovery.*;
//import com.arjuna.ats.jta.xa.XAModifier;
//import com.arjuna.ats.jta.xa.RecoverableXAConnection;
//
//import com.arjuna.ats.internal.jdbc.*;
//import com.arjuna.ats.internal.jta.xa.*;
//import com.arjuna.ats.internal.jdbc.drivers.modifiers.ConnectionModifier;
//import com.arjuna.ats.internal.jdbc.drivers.modifiers.ModifierFactory;
//
//import com.microsoft.sqlserver.jdbc.*;
//
//import org.jboss.util.property.*;
//import org.jboss.util.property.PropertyManager;
//
//
//public class xaConnection {
//
//    public void loadDrivers(String dbType){    	
// 
//    	// Register the driver via the system properties variable "jdbc.drivers"
//		Properties p = System.getProperties();
//		try{
//		switch(dbType){
//			case "ORACLE":
//				p.put("jdbc.drivers", "oracle.jdbc.driver.OracleDriver");
//				break;
//			case "SQLSERVER":
//				p.put("jdbc.drivers", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
//				break;
//			case "MYSQL":
//			    p.put("jdbc.drivers", "com.mysql.jdbc.Driver"); 
//			    break;
//			case "PGSQL":
//			    p.put("jdbc.drivers", "org.postgresql.Driver"); 
//			    break;
//		}
//		System.setProperties(p);
//		}catch(Exception ex){
//			
//		}	
//    }
//
//    // Establish the connection.
//    public String getConnectionUrl(String prefix, String serverName, int portNumber,
//    		String databaseName, String username, String password) throws SQLException{
//    	
//        String connectionUrl = prefix + serverName + ":" + portNumber
//           + ";databaseName=" + databaseName + ";user=" + username + ";password=" + password;
//        return connectionUrl;
//    }
//    
//    //JNDI: Java Naming and Directory Interface
//    public void createXADataSource(Connection con, String sqlStatement){
//    	// Create the XA data source and XA ready connection.
//    	 XAResource xaRes = null;
//    	 Connection connection=null; 
//    	try{
//	        XADataSource ds = new SQLServerXADataSource(); //        
//	        /*
//	        ds.setUser(user);
//	        ds.setPassword(password);
//	        ds.setServerName(serverName);
//	        ds.setPortNumber(portNumber);
//	        ds.setDatabaseName(databaseName);
//	        */
//	        Hashtable env = new Hashtable();
//	        String initialCtx = PropertyManager.getProperty("Context.INITIAL_CONTEXT_FACTORY");
//	        env.put(Context.INITIAL_CONTEXT_FACTORY, initialCtx);
//	        InitialContext ctx = new InitialContext(env);
//	        ctx.bind("jdbc/foo", ds);
//	        
//	        Properties dbProps = new Properties();
//	        dbProps.setProperty(TransactionalDriver.userName, "tedi");
//	        dbProps.setProperty(TransactionalDriver.password, "tedi");
//	        // the driver uses its own JNDI context info, remember to set it up:
//	        //jdbcPropertyManager.propertyManager.setProperty("Context.INITIAL_CONTEXT_FACTORY", initialCtx);
//	        //jdbcPropertyManager.propertyManager.setProperty("Context.PROVIDER_URL", myUrl);
//	        TransactionalDriver arjunaJDBCDriver = new TransactionalDriver();
//	        connection = arjunaJDBCDriver.connect("jdbc:arjuna:jdbc/foo", dbProps);
//	        
//	        XAConnection xaCon = ds.getXAConnection();
//	        con = xaCon.getConnection();
//	        
//	     // Get the XAResource object and set the timeout value.
//	         xaRes = xaCon.getXAResource();
//	         xaRes.setTransactionTimeout(0);
//
//	    // Perform the XA transaction.
//	         xaRes.start(getXid(),XAResource.TMNOFLAGS);
//	         PreparedStatement pstmt = con.prepareStatement(sqlStatement);//"INSERT INTO XAMin (f1,f2) VALUES (?, ?)");
//	         //pstmt.setInt(1,1);
//	         //pstmt.setString(2,xid.toString());
//	         pstmt.executeUpdate();
//
//	         // Commit the transaction.
//	         xaRes.end(getXid(),XAResource.TMSUCCESS);
//	         xaRes.commit(getXid(),true);
//
//	         // Cleanup.
//	         con.close();
//	         xaCon.close();
//         	        
//    	}catch(Exception ex){
//    		System.out.print(ex.getMessage());
//    		ex.printStackTrace();
//    	}
//    }
//    
//    public Xid getXid(){
//    	XidImple tid= new XidImple();
//    	XID xid= tid.getXID();
//    	System.out.println("xid = " + xid.toString());
//    	return (Xid)xid;
//    }
//    
//    public void retreiveData(String selectSqlStatement){
//        // Open a new connection and read back the record.
//        Connection con=null;
//        ResultSet rs=null;
//		try {
//			con = setupConnection();
//			rs = con.createStatement().executeQuery(selectSqlStatement);
//		        while (rs.next()){
//		        	//System.out.println("Read -> xid = " + rs.getString(rs.get));
//		        }
//		   rs.close();
//		   con.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
//}