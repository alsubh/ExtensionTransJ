package Resource;

import java.util.List;

public class Hotel implements java.io.Serializable
{
	  private int hotelId;
	  private String hotelName;
	  private String address;
	  private String state;
	  private String city; 
	  private int numberOfRooms;
	  private int numberOfLevels;
	  private int numberofStars;
	  private int numberOfSuites;
	  private int unitPriceOfRoom;
	  private int unitpriceOfSuit;
	  private String typeOfroom; // single or double
	  
	public Hotel(){}
	
	public Hotel(int hotelId,
	   String hotelName,
	   String address,
	   String state,
	   String city,
	   int numberOfRooms,
	   int numberOfLevels,
	   int numberofStars, int numberOfSuits, int unitPriceOfRoom, int unitpriceOfSuit, String typeOfroom)
	{
		this.hotelId=hotelId;
		this.hotelName=hotelName;
		this.address=address;
		this.state=state;
		this.city=city;
		this.numberOfRooms=numberOfRooms;
		this.numberOfLevels=numberOfLevels;
		this.numberofStars= numberofStars;
		this.numberOfSuites=numberOfSuits;
		this.unitPriceOfRoom=unitPriceOfRoom;
		this.unitpriceOfSuit=unitpriceOfSuit;
		this.typeOfroom=typeOfroom;
	}
	
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getNumberOfRooms() {
		return numberOfRooms;
	}
	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
	public int getNumberOfLevels() {
		return numberOfLevels;
	}
	public void setNumberOfLevels(int numberOfLevels) {
		this.numberOfLevels = numberOfLevels;
	}
	public int getNumberofStars() {
		return numberofStars;
	}
	public void setNumberofStars(int numberofStars) {
		this.numberofStars = numberofStars;
	}
	
	  public int getNumberOfSuits() {
		return numberOfSuites;
	}

	public void setNumberOfSuits(int numberOfSuits) {
		this.numberOfSuites = numberOfSuits;
	}

	public int getUnitPriceOfRoom() {
		return unitPriceOfRoom;
	}

	public void setUnitPriceOfRoom(int unitPriceOfRoom) {
		this.unitPriceOfRoom = unitPriceOfRoom;
	}

	public int getUnitpriceOfSuit() {
		return unitpriceOfSuit;
	}

	public void setUnitpriceOfSuit(int unitpriceOfSuit) {
		this.unitpriceOfSuit = unitpriceOfSuit;
	}

	public String getTypeOfroom() {
		return typeOfroom;
	}

	public void setTypeOfroom(String typeOfroom) {
		this.typeOfroom = typeOfroom;
	}
}
