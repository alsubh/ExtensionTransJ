package ResourceManager;


import java.sql.SQLException;
import java.util.*;

import Resource.Car;
import Resource.Flight;


/**
 * this class has many of read and write oparations 
 * @author AnasAlsobeh
 *
 */
public class FlightRM 
{
	Flight flight;
	
	public FlightRM(){}
	
	public FlightRM (Flight flight)
	{
		flight=new Flight();
				
		this.flight=flight;
	}
	
	public void save(Flight flight)
	{
		// add flight to the data flight source 
	}
	
	public void save(String airline, int flightNumber, String destination, int departureTime, String terminal,
			int gateNumber, String stringStatus,
			int price, int numberOfSeats)
	{
		//add flight to the data flight source
	}
	
	public List<Flight> loadAllFlights()
	{
		List<Flight> flights=new ArrayList<Flight>();
		// Select (read) query to return 
		return flights;
	}
	public Flight load(String airline, int flightId)
	{
		Flight result=new Flight();
		//
		return result;
	}
	
    public List searchMatching (String airline, int ID)
	{
		List result= new ArrayList();
		// Select the flight and return flight object
		return result;
	}
	
	//Delays a flight 
	public void delayFlight()
	{
		// update the arrival time and update status of flight
	}
	
	public void cancelFlight()
	{
		// update status of flight
	}
	
	public void delete (String airline, int ID)
	{
		
	}
	public void deleteAll()
	{
		//delete flight from the flight resource
	}
	
	public void changeGate(int Id)
	{
		// update gate in the flight data resource
	}
	
	public void updatePrice(String airline, int ID, int flightPrice)
	{
		
	}
}
