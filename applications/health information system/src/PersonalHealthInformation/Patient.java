/**
 * 
 */
package PersonalHealthInformation;

import java.util.*;


public class Patient extends Person
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Data Member
	private int id;
	private String gender;
	private Date birthDate;
	
	private List<Name> patientOtherName =new ArrayList<Name>();
	private List<EmergencyContact> PatientEmergencyContacts=new ArrayList<EmergencyContact>(); 
	private List<Diagnosis> PatientDiagnoses = new ArrayList<Diagnosis>(); 
	private List<Surgery> PatientSurgeries=new ArrayList<Surgery>(); 
	private List<Allergy> PatientAllergies=new ArrayList<Allergy>(); 
	private List<Perscription> PatientPerscriptions=new ArrayList<Perscription>(); 
	private List<HealthIssue> PatientHealthIssues=new ArrayList<HealthIssue>(); 
	private List<Physician> PhysiciansWorksWithPatient=new ArrayList<Physician>();
	
	// Getter and Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Encrypted	
	@PII
	public Date getBirthDate() {
		return birthDate;
	}

	@Encrypted
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<Name> getPatientOtherName() {
		return patientOtherName;
	}

	public List<EmergencyContact> getPatientEmergencyContacts() {
		return PatientEmergencyContacts;
	}

	public List<Diagnosis> getPatientDiagnoses() {
		return PatientDiagnoses;
	}

	public List<Surgery> getPatientSurgeries() {
		return PatientSurgeries;
	}

	public List<Allergy> getPatientAllergies() {
		return PatientAllergies;
	}

	public List<Perscription> getPatientPerscriptions() {
		return PatientPerscriptions;
	}

	public List<HealthIssue> getPatientHealthIssues() {
		return PatientHealthIssues;
	}

	public List<Physician> getPhysiciansWorksWithPatient() {
		return PhysiciansWorksWithPatient;
	}
	
	// Constructors 
	public Patient()
	{
		
	}
	
	public Patient(int id, String gender, Date birthDate)
	{
		super();
		this.id = id;
		this.gender = gender;
		this.birthDate = birthDate;
	}
	
	//Methods
	
	public void addPhysician(Physician physician)
	{
		PhysiciansWorksWithPatient.add(physician);
	}
	
	public void removePhysician (Physician physician)
	{
		PhysiciansWorksWithPatient.remove(physician);
	}
	
	
	public void addEmergencyContact (EmergencyContact contact)
	{
		PatientEmergencyContacts.add(contact);
	}
	
	public void removeEmergencyContact (EmergencyContact contact)
	{
		PatientEmergencyContacts.remove(contact);
	}
	
	public void addHealthIssue (HealthIssue healthIssue)
	{
		PatientHealthIssues.add(healthIssue);
	}
	public void removeHealthIssue (HealthIssue healthIssue)
	{
		PatientHealthIssues.remove(healthIssue);
	}
	
	public void AddAllergy (Allergy allergy)
	{
		PatientAllergies.add(allergy);
	}
	
	public void removeAllergy (Allergy allergy)
	{
		PatientAllergies.remove(allergy);
	}
	
	public void AddPerscription(Perscription perscription)
	{
		PatientPerscriptions.add(perscription);
	}
	
	public void removePerscription(Perscription perscription)
	{
		PatientPerscriptions.remove(perscription);
	}
		
	public void AddDiagnosis (Diagnosis diagnosis)
	{
		PatientDiagnoses.add(diagnosis);
	}
	
	public void removeDiagnosis (Diagnosis diagnosis)
	{
		PatientDiagnoses.remove(diagnosis);
	}
	
	
	public void AddSurgeries(Surgery surgery)
	{
		PatientSurgeries.add(surgery);
	}
	
	public void removeSurgery(Surgery surgery)
	{
		PatientSurgeries.remove(surgery);
	}
	
}
	