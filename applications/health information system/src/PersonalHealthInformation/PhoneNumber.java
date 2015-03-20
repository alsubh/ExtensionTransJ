package PersonalHealthInformation;

import java.io.Serializable;
import java.util.Comparator;

public class PhoneNumber implements Comparator<PhoneNumber>,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// data member 
	private String phoneType;
	private String areaCode;
	private String exchange;
	private String detailNumber;
	private String extension;
	private static final float matchThreshold=(float) 1.0;
		
	public enum PhoneType
	{
		Home, Cell, Work; 
	}
	public float getMatchThreshold() {
		return matchThreshold;
	}
	
	// Setter and Getter
	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getDetailNumber() {
		return detailNumber;
	}

	public void setDetailNumber(String detailNumber) {
		this.detailNumber = detailNumber;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	// Contractors 
	public PhoneNumber()
	{
		
	}
	
	public PhoneNumber(String phoneType, String areaCode, String exchange,
			String detailNumber, String extension) {
		super();
		this.phoneType = phoneType;
		this.areaCode = areaCode;
		this.exchange = exchange;
		this.detailNumber = detailNumber;
		this.extension = extension;
	}
	
	//Method	
	public String getFormattedNumber()
    {
		// formatted the data before storing
		return areaCode+'-'+exchange+'-'+detailNumber;
    }
    	
    public float match(PhoneNumber  phoneNumber)
    {
    	int matchval=compare(this, phoneNumber);
   	    if(matchval==0)
   	    	return (float) 1.0;
   	    else
   	    	return (float) 0.0; 
    }

	@Override
	public int compare(PhoneNumber phone1, PhoneNumber phone2) {
		// TODO Auto-generated method stub
		return phone1.getFormattedNumber().compareTo(phone2.getFormattedNumber());
	}
}