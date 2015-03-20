package PersonalHealthInformation;



public class Physician extends Person {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String specialization;
	private String nationalProviderId;

	
	
	// Getter and Setter	
	public String getSpecialization()
	{
	return specialization;
	}
	public void setSpecialization(String specialization)
	{
		this.specialization = specialization;
	}
	public String getNationalProviderId() 
	{
		return nationalProviderId;
	}
	public void setNationalProviderId(String nationalProviderId) 
	{
		this.nationalProviderId = nationalProviderId;
	}

	
	// constractors 
	
	public Physician()
	{
		
	}
	public Physician(String specialization, String nationalProviderId) 
	{
		super();
		this.specialization = specialization;
		this.nationalProviderId = nationalProviderId;
	}
	
	// Methods
	
	public void addPatient(Patient patient)
	{
		
	}
	
	public void removePatient(Patient patient)
	{
		
	}

	
		
}