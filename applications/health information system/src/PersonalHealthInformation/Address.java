package PersonalHealthInformation;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Comparator;

public class Address implements Comparator<Address>,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// data Member
	
	private String addressType;
	private String StreetLine1;
	private String StreetLine2;
	private String city;
	private String state;
	private String postalCode;
	private static final float matchThreshold=(float) 80.0;
	


	public enum AddressType
	{
		CurrentMailAddress, Residental; 
	}
	// Getter and Setter	
	@Getter
	public float getMatchThreshold() {
		return matchThreshold;
	}
	@Getter
	public String getAddressType() {
		return addressType;
	}
	@Setter
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	@Getter
	public String getStreetLine1() {
		return StreetLine1;
	}
	@Setter
	public void setStreetLine1(String streetLine1) {
		StreetLine1 = streetLine1;
	}
	@Getter
	public String getStreetLine2() {
		return StreetLine2;
	}
	@Setter
	public void setStreetLine2(String streetLine2) {
		StreetLine2 = streetLine2;
	}

	@Getter
	public String getCity() {
		return city;
	}
	@Setter
	public void setCity(String city) {
		this.city = city;
	}
	@Getter
	public String getState() {
		return state;
	}
	@Setter
	public void setState(String state) {
		this.state = state;
	}
	@Getter
	public String getPostalCode() {
		return postalCode;
	}
	@Setter
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	//Contractors 
	public Address()
	{
		
	}
	
	public Address(String addressType, String streetLine1, String streetLine2,
			String city, String state, String postalCode) {
		super();
		this.addressType = addressType;
		StreetLine1 = streetLine1;
		StreetLine2 = streetLine2;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
	}
	
	// methods
	@Method
    public String getFormattedAddress()
    {
    	if(StreetLine2.isEmpty())
    	{
    		return StreetLine1+','+city+','+state+','+postalCode;
    	}
    	else
    		return StreetLine1+','+StreetLine2+','+city+','+state+','+postalCode;
    }
    	
    public float match(Address address)
    {
    	float matchVal=0;
		if(this.getStreetLine1().equals(address.getStreetLine1()))
			matchVal ++;
		if(this.getStreetLine2().equals(address.getStreetLine2()))
			matchVal ++;
		if(this.getCity().equals(address.getCity()))
			matchVal=(float) (matchVal+1.5);
		if(this.getState().equals(address.getState()))
			matchVal=(float) (matchVal+2);
		if(this.getPostalCode().equals(address.getPostalCode()))
			matchVal=(float) (matchVal+2);
		
		DecimalFormat df = new DecimalFormat("#.##");
		String res=df.format((matchVal/7.5)*100);        
		return (Float.parseFloat(res));
    }

	@Override
	public int compare(Address add1, Address add2) {
		// TODO Auto-generated method stub
		return  add1.getFormattedAddress().compareTo(add2.getFormattedAddress());
	} 
}
