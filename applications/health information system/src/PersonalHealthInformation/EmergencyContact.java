/**
 * 
 */
package PersonalHealthInformation;

public class EmergencyContact extends Person{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
// Data member
	private String relationship;
    // Getter and Setter 
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getRelationship() {
		return relationship;
	}
	
	// Constructors
	public EmergencyContact() {
		super();
	}
	public EmergencyContact(Person person,String relationship) {
			super();
			this.relationship = relationship;
	}
}