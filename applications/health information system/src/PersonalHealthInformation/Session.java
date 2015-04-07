package PersonalHealthInformation;

import java.util.HashMap;

// Singleton Class 
public class Session 
{
		private static Session session = null;
		
		
		private Session()
		{
			initialize();		
		}
		
		
		HashMap<String, Boolean> view_priviledge = new HashMap<String, Boolean>(); 
		HashMap<String, Boolean> modify_priviledge = new HashMap<String, Boolean>();

		private void initialize()
		{	
			view_priviledge.put("BirthDate", false);
			view_priviledge.put("LastName", true);
			
			modify_priviledge.put("BirthDate", false);
			modify_priviledge.put("LastName", true);	
	
		}
		
		public static Session getSession()
		{
			if(session==null)
			{
				session = new Session();
			}
			return session;
		}
		

		
		public  boolean canView(String fieldName)
		{		
			//System.out.println("Entering Can view "+ fieldName);
			
			return view_priviledge.get(fieldName); 
		}


		public  boolean canModify(String fieldName)
		{		
			return modify_priviledge.get(fieldName); 
		}
		
}
