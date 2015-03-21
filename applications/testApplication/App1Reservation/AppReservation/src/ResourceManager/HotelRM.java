package ResourceManager;

import java.util.ArrayList;
import java.util.List;
import Resource.Hotel;

public class HotelRM 
{

	Hotel hotel;
	
	public HotelRM(){}
	
	public HotelRM (Hotel hotel)
	{
		hotel=new Hotel();
				
		this.hotel=hotel;
	}
	
	public void save(Hotel hotel)
	{
		 
	}
	
	public void save(int hotelId,
			   String hotelName,
			   String address,
			   String state,
			   String city,
			   int numberOfRooms,
			   int numberOfLevels,
			   int numberofStars, int numberOfSuits, int unitPriceOfRoom, int unitpriceOfSuit, String typeOfroom)
	{
		
	}
	
	public List<Hotel> loadAllHotels()
	{
		List<Hotel> hotel=new ArrayList<Hotel>();
		// Select (read) query to return 
		return hotel;
	}
	
	public Hotel LoadHotel (int hotelId)
	{
		Hotel result= new Hotel();
		
		return result;
	}
	
    public List searchMatching (  String hotelName, String address, String state, String city)
	{
		List result= new ArrayList();
		// Select the hotel and return hotel object
		return result;
	}
		
	public void delete(int hotelId,
			   String hotelName)
	{
		
	}
	
	public void delete (String hotelName,
			   String address,
			   String state,
			   String city)
	{
		
	}
	
	public void deleteAll()
	{
		//delete hotel from the hotel resource
	}
	
	public void updateHotelInfo (int hotelId, String hotelName, String address, String state, 
			String city, int numberOfRooms, int numberOfLevels, int numberofStars, 
			int numberOfSuits, int unitPriceOfRoom, int unitpriceOfSuit, String typeOfroom)
	{
		
	}
}
