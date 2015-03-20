package PersonalHealthInformation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


// static class
public  class HealthInfoDB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static private List<Patient> patientList=new ArrayList<Patient>();
	static private List<Person> personList= new ArrayList<Person>();

	
	public static List<Patient> getPatientList() {
		return patientList;
	}
	@Method
	public static void AddPatient(Patient patient)
	{
		patientList.add(patient);
	}
	@Method
	public  static void RemovePatient(Patient patient)
	{
		patientList.remove(patient);
	}
	@Method
	public static List<Person> getPersonList() {
		return personList;
	}
	
	public static void resetPersonList()
	{
		personList.clear();
		patientList.clear();
	}
	
	@Method
	public static boolean AddPerson(Person person)
	{
		personList.add(person);
		return true;
	}
	@Method
	public  static boolean RemovePerson(Person person)
	{
		personList.remove(person);
		return true;
	}
	

	public static void save()
	{
		// Serialization
		
	}
	
	public static void load()
	{
		//Deserialization
	}
}
