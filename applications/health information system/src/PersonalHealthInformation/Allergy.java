package PersonalHealthInformation;

public class Allergy {
	
	private String allergen;
	private int severity;
	
	// setter and Getter
	
	
	public void setAllergen(String allergen) {
        this.allergen = allergen;
     }

     public String getAllergen() 
     {
        return allergen;
     }
     	
    public void setSeverity(int severity)
    {
        this.severity = severity;
     }

     public int getSeverity() {
        return severity;
     }
	
     
     // Constructors 
	public Allergy() 
	{
		super();
	}
	
	public Allergy(String allergen, int severity)
	{
		super();
		this.allergen = allergen;
		this.severity = severity;

	}

	
	
	

}
