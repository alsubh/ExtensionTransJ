package PersonalHealthInformation;

import java.util.Date;;

public class HealthIssue {
	
	private Date beganOn;
	private Date endedOn;
	private String symptomOrObservation;
		
	
	
    public void setBeganOn(Date beganOn) {
        this.beganOn = beganOn;
     }

	public Date getBeganOn() {
        return beganOn;
     }
     	
    public void setEndedOn(Date endedOn) {
        this.endedOn = endedOn;
     }

     public Date getEndedOn() {
        return endedOn;
     }
     	
    public void setSymptomOrObservation(String symptomOrObservation) {
        this.symptomOrObservation = symptomOrObservation;
     }

     public String getSymptomOrObservation() {
        return symptomOrObservation;
     }
     
     // Constructors 
 	public HealthIssue()
 	{
 		super();
 	}
 	public HealthIssue(Date beganOn, Date endedOn, String symptomOrObservation)
 	{
 		super();
 		this.beganOn = beganOn;
 		this.endedOn = endedOn;
 		this.symptomOrObservation = symptomOrObservation;
 	}
}
