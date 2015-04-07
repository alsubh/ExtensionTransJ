package Resource;

import java.sql.Date;

public class Customer implements java.io.Serializable
{
	private int customerID; 
	private String firstName="";
	private String lastName="";
	private Date birthDate=Date.valueOf("1/1/1950");
	private String Address="";
	private char gender=' ';
	private String userName;
	private String password;
	
	public Customer(){}
	
	public Customer(int ID, String firstname, String lastName, Date birthDate, String address, char gender)
	{
		this.setCustomerID(ID);
		this.firstName=firstname;
		this.lastName=lastName;
		this.birthDate=birthDate;
		this.Address=address;
		this.gender=gender;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
