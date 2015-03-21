package ResourceManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.*;

import configs.BusinessRules;
import Resource.*;


public class ReservationRM 
{
	private Reservation reservation;
	private int availableHotelRooms;
	private int availableCars;
	private int availableSeats;
	private int availableHotelSuits;
	
	private int totalCost=0;


	//
	CarRM carRm= new CarRM();
	FlightRM flightRm =new FlightRM();
	HotelRM hotelRm= new HotelRM();
	//
	
	private int dollar_pre_room;
	private int dollar_per_suite;
	private int dollar_per_car;
	private int dollar_per_passenger;
	
	private void setupVariables() 
	{
		setAvailableHotelRooms(BusinessRules.MAX_NUMBER_OF_AVAILABLE_ROOMS);
		setAvailableHotelSuits(BusinessRules.MAX_NUMBER_OF_AVAILABLE_SUITES);
		setAvailableCars(BusinessRules.QUANTITY_OF_AVAILABLE_CARS);
		setAvailableSeats(BusinessRules.MAX_NUMBER_OF_AVAILABLE_SEATS);
	}
	
	public ReservationRM()
	{
		 setupVariables();
	}
	
	public ReservationRM(Reservation reservation)
	{
		setupVariables();
		reservation = new Reservation();
		this.reservation=reservation;
		
	}
	
	public void calculateTotalCost(int reservationID, int customerId, String airline,int flightNumber, int HotelId, int carId,
			int numberOfReservedRooms, int numberOfReservedSuites, int numberOfReservedCars, int numberOfreservedSeats,
			int numNightsPerRooms, int numberNightPerSuites, int numDaysPerCars, int numSeats)
	{
		getRoomAndSuitesUnitPrice(HotelId);
		getCarUnitPrice(carId);
		getPassengerUnitPrice(airline, flightNumber);
		
		int costs=
				(numberOfReservedRooms*numNightsPerRooms*getDollar_pre_room()) + 
				(numberOfReservedSuites*numberNightPerSuites* getDollar_per_suite()) +
				(numberOfReservedCars*numDaysPerCars*getDollar_per_car())+ 
				(numberOfreservedSeats*numSeats*getDollar_per_passenger());
				
		
		
		setTotalCost(costs);
	}
	
	public void getRoomAndSuitesUnitPrice(int hotelId)
	{
		Hotel hotel= new Hotel();
		hotel =hotelRm.LoadHotel(hotelId);
		setDollar_pre_room(hotel.getUnitPriceOfRoom());
		setDollar_per_suite(hotel.getUnitpriceOfSuit());
	}
	
	public void getCarUnitPrice(int carId)
	{
		Car car= new Car();
		car =carRm.loadCar(carId);
		setDollar_per_car(car.getRentalprice());
	}
	
	public void getPassengerUnitPrice(String airline, int flightId)
	{
		Flight flight= new Flight();
		flight =flightRm.load(airline, flightId);
		setDollar_per_passenger(flight.getPrice());
	}
	
	
	// add reservation
	public void save(Reservation reservation)
	{
		
	}
	public void save(int reservationID, int customerId,String airline, int flightNumber, int HotelId, int carId,
			int numberOfReservedRooms, int numberOfReservedSuites, int numberOfReservedCars, int numberOfreservedSeats,
			int numNightsPerRooms, int numberNightPerSuites, int numDaysPerCars, int numSeats)
	
	{
		// check the availability of resources
		// invoke the calculated cost of reservation ==> Insert totalCostfReservation
		//insert
		// update availability of other resources (flight, rooms of hotel, and cars)
	}
	
	// delete reservation
	public void delete (int reservationID)
	{
		
	}
	public void deleteAll()
	{
		//delete all reservations from the reservations resource
	}
	
	//update reservation
	public void update(int reservationID, int customerId, int flightNumber, int HotelId, int carId,
			int numberOfReservedRooms, int numberOfReservedSuites, int numberOfReservedCars, int numberOfreservedSeats,
			int numNightsPerRooms, int numberNightPerSuites, int numDaysPerCars, int numSeats)
	{
		// select the reservation from data source
		// check the availability of resources
		// recalculate the total cost of reservation
		// modify the reservations elements
		// update availability of other resources (flight, rooms of hotel, and cars)
	}
	
	//Load reservation/s
	public List<Reservation> loadAllReservations()
	{
		List<Reservation> reservations=new ArrayList<Reservation>();
		// Select (read) query to return 
		return reservations;
	}
	
	public int getReservationCount()
	{
		int result=0;
		List<Reservation> reservations=new ArrayList<Reservation>();
		reservations=loadAllReservations();
		result=reservations.size();
		return result;
	}
    public Reservation searchMatching (int ID)
	{
		Reservation result= new Reservation();
		// Select the flight and return flight object
		return result;
	}
   
    public List searchMatching (int customerId, int flightNumber, int HotelId, int carId)
	{
		List result= new ArrayList();
		// Select the flight and return flight object
		return result;
	}
    
	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public int getAvailableHotelRooms() {
		return availableHotelRooms;
	}

	public void setAvailableHotelRooms(int availableHotelRooms) {
		this.availableHotelRooms = availableHotelRooms;
	}

	public int getAvailableCars() {
		return availableCars;
	}

	public void setAvailableCars(int availableCars) {
		this.availableCars = availableCars;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public int getAvailableHotelSuits() {
		return availableHotelSuits;
	}

	public void setAvailableHotelSuits(int availableHotelSuits) {
		this.availableHotelSuits = availableHotelSuits;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public int getDollar_pre_room() {
		return dollar_pre_room;
	}

	public void setDollar_pre_room(int dollar_pre_room) {
		this.dollar_pre_room = dollar_pre_room;
	}

	public int getDollar_per_suite() {
		return dollar_per_suite;
	}

	public void setDollar_per_suite(int dollar_per_suite) {
		this.dollar_per_suite = dollar_per_suite;
	}

	public int getDollar_per_car() {
		return dollar_per_car;
	}

	public void setDollar_per_car(int dollar_per_car) {
		this.dollar_per_car = dollar_per_car;
	}

	public int getDollar_per_passenger() {
		return dollar_per_passenger;
	}

	public void setDollar_per_passenger(int dollar_per_flight) {
		this.dollar_per_passenger = dollar_per_flight;
	}

}
