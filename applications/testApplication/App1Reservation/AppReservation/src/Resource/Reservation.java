package Resource;

public class Reservation implements java.io.Serializable
{
	private int customerId=0;
	private int hotelId=0; 
	private String airline;
	private int flightNumber=0;
	private int carID=0;
	private int reservationID=0;
		
	private int numberOfReservedRooms;
	private int numberOfReservedSuites;
	private int numberOfReservedCars;
	private int numberOfreservedSeats;
	
	private int numNightsPerRooms;
	private int numberNightPerSuites;
	private int numDaysPerCars;
	private int numSeats;
	
	public Reservation(){}
	
	public Reservation(int reservationID, int customerID,int  hotelID, String airline,int flightNumber, int carID,
			int numberOfReservedRooms, int numberOfReservedSuites, int numberOfReservedCars, int numberOfreservedSeats,
					int numNightsPerRooms, int numberNightPerSuites, int numDaysPerCars, int numSeats)
	{
		setReservationID(reservationID);
		setCustomerId(customerID);
		setHotelId(hotelID);
		setFlightNumber(flightNumber);
		setAirline(airline);
		setCarID(carID);
	}

	public int getReservationID() {
		return reservationID;
	}

	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}

	public int getNumberOfReservedRooms() {
		return numberOfReservedRooms;
	}

	public void setNumberOfReservedRooms(int numberOfReservedRooms) {
		this.numberOfReservedRooms = numberOfReservedRooms;
	}

	public int getNumberOfReservedSuites() {
		return numberOfReservedSuites;
	}

	public void setNumberOfReservedSuites(int numberOfReservedSuites) {
		this.numberOfReservedSuites = numberOfReservedSuites;
	}

	public int getNumberOfReservedCars() {
		return numberOfReservedCars;
	}

	public void setNumberOfReservedCars(int numberOfReservedCars) {
		this.numberOfReservedCars = numberOfReservedCars;
	}

	public int getNumberOfreservedSeats() {
		return numberOfreservedSeats;
	}

	public void setNumberOfreservedSeats(int numberOfreservedSeats) {
		this.numberOfreservedSeats = numberOfreservedSeats;
	}

	public int getNumNightsPerRooms() {
		return numNightsPerRooms;
	}

	public void setNumNightsPerRooms(int numNightsPerRooms) {
		this.numNightsPerRooms = numNightsPerRooms;
	}

	public int getNumberNightPerSuites() {
		return numberNightPerSuites;
	}

	public void setNumberNightPerSuites(int numberNightPerSuites) {
		this.numberNightPerSuites = numberNightPerSuites;
	}

	public int getNumDaysPerCars() {
		return numDaysPerCars;
	}

	public void setNumDaysPerCars(int numDaysPerCars) {
		this.numDaysPerCars = numDaysPerCars;
	}

	public int getNumSeats() {
		return numSeats;
	}

	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}
}
