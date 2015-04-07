/**
 * 
 */
package PersonalHealthInformation;

import java.io.Serializable;
import java.util.*;


public class Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Name> PrimaryNames=new ArrayList<Name>();	
	private List<Address> Addresses =new ArrayList<Address>();
	private List<PhoneNumber> Phones=new ArrayList<PhoneNumber>();
	private final static float threshold=(float) 2.5;
	
	
	//Getters and setters
	@Getter
	public List<Name> getPrimaryNames() {
		return PrimaryNames;
	}

	@Getter
	public List<Address> getAddresses() {
		return Addresses;
	}

	@Getter
	public List<PhoneNumber> getPhones() {
		return Phones;
	}

		
	// Contractors 
	public Person() {

	}

	// Methods
	
	public void addName(Name name)
	{
		PrimaryNames.add(name);
	}
		
	public void removeName(Name name)
	{
		PrimaryNames.remove(name);
	}
	
	public void addPhoneNumber(PhoneNumber phoneNumber)
	{
		Phones.add(phoneNumber);
	}
	
	public void removePhoneNumber(PhoneNumber phoneNumber)
	{
		PrimaryNames.remove(phoneNumber);
	}	
	
	public void addAddress(Address address)
	{
		Addresses.add(address);
	}
	
	public void removeAddress(Address address)
	{
		PrimaryNames.remove(address);
	}
	
	
	public List<Name> SortNames()
	{
	  List<Name> nameList=PrimaryNames;
	  Collections.sort(nameList,new Name());
	  return nameList;
	}
	
	@Method
	public boolean match(Person person)
	{	
		
		float matchVal=0, matchVal1=0, matchVal2=0, matchVal3=0;
		for(Name name1: this.getPrimaryNames())
		{
			for(Name name2: person.getPrimaryNames())
			{
				if(name1.match(name2)>= Name.getMatchthreshold())
				{
					matchVal1=2;
					break;
				}
			}
		}
		for(PhoneNumber phone1: this.getPhones())
		{
			for(PhoneNumber phone2: person.getPhones())
			{
				if(phone1.match(phone2) == phone1.getMatchThreshold())
				{
					matchVal2=1;
					break;
				}
			}
		}
		
		for(Address address1: this.getAddresses())
		{
			for(Address address2: person.getAddresses())
			{
				if(address1.match(address2) > address1.getMatchThreshold())
				{
					matchVal3=(float) 0.5;
					break;
				}
				
			}
		}
		
		matchVal=matchVal1+ matchVal2+ matchVal3;
		
		if(matchVal>=threshold)
		{
			return true;
		}
		return false;
	}
}