package JavaTransaction;

import java.rmi.*;

/** 
 * Distributed Transaction System in Java
 * 
 * Class: ResourceManagerImpl
 * Description: toy implementation of the RM, for initial testing
 */

public class ResourceManagerImpl 
	extends java.rmi.server.UnicastRemoteObject
    	//ResourceManager

{

	// in this toy, we don't care about location or flight number
	protected int carscounter, roomscounter, roomsprice; 
	protected int carsprice, flightcounter,flightprice;

	protected int XidCounter;

	public static void main(String args[]) {
		System.setSecurityManager(new RMISecurityManager());

		try {
			ResourceManagerImpl obj = new ResourceManagerImpl();
			Naming.rebind("RM", obj);
			System.out.println("RM bound");
		} 
		catch (Exception e) {
			System.out.println("RM not bound:" + e);
		}
	}

	
	public ResourceManagerImpl() throws RemoteException {
		carscounter =0;
		roomscounter =0;
		flightcounter =0;
		flightprice =0;
		carsprice =0;
		roomsprice =0;
		flightprice =0;

		XidCounter =1;

	}


	public int start() throws RemoteException{ // returns Xid
		//System.out.println("Sleeping");
		//try {
		//Thread.sleep(3000);
		//} catch( InterruptedException tie) {
		//}
		//System.out.println("Awakened");
		return (XidCounter++);

	}
	public boolean commit(int transactionID)  
		throws RemoteException, 
		TransactionAbortedException, 
		InvalidTransactionException {
		System.out.println("Committing");
		//throw(new TransactionAbortedException(transactionID, "commit failure"));
		return (true);
	} // returns success
	public void abort(int transactionID) throws RemoteException{
		return;
	}

	// administrative interface
	public boolean addFlight(int Xid, int flightNum, int flightPrice, int flightSeats) 
		throws RemoteException {
		flightcounter += flightSeats;
		flightprice = flightPrice;
		return(true);
	}
	/* deleteflight implies whole deletion of the flight.  
	   all seats, all reservations */
	public boolean deleteFlight(int Xid, int flightNum) throws RemoteException {
		flightcounter =0;
		flightprice =0;
		return(true);
	}
		
	public boolean addRooms(int Xid, String location, int numRooms, int price) 
		throws RemoteException {
		roomscounter += numRooms;
		roomsprice = price;
		return(true);
	}
	public boolean deleteRooms(int Xid, String location, int numRooms) 
		throws RemoteException{
		roomscounter = 0;
		roomsprice = 0;
		return(true);
	}

	public boolean addCars(int Xid, String location, int numCars, int price) 
		throws RemoteException{
		carscounter += numCars;
		carsprice = price;
		return(true);
	}
	public boolean deleteCars(int Xid, String location, int numCars) 
		throws RemoteException{
		carscounter = 0;
		carsprice = 0;
		return(true);
	}

	// technical/testing interface
	public boolean shutdown() throws RemoteException {
		System.exit(1);
		return(true); // haha!  wonder what happens here!
	}
	public boolean selfDestruct(int diskWritesToWait) throws RemoteException {
		return(true);
	}

	// query interface.
	/* queryFlight returns the number of empty seats. */
	public int queryFlight(int Xid, int flightNumber) throws RemoteException
	{ return(flightcounter); }
	public int queryFlightPrice(int Xid, int flightNumber) throws RemoteException
	{ return(flightprice); }
	/* return the number of rooms available at a location */
	public int queryRooms(int Xid, String location) throws RemoteException
	{ return(roomscounter); }
	public int queryRoomsPrice(int Xid, String location) throws RemoteException
	{ return(roomsprice); }
	/* return the number of cars available at a location */
	public int queryCars(int Xid, String location) throws RemoteException
	{ return(carscounter); }
	public int queryCarsPrice(int Xid, String location) throws RemoteException
	{ return(carsprice); }
  /* return a bill */
	public String queryCustomerInfo(int customer) throws RemoteException 
	{return("hoohah!"); }

  // customer functions
  /* new customer just returns a unique customer identifier */
  public int newCustomer(int Xid) throws RemoteException 
	{ return(7); }
	/* deleteCustomer removes the customer and associated reservations */
  public boolean deleteCustomer(int Xid,int customer) throws RemoteException
	{ return(true); }
  public boolean reserveFlight(int Xid, int customer, int flightNumber) 
		throws RemoteException
	{ flightcounter--; return(true); }

  public boolean reserveCar(int Xid, int customer, String location) 
		throws RemoteException
	{ carscounter--; return(true); }
  public boolean reserveRoom(int Xid, int customer, String location) 
		throws RemoteException
	{ roomscounter--; return(true); }

}
