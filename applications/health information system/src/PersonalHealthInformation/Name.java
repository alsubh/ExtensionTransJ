package PersonalHealthInformation;

import java.util.Comparator;
import java.io.Serializable;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.language.Soundex;

public class Name implements Comparator<Name>,Serializable{
	
	

	private static final long serialVersionUID = 1L;
	
	// Data member
	private String nameType;
	private String salutation;
	private String firstName = "";
	private String middleName="";
	private String lastName="";
	private String suffix;
	private static final float matchThreshold=(float) 3;	
	
	
	protected int age =0;
	public int age1 = 0;
	public static int age2 = 0;
	private int age4 = 0;
	int age5 = 0;
	

//	public int getAge()
//	{
//		return age;
//	}
//	
//	public void setAge(int age)
//	{
//		this.age = age;
//	}
	
	public static float getMatchthreshold() {
		return matchThreshold;
	}

	
	
	
	
	
	public enum NameType 
	{
		aliases, givenName, familyName;
	}
	/// Setter and Getter
	public String getNameType() {
		return nameType;
	}

	public void setNameType(String nameType) {
		this.nameType = nameType;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	@Encrypted
	public String getFirstName() {
		return firstName;
	}

	@Encrypted
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Protected
	public String getLastName() {
		return lastName;
	}
	
	@Protected
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	
	/// Contractors
	public Name()
	{
		
	}
	
	public Name(String nameType, String salutation, String firstName,
			String middleName, String lastName, String suffix) {
		super();
		this.nameType = nameType;
		this.salutation = salutation;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.suffix = suffix;
	}
	
	
	// Methods
	//@Protected
	@PII
	public String getSortedName()
	{
		return lastName+' '+middleName+' '+firstName;
	}
	@Method
	public String getFormattedName()
	{
		// return formatted name then store it in the list. 
		// may be call before add name to list. 
		
	return firstName+' '+middleName+' '+lastName+' '+suffix;
	}
	
	
	public int  match(Name nameToCompare)
	{
		// fuzzy matching
		// ? this method before adding , or doesn't matter
		//Highest matchVal=>4 lowest =>0
		Soundex soundMatching=new Soundex();
		int matchVal=-1;
		try {
			matchVal = soundMatching.difference(this.getFormattedName(),nameToCompare.getFormattedName());
		} catch (EncoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return matchVal;
		
		
		
	}
	@Override
	public int compare(Name name1, Name name2) {
		// TODO Auto-generated method stub
		return name1.getSortedName().compareTo(name2.getSortedName());
	}

}
	
