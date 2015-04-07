package TestJBossJTA;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;


public class testJDNI 
{
	 public static void main (String args []) throws java.sql.SQLException
	 {
		 testJDNI test= new testJDNI();
		 test.method();
	 }
	 
	public void  method()
	{
		try {
	        //jbInit();
			Hashtable<String, String> env = new Hashtable<String, String>();
			Connection conn= null;	
			
	        env.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
	        env.put(javax.naming.Context.PROVIDER_URL,"t3://localhost:1433");
	        
	        try {
	            Context ctx = new InitialContext(env);
	            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup("jdbc/Userdb");
	            conn = ds.getConnection();
	        } catch(Exception e){
	            e.printStackTrace();
	        }
	        
	        if(conn != null){
	            System.out.println("Got connection...");
	            
	            String colDescQuery =
	                "select Name from customer";
	            Statement colDescStmt = conn.createStatement();
	            ResultSet colDescRS = colDescStmt.executeQuery(colDescQuery);
	            
	            while (colDescRS.next()) {
	                System.out.println(colDescRS.getString(1));
	            }                 
	            
	        } else {
	            System.out.println("No connection...");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
