package TestJBossJTA;

import java.util.Hashtable;
import java.util.Properties;

import java.sql.*;
import java.util.Properties;
import javax.naming.InitialContext;
//import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;
import javax.naming.Context;

import com.arjuna.ats.jta.TransactionManager;
import com.arjuna.ats.jta.xa.XidImple;

/*
javax.transaction.UserTransaction utx;
// Use the begin method on a UserTransaction object to indicate
// the beginning of a distributed transaction.
utx.begin();

...
	// Execute some SQL with one Connection object.
	// Do not call Connection methods commit or rollback.
	...
	// Use the commit method on the UserTransaction object to
	// drive all transaction branches to commit and indicate
	// the end of the distributed transaction.

	utx.commit();
	...

	Figure 22 illustrates a program that uses JTA methods to execute a distributed transaction. This program acts as the transaction manager and a transactional application. Two connections to two different data sources do SQL work under a single distributed transaction.
	Figure 22. Example of a distributed transaction that uses the JTA
*/
	public class XASample
	{
	  javax.sql.XADataSource xaDS1;
	  javax.sql.XADataSource xaDS2;
	  javax.sql.XAConnection xaconn1;
	  javax.sql.XAConnection xaconn2;
	  javax.transaction.xa.XAResource xares1;
	  javax.transaction.xa.XAResource xares2;
	  java.sql.Connection conn1;
	  java.sql.Connection conn2;

	  public static void main (String args []) throws java.sql.SQLException
	  {
	    XASample xat = new XASample();
	    xat.runThis(args);
	  }

	  public void runThis(String[] args)
	  {
	    byte[] gtrid = new byte[] { 0x44, 0x11, 0x55, 0x66 };
	    byte[] bqual = new byte[] { 0x00, 0x22, 0x00 };
	    int rc1 = 0;
	    int rc2 = 0;

	    try
	    {

	    	Properties pro =new Properties();
	    	pro.setProperty("DatabaseURL", "jdbc:sqlserver://129.123.7.41:1433;databaseName=Userdb;user=anas;password=tedi;integratedSecurity=true");
	    	pro.setProperty("UserName", "anas");
	    	pro.setProperty("Password", "tedi");

	    	
	    	// Initialize JMS.
	    	// Create a hash table of settings required to access JMS.
	    	// Note that these settings are application server specific.
	    	Hashtable<String, String> hashTable = new Hashtable<String, String>();
	    	hashTable.put(javax.naming.InitialContext.URL_PKG_PREFIXES, "XASample");
	    	hashTable.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "com.arjuna.ats.internal.jdbc.drivers.jndi");
	    	hashTable.put(javax.naming.InitialContext.PROVIDER_URL, "jdbc:sqlserver://129.123.7.41:1433");
	    	hashTable.put(javax.naming.Context.SECURITY_PRINCIPAL, "anas");
	    	hashTable.put(javax.naming.Context.SECURITY_CREDENTIALS, "anas");
	    	
	    	// Create an InitialContext object so you can discover
	    	// various objects such as connection factories and queues.
	        javax.naming.InitialContext context = new javax.naming.InitialContext();
	    	javax.sql.DataSource d = (javax.sql.DataSource)context.lookup("java:comp/UserTransaction");

	      //javax.naming.InitialContext context = new javax.naming.InitialContext();
	      /*
	        * Note that javax.sql.XADataSource is used instead of a specific
	        * driver implementation such "com.microsoft.sqlserver.jdbc.SQLServerDriver"
	        */
	      xaDS1 = (javax.sql.XADataSource)context.lookup("Userdb");
	      xaDS2 = (javax.sql.XADataSource)context.lookup("accountdb");
	      
	      //"jdbc:sqlserver://129.123.7.41:1433;databaseName=Userdb;user=anas;password=tedi;integratedSecurity=true");
	      //"jdbc:sqlserver://129.123.7.41;databaseName=accountdb;user=anas;password=tedi;integratedSecurity=true");
	      
	      // The XADatasource contains the user ID and password.
	      // Get the XAConnection object from each XADataSource
	      xaconn1 = xaDS1.getXAConnection();
	      xaconn2 = xaDS2.getXAConnection();

	      // Get the java.sql.Connection object from each XAConnection
	      conn1 = xaconn1.getConnection();
	      conn2 = xaconn2.getConnection();

	      // Get the XAResource object from each XAConnection
	      xares1 = xaconn1.getXAResource();
	      xares2 = xaconn2.getXAResource();

	      // Create the Xid object for this distributed transaction
	      //javax.transaction.xa.Xid xid1 =new Xid(100, gtrid, bqual);
	      XidImple xid1= new XidImple();
	      
	      // Start the distributed transaction on the two connections.
	      // The two connections do NOT need to be started and ended together.
	      // They might be done in different threads, along with their SQL operations.
	      xares1.start(xid1, javax.transaction.xa.XAResource.TMNOFLAGS);
	      xares2.start(xid1, javax.transaction.xa.XAResource.TMNOFLAGS);
	/*...
	      // Do the SQL operations on connection 1.
	      // Do the SQL operations on connection 2.
	...*/
	      // Now end the distributed transaction on the two connections.
	      xares1.end(xid1, javax.transaction.xa.XAResource.TMSUCCESS);
	      xares2.end(xid1, javax.transaction.xa.XAResource.TMSUCCESS);

	      try
	      { // Now prepare both branches of the distributed transaction.
	        // Both branches must prepare successfully before changes
	        // can be committed.
	        // If the distributed transaction fails, an XAException is thrown.
	        rc1 = xares1.prepare(xid1);
	        if(rc1 == javax.transaction.xa.XAResource.XA_OK)
	        { // Prepare was successful. Prepare the second connection.
	          rc2 = xares2.prepare(xid1);
	          if(rc2 == javax.transaction.xa.XAResource.XA_OK)
	          { // Both connections prepared successfully and neither was read-only.
	            xares1.commit(xid1, false);
	            xares2.commit(xid1, false);
	          }
	          else if(rc2 == javax.transaction.xa.XAException.XA_RDONLY)
	          { // The second connection is read-only, so just commit the 
	            // first connection.
	            xares1.commit(xid1, false);
	          }
	        }
	        else if(rc1 == javax.transaction.xa.XAException.XA_RDONLY)
	        { // SQL for the first connection is read-only (such as a SELECT).
	          // The prepare committed it. Prepare the second connection.
	          rc2 = xares2.prepare(xid1);
	          if(rc2 == javax.transaction.xa.XAResource.XA_OK)
	          { // The first connection is read-only but the second is not.
	            // Commit the second connection.
	            xares2.commit(xid1, false);
	          }
	          else if(rc2 == javax.transaction.xa.XAException.XA_RDONLY)
	          { // Both connections are read-only, and both already committed,
	            // so there is nothing more to do.
	          }
	        }
	      }

	      catch (javax.transaction.xa.XAException xae)
	      { // Distributed transaction failed, so roll it back.
	        // Report XAException on prepare/commit.
	        System.out.println("Distributed transaction prepare/commit failed. " +
	                            "Rolling it back.");
	        System.out.println("XAException error code = " + xae.errorCode);
	        System.out.println("XAException message = " + xae.getMessage());
	        xae.printStackTrace();
	        try
	        {
	          xares1.rollback(xid1);
	        }
	        catch (javax.transaction.xa.XAException xae1)
	        { // Report failure of rollback.
	          System.out.println("distributed Transaction rollback xares1 failed");
	          System.out.println("XAException error code = " + xae1.errorCode);
	          System.out.println("XAException message = " + xae1.getMessage());
	        }
	        try
	        {
	          xares2.rollback(xid1);
	        }
	        catch (javax.transaction.xa.XAException xae2)
	        { // Report failure of rollback.
	          System.out.println("distributed Transaction rollback xares2 failed");
	          System.out.println("XAException error code = " + xae2.errorCode);
	          System.out.println("XAException message = " + xae2.getMessage());
	        }
	      }

	      try
	      {
	        conn1.close();
	        xaconn1.close();
	      }
	      catch (Exception e)
	      {
	        System.out.println("Failed to close connection 1: " + e.toString());
	        e.printStackTrace();
	      }
	      try
	      {
	        conn2.close();
	        xaconn2.close();
	      }
	      catch (Exception e)
	      {
	        System.out.println("Failed to close connection 2: " + e.toString());
	        e.printStackTrace();
	      }
	    }

	    catch (java.sql.SQLException sqe)
	    {
	      System.out.println("SQLException caught: " + sqe.getMessage());
	      sqe.printStackTrace();
	    }
	    catch (javax.transaction.xa.XAException xae)
	    {
	      System.out.println("XA error is " + xae.getMessage());
	      xae.printStackTrace();
	    }
	    catch (javax.naming.NamingException nme)
	    {
	      System.out.println(" Naming Exception: " + nme.getMessage());
	    }
	  }
	}
