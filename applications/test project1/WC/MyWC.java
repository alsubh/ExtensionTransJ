package WC;

import RM.MyRM;
import JavaTransaction.*;
import java.rmi.*;
import Resources.*;

public class MyWC extends java.rmi.server.UnicastRemoteObject implements ClientInterface{

    static ResourceManager theRM;

    public MyWC()throws java.rmi.RemoteException{}

    public int start ()throws java.rmi.RemoteException{
	return -1;
    }
    public void commit (int context)throws java.rmi.RemoteException{
    }
    public void abort (int context)throws java.rmi.RemoteException{
	theRM.abort(context);
    }
    public boolean addFlight(int context, int flight, int flightSeats, int flightPrice)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException
    {
	return theRM.add(context, new ReservableElement(flight),flightSeats,flightPrice);
    }
    public boolean deleteFlight(int context, int flight)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException
    {
				    
	return theRM.delete(context, new ReservableElement(flight));
    }
    public boolean addCars(int context, String location, int numCars, int price)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException
    {
	return theRM.add(context, new ReservableElement(location),numCars,price);
    }
    public boolean deleteCars(int context, String location, int numCars)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException
    {
	ReservableElement e=new ReservableElement(location);
	e.addAvailable(numCars);
	return theRM.delete(context, e);
    }
    public boolean addRooms(int context, String location, int numRooms, int price)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException
    {
	return theRM.add(context, new ReservableElement(location),numRooms,price);
    }
    public boolean deleteRooms(int context, String location, int numRooms)
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException
    {
	ReservableElement e=new ReservableElement(location);
	e.addAvailable(numRooms);
	return theRM.delete(context,e);
    }
    public int queryFlight(int context, int flight)throws RemoteException, 
    TransactionAbortedException,
    InvalidTransactionException
    {
	return theRM.query(context, new ReservableElement(flight));
    }
    public int queryFlightPrice(int context, int flight)throws RemoteException, 
    TransactionAbortedException,
    InvalidTransactionException
    {
	return theRM.queryPrice(context, new ReservableElement(flight));
    }
    public int queryRooms(int context, String location)throws RemoteException, 
    TransactionAbortedException,
    InvalidTransactionException
    {
	return theRM.query(context, new ReservableElement(location));
    }
    public int queryRoomsPrice(int context, String location)throws RemoteException, 
    TransactionAbortedException,
    InvalidTransactionException
    {
	return theRM.queryPrice(context, new ReservableElement(location));
    }
    public int queryCars(int context, String location)throws RemoteException, 
    TransactionAbortedException,
    InvalidTransactionException
    {
	return theRM.query(context, new ReservableElement(location));
    }
    public int queryCarsPrice(int context, String location)throws RemoteException, 
    TransactionAbortedException,
    InvalidTransactionException
    {
	return theRM.queryPrice(context, new ReservableElement(location));
    }
    /**
       Question for Phil: who really does this in the end? Does it need to be in the RM or can he WC just create a GUID?
    */

    public int newCustomer(int context)throws RemoteException{
	return -1;
	//return theRM.newCustomer(context);
    }
    public boolean deleteCustomer(int context, int customer)
	throws RemoteException{return false;}
    public boolean reserveFlight(int context, int customer, int flight)throws RemoteException,
    TransactionAbortedException,
    InvalidTransactionException
    {
	return theRM.reserve(context,customer,new ReservableElement(flight));
    }
    public boolean reserveCar(int context, int customer, String location)throws RemoteException,
    TransactionAbortedException,
    InvalidTransactionException
    {
	return theRM.reserve(context,customer,new ReservableElement(location));
    }
    public boolean reserveRoom(int context, int customer, String location)throws RemoteException,
    TransactionAbortedException,
    InvalidTransactionException
    {
	return theRM.reserve(context,customer,new ReservableElement(location));
    }
    public boolean reserveItinerary( int customer, int [] flights, String location, boolean car, boolean room )throws RemoteException{
	return false;
    }		
    public int queryCustomer(int context, int customer)throws RemoteException{
	return theRM.queryCustomer(context,customer);
    }
    public String queryCustomerInfo(int context, int customer)throws RemoteException,
    TransactionAbortedException,
    InvalidTransactionException{
	return theRM.queryCustomerInfo(context,customer);
    }

    
    public static void main(String [] args ){

	String rm_name="";
	
	if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
	if (args.length<2){
	    rm_name="testRM";
	}
	else 
	    rm_name=args[1];
	try {
	    Object temp=Naming.lookup(rm_name);
	    System.err.println(temp.getClass());
	    theRM = (ResourceManager)Naming.lookup(rm_name);
	} catch (RemoteException e) {
            e.detail.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
	String name ="";	
        if (args.length != 1) {
            System.err.println("Usage: WC.MyWC <name> <RM_name>");
	    name="testWC";
        }
        else name= args[0];
	
        // Bind the WC and begin processing requests.
        try {
            MyWC obj = new MyWC();
            Naming.rebind(name, obj);
	}
        catch (Exception e) {
            System.err.println(name + " not bound:" + e);
        }
	
    }
			    
			    
}
