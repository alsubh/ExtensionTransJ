package TestJBossJTA;

import com.arjuna.ats.internal.jta.xa.*;
import com.arjuna.ats.jta.xa.XidImple;
import com.arjuna.ats.internal.jta.resources.*;
import com.arjuna.ats.internal.jta.resources.arjunacore.*;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerXADataSource; 
import java.sql. *; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import javax.sql.*; 
import javax.transaction.xa.*;
import com.microsoft.sqlserver.jdbc.SQLServerXAResource.*;

public class Mutil_DataSource_Test 
{ 
	public static void main (String [] args) 
	{ 
		Mutil_DataSource_Test mdt = new Mutil_DataSource_Test(); 
		try
		{ 
			mdt.test1 (); 
		}
		catch (Exception ex)
		{ 
			System.out.println ("abnormal except SQLException, XAException outside: \n"); 
			Logger.getLogger (Mutil_DataSource_Test.class.getName ()).log (Level.SEVERE, null, ex); 
		} 
	} 
	
	// Multi-database test 
	public void test1 () 
	{ 
		// Define the variables used in 
		Connection sqlCn = null; 
	
		XADataSource sqlDs = null; 
	
		XAConnection xasqlCn = null; 

		XAResource xasqlRes  = null; 
	
		XidImple sqlXid = null; 
	
		Statement sqlpst = null; 

		try
		{ 
			// Get the data source 
			sqlDs =  new SQLServerXADataSource (); 
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			((SQLServerDataSource) sqlDs).setURL("jdbc:sqlserver://localhost:1433;databaseName=Bank1;user=anas;password=tedi;integratedSecurity=true");
			//sqlDs.setURL ("jdbc:sqlserver://129.123.7.41; DatabaseName=bank1; loginTimeout = 20; user=anas ; password=tedi"); 
			//SqlDs.setUser ("anas"); 
			//SqlDs.setPassword ("tedi"); 
			// SqlDs.setServerName ("129.123.7.41"); 
			// SqlDs.setPortNumber (1433); 
			// SqlDs.setDatabaseName ("bank1"); 

			// Get connected 
			xasqlCn = sqlDs.getXAConnection (); 
			System.out.println ("xasqlCn: "+ xasqlCn); 
		
			sqlCn = xasqlCn.getConnection (); 	
			sqlpst = sqlCn.createStatement (); 

			// Defined XAResources. 
			xasqlRes = xasqlCn.getXAResource (); 

			// Define Xid 
			sqlXid = new XidImple();//(0, new byte [] {0x01}, new byte [] {0x03}); 

			// Implementation of SQL Server 
			xasqlRes.start(sqlXid, XAResource.TMNOFLAGS); 
			//sqlpst.executeUpdate("insert into test values ??('444 ')");
			sqlpst.executeUpdate("INSERT INTO [Bank1].[dbo].[Branch]([BranchName], [BranchCity],[Assets]) VALUES('Irbid Jordan Bank','Irbid',900000);");
			xasqlRes.end(sqlXid, XAResource.TMSUCCESS); 
			
			// Prepare 
			int sqlRea = xasqlRes.prepare(sqlXid); 

			// Determine ready or not to submit or rollback 
			if (sqlRea == xasqlRes.XA_OK) 
			{ 
				xasqlRes.commit (sqlXid, false); 
				System.out.println ("SQL Server transaction submitted successfully!"); 
			}
			else
			{ 
				xasqlRes.rollback (sqlXid); 
				System.out.println ("transaction rollback is successful!"); 
			} 
		}
		catch (SQLException ex)
		{ 
			Logger.getLogger (Mutil_DataSource_Test.class.getName ()).log (Level.SEVERE, null, ex); 
			try 
				{ 
					xasqlRes.rollback (sqlXid); 
				}
				catch (XAException e) 
				{ 
					System.out.println ("rollback error slightly! ~"); 
					e.printStackTrace (); 
			 		Logger.getLogger (Mutil_DataSource_Test.class.getName ()).log (Level.SEVERE, null, e); 
			 	}
				finally
				{ 
					try { 
						// Close  
						sqlpst.close (); 
						sqlCn.close (); 
						xasqlCn.close (); 
					}
					catch (SQLException sqex) 
					{ 
						Logger.getLogger (Mutil_DataSource_Test.class.getName ()).log(Level.SEVERE, null, sqex); 
					} 
				}
		} catch (XAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}