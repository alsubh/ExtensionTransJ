package BusinessTier;

import java.io.Serializable;

import PersonalHealthInformation.Person;
import PersonalHealthInformation.HealthInfoDB;;

public class Operations implements Serializable {

	
	
	public boolean  Save(Person p)
	{
		if(HealthInfoDB.AddPerson(p))		
			return true;
		else
			return false;
	}
	
	public boolean  Delete(Person p)
	{
		if(HealthInfoDB.RemovePerson(p))		
			return true;
		else
			return false;
	}
	

}
