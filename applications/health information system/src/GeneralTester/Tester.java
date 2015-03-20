package GeneralTester;

import PersonalHealthInformation.*;
import gui.PersonForm;


public class Tester {

	
	public Tester()
	{
		HealthInfoDB.getPatientList().clear();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
				
		PersonForm personForm =new PersonForm();
		
		Session session = Session.getSession();
		
		session.canView("BirthDate");
		session.canModify("BirthDate");
	    
		session.canView("LastName");
		session.canModify("LastName");
		
		HealthInfoDB.getPersonList().get(0).getPrimaryNames().get(0).addPropertyChangeListener(personForm);
		
		HealthInfoDB.getPersonList().get(0).getPrimaryNames().get(0).getSortedName();
		System.out.println(HealthInfoDB.getPersonList().get(0).getPrimaryNames().get(0).getSortedName());
		System.out.println(HealthInfoDB.getPersonList().get(0).getPrimaryNames().get(0).getFormattedName());
		
		HealthInfoDB.getPersonList().get(0).getPrimaryNames().get(0).setFirstName(
				HealthInfoDB.getPersonList().get(0).getPrimaryNames().get(0).getFirstName());
	}
}
