package com.client.utilities;

//import com.arjuna.ArjunaCommon.Common.*;
//import com.arjuna.JDBC2.*;
import java.io.*;
import java.util.*;
import java.sql.*;

import javax.sql.*;
import javax.transaction.xa.*;
import javax.naming.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Logger;

import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import com.arjuna.ats.arjuna.common.CoordinatorEnvironmentBean;
import com.arjuna.ats.internal.arjuna.objectstore.jdbc.JDBCImple;
import com.arjuna.ats.internal.arjuna.objectstore.jdbc.JDBCStore;
import com.arjuna.ats.internal.arjuna.objectstore.jdbc.microsoft_driver;
import com.arjuna.ats.internal.jdbc.drivers.jndi;
import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionManagerImple;
import com.arjuna.ats.internal.jta.transaction.jts.TransactionImple;
import com.arjuna.ats.internal.jta.xa.XID;
import com.arjuna.ats.internal.jts.orbspecific.ControlImple;
import com.arjuna.ats.internal.jts.orbspecific.TransactionFactoryImple;
import com.arjuna.ats.jdbc.TransactionalDriver;
import com.arjuna.ats.jdbc.common.jdbcPropertyManager;
import com.arjuna.ats.jta.common.jtaPropertyManager;
import com.arjuna.ats.jta.utils.JNDIManager;
import com.arjuna.ats.jta.xa.XidImple;
import com.arjuna.common.internal.util.propertyservice.PropertyManagerImpl;
import com.arjuna.common.util.propertyservice.PropertyManager;
import com.arjuna.common.util.propertyservice.PropertyManagerFactory;
import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import com.microsoft.sqlserver.jdbc.SQLServerXADataSource;

import org.jboss.jca.adapters.jdbc.*;
import org.omg.CosTransactions.*;
import org.omg.CORBA.WCharSeqHelper;
import org.omg.CosTransactions.HeuristicHazard;
import org.omg.CosTransactions.HeuristicMixed;
import org.omg.CosTransactions.NoTransaction;
import org.omg.CosTransactions.PropagationContext;
import org.omg.CosTransactions.SubtransactionsUnavailable;
import org.omg.CosTransactions.Unavailable;

import com.arjuna.ats.internal.jts.*;
import com.arjuna.ats.internal.jts.orbspecific.*;
import com.arjuna.ats.jta.cdi.*;
import com.arjuna.ats.jta.cdi.transactional.*;
import com.arjuna.ats.jta.cdi.TransactionContext;

public class ClientUtility {
 
    private static Context initialContext;
 
    private static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";
 
    public static Context getInitialContext() throws NamingException {
        if (initialContext == null) {
            Properties properties = new Properties();
            properties.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
            properties.put("jboss.naming.client.ejb.context", true);
            properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            properties.put(Context.PROVIDER_URL,"remote://127.0.0.1:4447");  
            properties.put(Context.SECURITY_PRINCIPAL, "ManagementRealm");
            properties.put(Context.SECURITY_CREDENTIALS, "godanas2005!");
             
            // create a context passing these properties
            initialContext = new InitialContext(properties);
        }
        return initialContext;
    }
    
    public static void loadDrivers(String dbType){    	
    	// Register the driver via the system properties variable "jdbc.drivers"
 		Properties property = System.getProperties();
		try{
		switch(dbType){
			case "ORACLE":
				property.put("jdbc.drivers", "oracle.jdbc.driver.OracleDriver");
				//sun.jdbc.odbc.JdbcOdbcDriver drv = new sun.jdbc.odbc.JdbcOdbcDriver();
				//DriverManager.registerDriver(drv);
				break;
			case "SQLSERVER":
				property.put("jdbc.drivers", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
				//SQLServerDriver drv = new SQLServerDriver();
				//DriverManager.registerDriver(drv);
				break;
			case "MYSQL":
			    property.put("jdbc.drivers", "com.mysql.jdbc.Driver"); 
			    break;
			case "PGSQL":
			    property.put("jdbc.drivers", "org.postgresql.Driver"); 
			    break;
		}
		System.setProperties(property);
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}	
    }

	private static Connection registerDriver(String username, String password,String dbName, String sqlStatement) {
		Connection conn=null;
		Context context= null;
		try
		{
			TransactionalDriver JDBCDriver = new TransactionalDriver();
			DriverManager.registerDriver(JDBCDriver);
	        
			XADataSource ds = getxaResource(username, password, dbName);
			
	        Properties dbProps = new Properties();
	        dbProps.setProperty(TransactionalDriver.userName, username);
	        dbProps.setProperty(TransactionalDriver.password, password);
	        
	        //dbProps.setProperty(TransactionalDriver.dynamicClass, "com.arjuna.ats.internal.jdbc.drivers.modifiers.sqlserver_jndi");
	        conn = JDBCDriver.connect("jdbc:arjuna:jdbc/"+dbName, dbProps);
	        XAConnection c= ds.getXAConnection();
	        WrappedConnection wc = (WrappedConnection)c;
	        com.microsoft.sqlserver.jdbc.SQLServerConnection  mc = (SQLServerConnection) wc.getUnderlyingConnection();
	        
	        manageXADataSource(ds,conn, sqlStatement);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		return conn;
	}

	private static XADataSource getxaResource(String username, String password,
			String dbName) throws NamingException {
		Context context;
		XADataSource ds = new SQLServerXADataSource();
		
		try{
//			((SQLServerDataSource) ds).setUser(username);
//			((SQLServerDataSource) ds).setPassword(password);
//			((SQLServerDataSource) ds).setServerName("localhost");
//			((SQLServerDataSource) ds).setPortNumber(1433);
//			((SQLServerDataSource) ds).setDatabaseName(dbName);
			
			context = getInitialContext();
			
			DataSource dataSource= (DataSource) context.lookup("java:/MSSQLDS/"+dbName);
			System.out.println(dataSource.getName());
			XADataSource xaDataSource = (XADataSource) context.lookup("java:/MSSQLXADS/GooPile");
			System.out.println(xaDataSource.getXAConnection());
			
//		    Hashtable env = new Hashtable();
//		    String initialCtx = PropertyManager.getProperty("Context.INITIAL_CONTEXT_FACTORY");
//		    env.put(Context.INITIAL_CONTEXT_FACTORY, initialCtx);
//		    InitialContext ctx = new InitialContext(env);
		    
			context.bind("MSSQLXADS/"+dbName, ds);
		
		} catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return ds;
	}
	
	//JNDI: Java Naming and Directory Interface
    public static void manageXADataSource(XADataSource ds, Connection con,String sqlStatement){
    	// Create the XA data source and XA ready connection.
    	 XAResource xaRes = null;
    	 TransactionManagerImple tm = new  TransactionManagerImple();
    	try{
	        XAConnection xaCon = ds.getXAConnection();
	        con = xaCon.getConnection();
	        
	     // Get the XAResource object and set the timeout value.
	         xaRes = xaCon.getXAResource();
	         xaRes.setTransactionTimeout(0);
	         tm.getTransaction().enlistResource(xaRes);
	         
	    // Perform the XA transaction.
	         xaRes.start(getXid(), XAResource.TMNOFLAGS);
	         PreparedStatement pstmt = con.prepareStatement(sqlStatement);
	         pstmt.executeUpdate();

	   // Commit the transaction.
	         xaRes.end(getXid(),XAResource.TMSUCCESS);
	         xaRes.commit(getXid(),true);

	   // Cleanup.
	         con.close();
	         xaCon.close();
	         
    	}catch(Exception ex){
    		System.out.print(ex.getMessage());
    		ex.printStackTrace();
    	}
    }
    
    public static Xid getXid(){
    	XidImple tid= new XidImple();
    	com.arjuna.ats.arjuna.xa.XID xid= tid.getXID();
    	System.out.println("xid = " + xid.toString());
    	return (Xid)xid;
    }  
    
    public static Connection setupGooPileXAConnection(String dbtype, String dbName, String userName, String password,String sqlStatement){
    	loadDrivers(dbtype);
    	Connection con= registerDriver(userName, password, dbName, sqlStatement);
    	return con;
    }
    
    public static void TransactionContext() throws SubtransactionsUnavailable
    {
        TransactionImple transaction = new TransactionImple();
        com.arjuna.ats.jta.cdi.TransactionContext transactionContext = new TransactionContext();
        
        // Begin a new top-level transaction 
        TransactionFactoryImple transactionFactory= new TransactionFactoryImple("java:jboss/T1");
        ControlImple control = (ControlImple) transactionFactory.create(1000);
        
        CoordinatorEnvironmentBean coordinator= new CoordinatorEnvironmentBean();
        CurrentImple current = new CurrentImple();
        PropagationContext pgtx;
        Terminator terminator= null;
        
        try{
            // control use to make the direct Context management and explicit transaction propagation
        
            IndirectImplicitContext(current);
            
            control = DirectExplicitContext(transactionFactory);
            
            
            control._orb().init();
            
            control._orb().shutdown(true);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
            
            
        
    }

    private static void IndirectImplicitContext(CurrentImple current)
            throws SubtransactionsUnavailable, NoTransaction, HeuristicMixed,
            HeuristicHazard 
    {
        current.begin();
        String currentTransactionName= current.get_transaction_name();
        System.out.println("Current Transaction Name is " + currentTransactionName);
        current.commit(true);
    }

    private static ControlImple DirectExplicitContext(
            TransactionFactoryImple transactionFactory) throws Unavailable,
            HeuristicHazard, HeuristicMixed 
    {
        ControlImple control;
        PropagationContext pgtx;
        Terminator terminator;
        //CREATE top-level action
        control = (ControlImple) transactionFactory.create(0);
        pgtx=control.get_coordinator().get_txcontext();
        // explicit propogation context operation
            //trans_obj.operation(arg, pgtx);
        
        //get terminator
        terminator= control.get_terminator();
        // it can be used to commit
        terminator.commit(false);
        return control;
    }
}