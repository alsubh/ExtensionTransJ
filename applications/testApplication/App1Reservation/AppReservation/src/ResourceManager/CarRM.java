package ResourceManager;


import java.sql.SQLException;
import java.util.*;

import configs.BusinessRules;

import Resource.Car;

public class CarRM
{
	Car car;
	int quantity=BusinessRules.QUANTITY_OF_AVAILABLE_CARS;
	
	
	public CarRM(){}
	
	public CarRM(Car car)
	{
		this.car=new Car();
		this.car=car;
	}
	
	
	public void save(Car car) throws SQLException
	{
		// add flight to the data flight source 
	}
	
	public void save(String make,String model,int year, int rentalPrice, int numOfPassengers) throws SQLException
	{
		//add flight to the data flight source
	}
	
	public List<Car> LoadAllCars()
	{
		List<Car> cars=new ArrayList<Car>();
		// Select (read) query to return 
		return cars;
	}
	public Car loadCar(int carId)
	{
		Car result =new Car();
		//
		return result;
	}
	
	public void delete(String make,String model,int year)throws SQLException
	{
		
	}
	
	public void delete(String make,String model,int year, int rentalPrice, int numOfPassengers)
	{
		
	}
	
	private void delete(Car car)throws SQLException
	{
		
	}
	
	public int countAll()throws SQLException
	{
		int result=0;
		return result;
	}
	
    public List searchMatching(Car car) throws SQLException
    {
		List cars=new ArrayList();
		// Select (read) query to return 
		return cars;
    }
    
    public void UpdateRentalprice(String make,String model,int year, int rentalPrice)
    {
    	
    }
    
    public void UpdateNumberOfPassengers(String make,String model,int year, int numOfPassengers)
    {
    	
    }
	
}
