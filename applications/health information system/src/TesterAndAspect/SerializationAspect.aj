package TesterAndAspect;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

import PersonalHealthInformation.Address;
import PersonalHealthInformation.Allergy;
import PersonalHealthInformation.Diagnosis;
import PersonalHealthInformation.EmergencyContact;
import PersonalHealthInformation.HealthInfoDB;
import PersonalHealthInformation.HealthIssue;
import PersonalHealthInformation.Name;
import PersonalHealthInformation.Patient;
import PersonalHealthInformation.Perscription;
import PersonalHealthInformation.Person;
import PersonalHealthInformation.PhoneNumber;
import PersonalHealthInformation.Physician;
import PersonalHealthInformation.Surgery;
import PersonalHealthInformation.Address.AddressType;
import PersonalHealthInformation.Name.NameType;
import PersonalHealthInformation.PhoneNumber.PhoneType;


public aspect SerializationAspect
{

	File f = new File("person.txt");
	
	pointcut MainSerializer(): execution(* GeneralTester.*.main(..)) ||
	execution(* gui.*.main(..));
	
	before() : MainSerializer()
	{
		try {
			if(ReadFile(f))
				{
				System.out.println("Loading data from file");
					DeserializeData();
				}
			else
			{
				System.out.println("File was empty : Loading the data ");
				LoadTestData();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	after() : MainSerializer()
	{
		
		serialize(PersonalHealthInformation.HealthInfoDB.getPersonList());
	}
	

	boolean ReadFile(File f) throws IOException
	{
		InputStream file = new FileInputStream(f);
        InputStream buffer = new BufferedInputStream( file );       
        
        if(buffer.available() != 0){
        	return true;
        }
        else 
        	return false;        
	}
	
	void DeserializeData()
	{
		ArrayList<Person> perList=deserialize();

		PersonalHealthInformation.HealthInfoDB.resetPersonList();//sgetPersonList().clear();
		
		for(Person p : perList)
		{	
		  PersonalHealthInformation.HealthInfoDB.AddPerson(p);		 		  
		}
			
	}
	
	public void serialize(java.util.List<Person> list)
	{		
				
	      try
	      {		
	    	  if(f!=null){
		         FileOutputStream fileOut = new FileOutputStream(f);
		         ObjectOutputStream out =  new ObjectOutputStream(fileOut);		         
		         out.writeObject(list);
		         out.flush();
		         out.close();
		         fileOut.close();
	    	  }
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	          
	      }	      
	      System.out.println("Serialization is working ");	   
	}

	
	@SuppressWarnings("unchecked")
	public ArrayList<Person> deserialize()
	{		
		ArrayList<Person> recoveredPersons=null;
		try{				    	  
			InputStream file = new FileInputStream( f);
            InputStream buffer = new BufferedInputStream( file );
            ObjectInput input = new ObjectInputStream ( buffer );
            recoveredPersons=(ArrayList<Person>)input.readObject();
            System.out.println("recovered persons: "+recoveredPersons.size());
            file.close();
            
        }catch(Exception i)
        {
        	i.printStackTrace();
           return null;
        }
		if(recoveredPersons != null && recoveredPersons.size()>0)
        	 System.out.println("Deserialized Person : " + recoveredPersons.get(0).getPrimaryNames().get(0).getFormattedName());         
        return recoveredPersons;
	}
	
	private void LoadTestData()
	{
		Date DOB1=null;
		Date DOB2=null;
		Date DOB3=null;
		Date  Perscriptiondate1=null;
		Date  Perscriptiondate2=null;
		Date  Perscriptiondate3=null;
		Date  Perscriptiondate4=null;
		
		Person person1 =new Person();
		Person person2 =new Person();
		Person person3 =new Person();
		Person person4 =new Person();
		Person person5 =new Person();
		
		Date HealthIssuedate1=null;
		Date HealthIssuedate2=null;
		Date HealthIssuedate3=null;
		Date HealthIssuedate4=null;
		
		Date Diagnosisdate1=null;
		Date Diagnosisdate2=null;
		
		Date Surgerydate1=null;
		Date Surgerydate2=null;
		
			
		Physician surgeon =new Physician("surgery","123544");
		Physician orthopaedic =new Physician("orthopaedic", "258741");
		Physician dentist =new Physician("dentistry", "963852");
		
		EmergencyContact contact1=new EmergencyContact(person1,"Brother");
		EmergencyContact contact2=new EmergencyContact(person2,"Mother");
		EmergencyContact contact3=new EmergencyContact(person1,"Father");
		
		DateFormat dfm = new SimpleDateFormat("MM-dd-yyyy");
		try
		{
			 DOB1 = dfm.parse("02-26-2007");
			 DOB2 = dfm.parse("01-20-1986");
			 DOB3 = dfm.parse("05-15-1985");
			 Perscriptiondate1 = dfm.parse("02-06-2008");
			 Perscriptiondate2 = dfm.parse("04-02-2010");
			 Perscriptiondate3 = dfm.parse("06-06-2008");
			 Perscriptiondate4 = dfm.parse("12-02-2010");
			 	
			HealthIssuedate1 = dfm.parse("02-06-2008");
			HealthIssuedate2 = dfm.parse("04-02-2009");
			HealthIssuedate3 = dfm.parse("06-06-2008");
			HealthIssuedate4 = dfm.parse("12-02-2009");

			Diagnosisdate1 = dfm.parse("02-06-2008");
			Diagnosisdate2 = dfm.parse("01-02-2009");
			
			Surgerydate1 = dfm.parse("02-26-2008");
			Surgerydate2 = dfm.parse("01-20-2009");	
		} 
		catch (ParseException e)
		{

		}
		
		Patient patient1=new Patient(1, "M", DOB1);
		Patient patient2=new Patient(2, "F", DOB2);
		Patient patient3=new Patient(3, "F", DOB3);
			
		person1.addName(CreatePersonName(NameType.aliases.toString(),"Mr." ,"John","A","Allen","Jr"));
		person1.addName(CreatePersonName(NameType.familyName.toString(),"Mr." ,"Johnny","A","Allen","Jr"));
		person1.addPhoneNumber(CreatePersonPhoneNumber(PhoneType.Cell.toString(), "435", "797", "1000", "222"));
		person1.addPhoneNumber(CreatePersonPhoneNumber(PhoneType.Home.toString(), "435", "797", "2222", "444"));
		person1.addAddress(CreatePersonAddress(AddressType.CurrentMailAddress.toString(), "36 Aggie Village", " ", "Logan", "UT", "84341"));
		person1.addAddress(CreatePersonAddress(AddressType.Residental.toString(), "460 N 400 E", "apt. 39 ", "Logan", "UT", "84321"));
		
		
		person2.addName(CreatePersonName(NameType.givenName.toString(),"Mrs." ,"Mary","W","Watson"," "));
		person2.addName(CreatePersonName(NameType.familyName.toString(),"Mrs." ,"Mary","W","Watson"," "));
		person2.addPhoneNumber(CreatePersonPhoneNumber(PhoneType.Cell.toString(), "435", "575", "9969", "420"));
		person2.addPhoneNumber(CreatePersonPhoneNumber(PhoneType.Work.toString(), "881", "700", "8888", "444"));
		person2.addAddress(CreatePersonAddress(AddressType.CurrentMailAddress.toString(), "38 Aggie Village", " ", "Logan", "UT", "84341"));
		person2.addAddress(CreatePersonAddress(AddressType.Residental.toString(), "500 N 600 W", "apt. 05 ", "Logan", "UT", "84333"));
		
		
		person3.addName(CreatePersonName(NameType.aliases.toString(),"Mr." ,"Joe","S","Smiths","Jr"));
		
		person3.addName(CreatePersonName(NameType.familyName.toString(),"Mr." ,"Joe","S","Smith","Jr"));
		person3.addPhoneNumber(CreatePersonPhoneNumber(PhoneType.Work.toString(), "801", "777", "8877", "222"));
		person3.addPhoneNumber(CreatePersonPhoneNumber(PhoneType.Home.toString(), "435", "797", "6653", " "));
		person3.addAddress(CreatePersonAddress(AddressType.CurrentMailAddress.toString(), "08 Aggie Village", " ", "Logan", "UT", "84341"));
		person3.addAddress(CreatePersonAddress(AddressType.Residental.toString(), "08 Aggie Village", " ", "Logan", "UT", "84341"));
		
		person4.addName(CreatePersonName(NameType.aliases.toString(),"Mr." ,"John","A","Allen","Jr"));
		person4.addName(CreatePersonName(NameType.familyName.toString(),"Mr." ,"Johnny","A","Allen","Jr"));
		person4.addPhoneNumber(CreatePersonPhoneNumber(PhoneType.Cell.toString(), "435", "797", "1000", "222"));
		person4.addPhoneNumber(CreatePersonPhoneNumber(PhoneType.Home.toString(), "435", "797", "2222", "444"));
		person4.addAddress(CreatePersonAddress(AddressType.CurrentMailAddress.toString(), "36 Aggie Village", " ", "Logan", "UT", "84341"));
		person4.addAddress(CreatePersonAddress(AddressType.Residental.toString(), "460 N 400 E", "apt. 39 ", "Logan", "UT", "84321"));
		
		person5.addName(CreatePersonName(NameType.givenName.toString(),"Mrs." ,"Mary","W","Watson"," "));
		person5.addName(CreatePersonName(NameType.familyName.toString(),"Mrs." ,"Mary","W","Watson"," "));
		person5.addPhoneNumber(CreatePersonPhoneNumber(PhoneType.Cell.toString(), "801", "575", "9969", "420"));
		person5.addPhoneNumber(CreatePersonPhoneNumber(PhoneType.Work.toString(), "881", "701", "8888", "444"));
		person5.addAddress(CreatePersonAddress(AddressType.CurrentMailAddress.toString(), "39 Aggie Village", " ", "SLC", "UT", "84341"));
		person5.addAddress(CreatePersonAddress(AddressType.Residental.toString(), "500 N 600 W", "apt. 05 ", "SLC", "UT", "84333"));
		
		patient1.addPhysician(surgeon);
		patient1.addPhysician(orthopaedic);
		patient1.addEmergencyContact(contact1);
		patient1.AddAllergy(CreateAllergy("Pollen", 5));
		patient1.AddDiagnosis(CreateDiagnosis(Diagnosisdate1, "Broken bone", "Broke left leg"));
		patient1.AddSurgeries(CreateSurgery(Surgerydate1, "Bone alignment", "Metal plate inserted"));
		patient1.addHealthIssue(CreateHealthIssue(HealthIssuedate1, HealthIssuedate2, "Pain in leg"));
		patient1.AddPerscription(CreatePerscription(Perscriptiondate1, Perscriptiondate2, "Calcium", "1 tab", "2 tabs daily"));
		
		patient2.addPhysician(dentist);
		patient2.addPhysician(orthopaedic);
		patient2.addEmergencyContact(contact1);
		patient2.addEmergencyContact(contact3);		
		patient2.AddAllergy(CreateAllergy("Sea Food", 6));
		patient2.AddDiagnosis(CreateDiagnosis(Diagnosisdate2, "Pain in tooth", "toothache"));
		patient2.AddSurgeries(CreateSurgery(Surgerydate2, "root canal", "root canal"));
		patient2.addHealthIssue(CreateHealthIssue(HealthIssuedate3, HealthIssuedate4, "Toothache"));
		patient2.AddPerscription(CreatePerscription(Perscriptiondate3, Perscriptiondate4, "Ibprofen", "1 tab", "4 tabs daily"));
		
		patient3.addPhysician(surgeon);
		patient3.addPhysician(dentist);
		patient3.addEmergencyContact(contact1);
		patient3.addEmergencyContact(contact2);		
		patient3.AddAllergy(CreateAllergy(" no allergy", 0));
		patient3.AddDiagnosis(CreateDiagnosis(Diagnosisdate1, "High blood pressure", "High blood pressure"));
		patient3.AddSurgeries(CreateSurgery(Surgerydate2, "Bypass", "bypass"));
		patient3.AddSurgeries(CreateSurgery(Surgerydate1, "Pacemaker", "insert pacemaker"));
		patient3.addHealthIssue(CreateHealthIssue(HealthIssuedate3, HealthIssuedate4, "Chest pain"));
		patient3.AddPerscription(CreatePerscription(Perscriptiondate3, Perscriptiondate4, "BYSTOLIC", "1 tab", "4 tabs daily"));
		
		HealthInfoDB.AddPatient(patient1);
		HealthInfoDB.AddPatient(patient2);
		HealthInfoDB.AddPatient(patient3);
		
		
		Name name1=CreatePersonName(NameType.aliases.toString(),"Mr." ,"John","A","Allen","Jr");
		Name name2=CreatePersonName(NameType.aliases.toString(),"Mr." ,"Mary","A","Smith","Sr");		
		System.out.println("Name match value:"+name1.match(name2));
		
		PhoneNumber phone1= CreatePersonPhoneNumber(PhoneType.Cell.toString(), "435", "797", "2000", "444");
		PhoneNumber phone2= CreatePersonPhoneNumber(PhoneType.Cell.toString(), "435", "797", "1000", "222");
		System.out.println("Phone match value:"+phone1.match(phone2));
		
		
		Address add1=  CreatePersonAddress(AddressType.CurrentMailAddress.toString(), "36 Aggie Village", " ", "Logan", "UT", "84341");
		Address add2=CreatePersonAddress(AddressType.CurrentMailAddress.toString(), "36 Aggie Village", " ", "Salt", "UT", "84321");
		System.out.println("Address match value:"+add1.match(add2));
		
		
		// Remove function 
		HealthInfoDB.RemovePatient(patient1);
		HealthInfoDB.RemovePerson(person1);	
		
		if(HealthInfoDB.getPersonList().size()==0)
		HealthInfoDB.AddPerson(person1);
		
		if(!person1.match(person2))
			HealthInfoDB.AddPerson(person2);
		
		if(!person1.match(person4))
			HealthInfoDB.AddPerson(person4);			
		
		if(!person2.match(person3))
			HealthInfoDB.AddPerson(person3);
		
		if(!person2.match(person5))
			HealthInfoDB.AddPerson(person5);
	
		for(Person p: HealthInfoDB.getPersonList())
		{
			System.out.println("List count:"+HealthInfoDB.getPersonList().size());
			for(Name n: p.getPrimaryNames())
			{
				System.out.println(n.getFormattedName());
			}
		}		

	}
	
	public static List<Patient> getPatientsWithDiagnosis( String condition)
	{
		List<Patient> p =new ArrayList<Patient>();
		
		for(Patient patient: HealthInfoDB.getPatientList())
		{
			for( Diagnosis d: patient.getPatientDiagnoses())
			{
				if (d.getCondition().equals(condition))
				{
					p.add(patient);
				}
			}
		}
		return p;
	}
	
	public static Name CreatePersonName(String nameType, String Salutation, String firstName, String middleName ,String lastName, String suffix)
	{
		
		Name primaryName=new Name();
		primaryName.addPropertyChangeListener(new gui.PersonForm());
		primaryName.setNameType(nameType);
		primaryName.setSalutation(Salutation);
		primaryName.setFirstName(firstName);
		primaryName.setMiddleName(middleName);
		primaryName.setLastName(lastName );
		primaryName.setSuffix(suffix);
		return primaryName;
	}

	public static PhoneNumber CreatePersonPhoneNumber(String phonetype, String areaCode, String exchange, String dnumber, String ext)
	{		
		PhoneNumber phone1=new PhoneNumber();
		phone1.addPropertyChangeListener(new gui.PersonForm());
		phone1.setPhoneType(phonetype );
		phone1.setAreaCode(areaCode );
		phone1.setExchange(exchange);
		phone1.setDetailNumber(dnumber);
		phone1.setExtension(ext);
		
		return phone1;
	}
	
	public static Address CreatePersonAddress(String AddressType, String StreeLine1, String StreetLine2, String city, String state, String postalCode)
	{
		
		Address address1=new Address();
		address1.addPropertyChangeListener(new gui.PersonForm());
		address1.setAddressType(AddressType);
		address1.setStreetLine1(StreeLine1);
		address1.setStreetLine2(StreetLine2);
		address1.setCity(city);
		address1.setState(state);
		address1.setPostalCode(postalCode);
							
		return address1;
	}
	
	public static Surgery CreateSurgery(Date date, String type, String notes)
	{
		
		Surgery surgery1=new Surgery();
		surgery1.setDate(date);
		surgery1.setType(type);
		surgery1.setNote(notes);
		
		return surgery1;
		
	}
	
	public static Diagnosis CreateDiagnosis(Date date1, String Cond, String notes)
	{
		Diagnosis diagnosis1=new Diagnosis();
		diagnosis1.setDate(date1);
		diagnosis1.setCondition(Cond);
		diagnosis1.setNotes(notes);
			
		return diagnosis1;
	}
	
	public static HealthIssue CreateHealthIssue(Date Bdate, Date Edate, String sym)
	{
	
		HealthIssue healthIssue1=new HealthIssue();
		healthIssue1.setBeganOn(Bdate);
		healthIssue1.setEndedOn(Edate);
		healthIssue1.setSymptomOrObservation(sym);
		
		return healthIssue1;
		
	}
	
	public static Perscription CreatePerscription(Date Sdate, Date eDate, String medication, String dosage, String freq)
	{
		
		Perscription perscription1=new Perscription();
		perscription1.setStartDate(Sdate);
		perscription1.setEndDate(eDate);
		perscription1.setMedication(medication);
		perscription1.setDosage(dosage);
		perscription1.setFerquency(freq);
				
		return perscription1;
	}
	
	public static Allergy CreateAllergy(String Allergen, int severity )
	{
		
		Allergy allergy1=new Allergy();
		allergy1.setAllergen(Allergen);
		allergy1.setSeverity(severity);
				
		return allergy1;
	}
	
	

	
}