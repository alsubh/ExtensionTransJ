package PersonalHealthInformation;

import java.util.Date;

public class Surgery {
	
	private Date date;
	private Physician physician;
	private String type;
	private String notes;
	
	public Surgery()
	{
		
	}
	public Surgery(Physician physician)
	{
		this.setPhysician(physician);
	}
	
	public Surgery(Date date, String type, String notes, Physician physician) 
	{
		super();
		this.date = date;
		this.setPhysician(physician);
		this.type = type;
		this.notes = notes;
	}
	
	public void setDate(Date date)
	{
        this.date = date;
     }

     public Date getDate() 
     {
        return date;
     }
     
    public void setType(String type)
    {
        this.type = type;
     }

     public String getType() {
        return type;
     }
     	
    public void setNote(String notes)
    {
        this.notes = notes;
    }

     public String getNote() 
     {
        return notes;
     }
	public Physician getPhysician() {
		return physician;
	}
	public void setPhysician(Physician physician) {
		this.physician = physician;
	}
}
