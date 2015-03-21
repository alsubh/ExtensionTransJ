package ResourceManager;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Resource.Car;
import Resource.Customer;

public class CustomerRM 
{
	Customer customer;
	public CustomerRM(){}
	
	public CustomerRM(Customer customer)
	{
		this.customer=new Customer();
		this.customer=customer;
	}
	
	
	public void save(Customer customer) throws SQLException
	{
		// add flight to the data flight source 
	}
	
	public void save(int ID, String firstname, String lastName, Date birthDate, String address, char gender, String userName, String password)throws SQLException
	{
		//add flight to the data flight source
		//else update if the customer exists
	}
	
	public List<Customer> LoadAllCustomers()
	{
		List<Customer> customers=new ArrayList<Customer>();
		// Select (read) query to return 
		return customers;
	}
	
	public void delete(int Id)throws SQLException
	{
		
	}
	
	public void delete(String firstname, String lastName, String userName, String password)throws SQLException
	{
		
	}
	
	public void delete(String firstname, String lastName, Date birthDate, char gender)throws SQLException
	{
		
	}
	
	private void delete(Customer customer)throws SQLException
	{
		
	}
	
	public int countAll()throws SQLException
	{
		int result=0;
		return result;
	}
	
    public List searchMatching(Customer customer) throws SQLException
    {
		List customers=new ArrayList();
		// Select (read) query to return 
		return customers;
    }
    
    public void update(int Id, String oldUserName, String OldPassword, String newUserName, String newPassword)
    {
    	// check authintication
    	//check the old username and password
    	//select the customer
    	// update information
    }
}
