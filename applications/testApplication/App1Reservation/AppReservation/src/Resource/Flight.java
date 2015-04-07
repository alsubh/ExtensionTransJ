package Resource;

import java.sql.Date;
import java.sql.Time;

public class Flight implements java.io.Serializable
{
	private int flightID=0;
	private String airline;
	private String destination;
	private int departureTime;
	private int gateNumber;
	private String terminalName;
	private String status;
	private int flightPrice;	
	private int numberofSeats;
	
	
	public Flight() {}
	
	public Flight(String airline, int Id, String destination, int departureTime, String gateName,String status)
	{
		setAirline(airline);
		setFlightID(Id);
		setDestination(destination);
		setDepartureTime(departureTime);
		setGateNumber(gateNumber);
		setStatus(status);
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(int departureTime) {
		this.departureTime = departureTime;
	}

	public int getGateNumber() {
		return gateNumber;
	}

	public void setGateNumber(int gateNumber) {
		this.gateNumber = gateNumber;
	}

	public java.lang.String getTerminalName() {
		return terminalName;
	}

	public void setTerminalName(java.lang.String terminalName) {
		this.terminalName = terminalName;
	}


	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public int getFlightID() {
		return flightID;
	}

	public void setFlightID(int flightID) {
		this.flightID = flightID;
	}

	public int getPrice() {
		return flightPrice;
	}

	public void setPrice(int price) {
		this.flightPrice = price;
	}

	public int getNumberofSeats() {
		return numberofSeats;
	}

	public void setNumberofSeats(int numberofSeats) {
		this.numberofSeats = numberofSeats;
	}

	
}