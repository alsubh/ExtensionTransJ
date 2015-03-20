package PersonalHealthInformation;

import java.util.*;

public class Diagnosis {
	
	private Date date;
	private Physician physician;
	private String condition;
	private String notes;
	
	// Setter and Getter 
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	// Constructor
	public Diagnosis()
	{
		
	}
	public Diagnosis(Date date, String condition,String notes, Physician physician)
	{
		super();
		this.date = date;
		this.setPhysician(physician);
		this.condition = condition;
		this.notes = notes;
	}
	public Physician getPhysician() {
		return physician;
	}
	public void setPhysician(Physician physician) {
		this.physician = physician;
	}


}