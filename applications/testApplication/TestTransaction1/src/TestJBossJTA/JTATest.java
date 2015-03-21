package TestJBossJTA;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import com.microsoft.sqlserver.jdbc.*;

import com.arjuna.ats.arjuna.*;
import com.arjuna.ats.internal.arjuna.common.*;
import com.arjuna.ats.internal.jdbc.ConnectionImple;

public class JTATest 
{
	public JTATest()
	{
		
	}
		
	public void insertData()
	{
		 String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//"com.sql.jdbc.Driver";  
		 ConnectionImple conn = null;
		 ConnectionImple conn2 = null;
		 Statement stmt = null;
		 
		try
        {
			// Step 1: Load the JDBC driver.   
			Class.forName(driverName);
           
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Bank1;integratedSecurity=true;";
            String connectionUrl2 = "jdbc:sqlserver://localhost;databaseName=Bank2;integratedSecurity=true;";
              
            String connUrl = "jdbc:sqlserver://AnasAlsubh-PC\\SQLEXPRESS;" +
                    "database=Bank1;" +
                    "user=anas;" +
                    "password=tedi";
            
            //conn = DriverManager.getConnection(database, "tedi", "tedi");
            conn = (ConnectionImple) DriverManager.getConnection(connUrl);
            System.out.println("Connected"); 
            
            // Create and execute an SQL statement that returns some data.  
            String SQL = "INSERT INTO [Bank1].[dbo].[Branch]([BranchName], [BranchCity],[Assets]) VALUES('Irbid Jordan Bank','Irbid',900000);"; 
            stmt = conn.createStatement();
            stmt.executeUpdate(SQL);
            
            String SQL2 = "INSERT INTO [BankDB].[dbo].[Account]([BranchID],[AccountNumber],[AccountType],[Balance])VALUES(1,'454545','S',10000.0);"; 
            stmt = conn.createStatement();
            stmt.executeUpdate(SQL2);
            
            //STEP of Commit data here.
            System.out.println("Commiting connection here....");
            conn.commit();
            
            //STEP : Now list all the available records.
            String sql = "SELECT AccountNumber,BranchID,AccountType FROM Account";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List result set for reference....");
            printRs(rs);
          
            //STEP : Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            
            ///
            // Database 2
            ///
            String connUrl2 = "jdbc:sqlserver://AnasAlsubh-PC\\SQLEXPRESS;" +
                    "database=Bank2;" +
                    "user=anas;" +
                    "password=tedi";
            
            conn2 = (ConnectionImple) DriverManager.getConnection(connUrl2);
            System.out.println("Connected2");
            
         // Create and execute an SQL statement that returns some data.  
            String SQL3 = "INSERT INTO [Bank1].[dbo].[Branch]([BranchName], [BranchCity],[Assets]) VALUES('Arab Bank','Amman',8000);"; 
            stmt = conn2.createStatement();
            stmt.executeUpdate(SQL3);
            
            String SQL4 = "INSERT INTO [BankDB].[dbo].[Account]([BranchID],[AccountNumber],[AccountType],[Balance])VALUES(2,'999999','C',70000.0);"; 
            stmt = conn2.createStatement();
            stmt.executeUpdate(SQL4);
            
            //STEP of Commit data here.
            System.out.println("Commiting connection here....");
            conn.commit();
            
            //STEP : Now list all the available records.
            String sql1 = "SELECT AccountNumber,BranchID,AccountType FROM Account";
            ResultSet rs2 = stmt.executeQuery(sql1);
            System.out.println("List result set for reference....");
            printRs(rs2);
          
            //STEP : Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
         }
		 catch(SQLException se)
		 {
            //Handle errors for JDBC
            se.printStackTrace();
            // If there is an error then rollback the changes.
            System.out.println("Rolling back data here....");
		      	 try
		      	 {
		      		 if(conn!=null)
		                  conn.rollback();
		         }
		      	 catch(SQLException se2)
		      	 {
		               se2.printStackTrace();
		         }//end try
		
		         }
				 catch(Exception e)
				 {
		            //Handle errors for Class.forName
		            e.printStackTrace();
		         }
				finally
				{
		            //finally block used to close resources
			        try
			        {
			           if(stmt!=null)
			           stmt.close();
			        }
			        catch(SQLException se2)
			        {
			        }// nothing we can do
				    
			        try
				    {
				       if(conn!=null)
				           conn.close();
				    }
				    catch(SQLException se)
				    {
				           se.printStackTrace();
				    }//end finally try
			    }//end try
		        System.out.println("Goodbye!");
		      }
	
	public void printRs(ResultSet rs) throws SQLException
	{
	      //Ensure we start with first row
	      rs.beforeFirst();
	      while(rs.next()){
	         //Retrieve by column name
	         String accountNumber  = rs.getString("AccountNumber");
	         int branchID = rs.getInt("BranchID");
	         String accountType = rs.getString("AccountType");
	         
	         //Display values
	         System.out.print("AccountNumber: " + accountNumber);
	         System.out.print(", BranchID: " + branchID);
	         System.out.print(", AccountType: " + accountType);
	     }
	     System.out.println();
	   }//end printRs()
	}//end JDBCExample