package com.Tester;

import java.util.*;
import java.sql.*;

import javax.naming.InitialContext;
import javax.sql.*;

import com.arjuna.ats.arjuna.common.Uid;
import com.arjuna.ats.internal.jta.resources.arjunacore.XAOnePhaseResource;
import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionManagerImple;
import com.arjuna.ats.internal.jta.transaction.arjunacore.UserTransactionImple;
import com.arjuna.ats.internal.jta.xa.XID;
import com.arjuna.ats.jta.*;
import com.arjuna.ats.jta.UserTransaction.*;
import com.arjuna.ats.jta.xa.XidImple;
import com.arjuna.ats.*;
import com.arjuna.ats.jdbc.TransactionalDriver;

import javax.transaction.*;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import com.arjuna.ats.jta.TransactionManager;
import com.arjuna.ats.jdbc.common.jdbcPropertyManager.*;
import com.arjuna.ats.jdbc.TransactionalDriver.*;
import com.client.utilities.ClientUtility;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerXADataSource;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


/**
 * Session Bean implementation class Tester
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class Tester {

    /**
     * Default constructor. 
     */
    public Tester() {
        // TODO Auto-generated constructor stub
    }
    
    public static void main (String[] args) throws SQLException
    {
    	Connection conn = null;
    	Connection conn2 = null;
    	Statement stmtx = null;  // will be a tx-statement
    	
    	XADataSource xaDS;   
    	XAConnection xaCon = null;   
    	XAResource xaRes;   
    	XidImple xid;   
    	int ret; 
    	ResultSet result=null;
    	UserTransactionImple userTransaction = new UserTransactionImple();
    	TransactionManagerImple transactionManager= new TransactionManagerImple();
    	
    	Properties dbProperties = new Properties(); 
    	try{
    		
    		Properties jdniproperties = new Properties();
    		jdniproperties =System.getProperties();
    		ClientUtility cUtil = new ClientUtility();
    		InitialContext cnx = (InitialContext) cUtil.getInitialContext();
    		
    		DriverManager.registerDriver(new com.arjuna.ats.jdbc.TransactionalDriver());
    		xaDS = getDS();   
    		System.out.println(DriverManager.getDrivers());
    		xaCon = xaDS.getXAConnection("tedi", "tedi");
    		System.out.println("XA connection is " + xaCon.getConnection());
    		xaRes = xaCon.getXAResource();   
    		System.out.println("XA Connection is "+ xaRes.getTransactionTimeout());
    		conn = xaCon.getConnection();
    		System.out.println("XA connection is " + conn+ " " + conn.getClientInfo());
    		
    		System.out.println("Starting top-level transaction.");
    		
    		userTransaction.begin();
    		//transactionManager.begin();
    		boolean enlistresult = transactionManager.getTransaction().enlistResource(xaRes);
    		stmtx = conn.createStatement();
    		xid = new XidImple(new Uid()); 
    		System.out.println(" Format ID "+xid.getFormatId() +" Global Transaction ID "+ xid.getGlobalTransactionId() + " Quailfier ID "+ xid.getBranchQualifier());
    		System.out.println("\nAdding entries to GooPile");
    		XAOnePhaseResource xaOnePhase = new XAOnePhaseResource(xaRes, xid, null);
    		xaRes.start((Xid) xid, XAResource.TMNOFLAGS);   
    			stmtx.executeUpdate("insert into Goo(name, code, type, price) values('Goo101', '101', 'A', 100)");
    		xaRes.end((Xid) xid, XAResource.TMSUCCESS);
    		ret = xaRes.prepare((Xid) xid); 
    		System.out.println("Result of prepare method "+ret);
    		if (ret == XAResource.XA_OK) {
    			xaRes.commit((Xid) xid, false);
    		}
    		result= stmtx.executeQuery("Select * from Goo");
    		while(result.next())
    		{
    			System.out.println("Column 1: "+result.getString(1));
    			System.out.println("Column 2: "+result.getString(2));
    		}
    		boolean delistresult= transactionManager.getTransaction().delistResource(xaRes, XAResource.TMSUCCESS);
    		//transactionManager.commit();
    		userTransaction.commit();
    		 
    	}catch(Exception ex){
    		System.out.println(ex.getMessage());
    		ex.printStackTrace();
    	}
    	finally{
    		stmtx.close();
    		conn.close();
    		xaCon.close();
    	}
    }
    
    public static XADataSource getDS(){
    	XADataSource ds = new SQLServerXADataSource();
    	try{
			((SQLServerDataSource) ds).setUser("tedi");
			((SQLServerDataSource) ds).setPassword("tedi");
			((SQLServerDataSource) ds).setServerName("localhost");
			((SQLServerDataSource) ds).setPortNumber(1433);
			((SQLServerDataSource) ds).setDatabaseName("GooPile");
    	} catch(Exception ex){
    		System.out.println(ex.getMessage());
    		ex.printStackTrace();
    	}
    	return ds;
    }
}
