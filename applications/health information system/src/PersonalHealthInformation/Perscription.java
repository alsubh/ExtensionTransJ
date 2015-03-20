package PersonalHealthInformation;

import java.util.Date;

public class Perscription {
	

	private Date startDate;
	private String medication;
	private Date endDate;
	private String dosage;
	private String ferquency;
	
	
	// Setter and Getter
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getMedication() {
		return medication;
	}

	public void setMedication(String medication) {
		this.medication = medication;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getFerquency() {
		return ferquency;
	}

	public void setFerquency(String ferquency) {
		this.ferquency = ferquency;
	}

	
	// Constructors 
	public Perscription()
	{
		super();
	}
	
	public Perscription(Date startDate, String medication, Date endDate,
			String dosage, String ferquency) {
		super();
		this.startDate = startDate;
		this.medication = medication;
		this.endDate = endDate;
		this.dosage = dosage;
		this.ferquency = ferquency;
	}
	
		
}