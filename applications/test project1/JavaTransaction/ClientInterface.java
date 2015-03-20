package JavaTransaction;

import java.rmi.*;
/**
 * An interface for interaction with the clients. 
 * <p>This interface is already implemented by class MyWC in package WC.
 * <p>Class MyWC will eventually be transformed into a real workflow controller.
 * Currently, class MyWC only redirects calls to MyRM: the implementation of 
 * the resource manager. If you wish that clients interact directly with your
 * resource manager, make sure that your Resource Manager class implements 
 * this interface
 */ 

public interface ClientInterface extends Remote {
    
    /**
     * Start a transaction
     * return a unique transaction ID
     * @return unique transaction ID
     */
    int start()throws java.rmi.RemoteException;
    /**
     * Commit a transaction
     * @param the transaction ID
     */
    void commit(int context)throws java.rmi.RemoteException;
    /**
     * Abort a transaction
     * @param the transaction ID
     */
    void abort (int context)throws java.rmi.RemoteException;
    /**
     * Add seats to a flight
     * This method will be used to create a new flight but 
     * if the flight already exists, seats will be added 
     * and the price overwritten
     * @param context: the transaction ID
     * @param flight: a flight number
     * @param flightSeats: the number of  flight Seats
     * @param flightSeats: the Seats
     * @return success
     */
    boolean addFlight(int context, int flight, int flightSeats, int flightPrice)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException; 
    /**
     * Delete the entire flight. 
     * deleteFlight implies whole deletion of the flight.
     * all seats, all reservations. It's undecided what will happen if a 
     * customer has a reservation on this flight, but one possibility is 
     * to delete the customer as well. 
     * The other possibility is to return failure.
     * @param context: the transaction ID
     * @param flight: the flight number
     * @return success
     */
    boolean deleteFlight(int context, int flight)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException; 
    /**
     * Add cars to a location. 
     * This should look a lot like addFlight,
     * only keyed on a string location instead of a flight number.
     * @param context: the transaction ID
     * @param location: the location to add cars
     * @param numCars: number of rooms to add
     * @param price: room price
     * @return success
     */
    boolean addCars(int context, String location, int numCars, int price)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException; 
    /**
     * Delete cars. 
     * @param context: the transaction ID
     * @param location: the location to add cars
     * @param numCars: the number to delete
     * @return success
     */
    boolean deleteCars(int context, String location, int numCars)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException; 
    /**
     * Add rooms to a location. 
     * This should look a lot like addFlight,
     * only keyed on a string location instead of a flight number.
     * @param context: the transaction ID
     * @param location: the location to add cars
     * @param numRooms: number of rooms to add
     * @param price: room price
     * @return success
     */
    boolean addRooms(int context, String location, int numRooms, int price)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException; 
    /**
     * Delete rooms. 
     * @param context: the transaction ID
     * @param location: the location to add cars
     * @param numRooms: the number to delete
     *@return success
     */
    boolean deleteRooms(int context, String location, int numRooms)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException; 
    /**
     * Get the number of seats available. 
     * return the number of seats available
     * @param context: the transaction ID
     * @param flight: the flight number
     * @return the number of seats available
     */
    int queryFlight(int context, int flight)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException; 
    /**
     * Get the flight price. 
     * return the price
     * @param context: the transaction ID
     * @param flight: the flight number
     * @return the price
     */
    int queryFlightPrice(int context, int flight)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException; 
    /**
     * Get the number of rooms available. 
     * return the number of rooms available
     * @param context: the transaction ID
     * @param location: the rooms location
     * @return the number of rooms available
     */
    int queryRooms(int context, String location)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException; 
    /**
     * Get the room price. 
     * @param context: the transaction ID
     * @param flight: the rooms location
     * @return the price
     */
    int queryRoomsPrice(int context, String location)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException; 
    /**
     * Get the number of cars available. 
     * @param context: the transaction ID
     * @param location: the cars location
     * @return the number of cars available
     */
    int queryCars(int context, String location)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException; 
    /**
     * Get the cars  price. 
     * @param context: the transaction ID
     * @param location: the cars location
     * @return the price
     */
    int queryCarsPrice(int context, String location)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException; 
    /**
     * Create a customer 
     * return a unique customer ID
     * @param context: the transaction ID
     * @return a unique customer ID
     */
    int newCustomer(int context)throws RemoteException;
    /**
     * Delete a customer 
     * @param context: the transaction ID
     * @param customer: the customer ID
     * @return success
     */
    boolean deleteCustomer(int context, int customer)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException; 
    /**
     * Reserve a seat on a flight
     * @param context: the transaction ID
     * @param customer: the customer ID
     * @param flight: the flight number
     * @return success
     */
    boolean reserveFlight(int context, int customer, int flight)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException; 
    /**
     * Reserve a car
     * @param context: the transaction ID
     * @param customer: the customer ID
     * @param location: cars location
     * @return success
     */
    boolean reserveCar(int context, int customer, String location)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException; 

    /**
     * Reserve a room
     * @param context: the transaction ID
     * @param customer: the customer ID
     * @param location: room location
     * @return success
     */
    boolean reserveRoom(int context, int customer, String location)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException;
    
    
    /**
     * Reserve an itinerary
     * @param customer: the customer ID
     * @param flights: an int array of flight numbers
     * @param location: room location
     * @param bCar: true if car reservation is needed
     * @param bRoom:  true if a room reservation is needed
     * @return success
     */
    boolean reserveItinerary( int customer, int[] flights, String location, boolean bCar, boolean bRoom )
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException; 
    /**
     * Get the total amount of money the customer owes
     * return total price of all reservations
     * @param context: the transaction ID
     * @param customer: the customer ID
     * @return total price of reservations
     */
    int queryCustomer(int context, int customer)throws RemoteException;
    
    /**
     * Get the bill for the customer
     * return a string representation of reservations
     * @param context: the transaction ID
     * @param customer: the customer ID
     * @return a string representation of reservations
     */
    String queryCustomerInfo(int context, int customer)throws RemoteException, 
    TransactionAbortedException,
    InvalidTransactionException;
}

