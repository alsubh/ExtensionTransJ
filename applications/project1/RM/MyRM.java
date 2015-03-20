package RM;

import java.rmi.*;
import java.util.ArrayList;
import java.util.Hashtable;

import JavaTransaction.*;
import Resources.*;
import LockManager.*;

 public class MyRM
    extends java.rmi.server.UnicastRemoteObject
    implements ResourceManager{

    Hashtable items;
    Hashtable reservations;
     
     public MyRM()throws java.rmi.RemoteException{
	 System.out.println("Comstructor ");
	 items=new Hashtable();
	 reservations=new Hashtable();
     }

      
    
     public int start()
     {return 0;
     }
    public boolean commit( int context )
    {
      //transactionManager.Commit( context );
	return true;
    }
    public void abort(int  context ){
	
    }
    public boolean add(int context,ReservableElement i, int amount, int price)
    {
	System.out.println("Adding "+i);
	ReservableElement ii=(ReservableElement)items.get(i.getKey());
	
	if (ii!=null){
	    System.out.println(" ADDING "+i);
	    ii.addAvailable(amount,price);
	}
	else {
	    System.out.println("RM: Add: item is null "+i);
	    ReservableElement item=new ReservableElement();
	    i.copy(item);
	    item.addAvailable(amount,price);
	    items.put(i.getKey(),item);
	}
	
	return true;
    }

   
     public boolean delete(int context, ReservableElement i)
    {
	if(items.remove(i.getName())==null) return false;
	return true;
    } 
    public void shutdown(int diskWritesToWait)
    {}
    public void SelfDestruct(int diskWritesToWait)
    {
    }
     public int queryPrice(int context, ReservableElement i ){
	 return ((ReservableElement)items.get(i.getName())).getPrice();
     }
     public int query(int context, ReservableElement i )
     {
	 System.out.println(" Query "+i);
	 ReservableElement ii = (ReservableElement)items.get(i.getName());
	if( ii == null )
	    return 0;
	else
	    return ii.getAvailable();
    }

    
    public int newCustomer(int Xid)
	throws RemoteException, InvalidTransactionException, TransactionAbortedException
    {
	return 0;
    }
     public int queryCustomer(int context,int customer) {
	 return 0;
     }
    public String queryCustomerInfo( int context, int c)
	throws InvalidTransactionException
    {
      String order;
      order = "";
      if (reservations.get(new Integer(c))==null)
	  return order;
      Reservation r = (Reservation) reservations.get(new Integer(c));
      ArrayList reserved=r.Items;
      System.out.println(" Query Cust "+reserved.size());
      for (int i=0;i<reserved.size();i++){
	  if( order.length() > 0 )
	      order += ",";
	 
	  order += ((ReservableElement)reserved.get(i)).getName();
      }
      return order;
    }
	
     /**
     * Reserve an item for a customer.
     *
     * @param xid Transaction ID (obtained via start)
     * @param customer ID of customer for reservation
     * @param location Name of location for reservation
     * @return success
     * @throws RemoteException RMI failure
     * @throws TransactionAbortedException Transaction was previously aborted
     * @throws InvalidTransactionException Invalid xid
     * @see #addCars
     * @see #deleteCars
     * @see #queryCars
     * @see #queryCarsPrice
     */
    public boolean reserve(int Xid, int customer,ReservableElement i) throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException
     {
	 ReservableElement e=(ReservableElement)items.get(i.getName());
	 if( e == null)
	     throw new InvalidTransactionException(Xid,"Item does not exists"+ i.getName());
	 int amt=i.getAvailable()<=0?1:i.getAvailable();
	 if( e.getAvailable() > amt)
	{
	    Reservation r = (Reservation)reservations.get(new Integer(customer));
	    if( r == null ) 
		{
		    r = new Reservation( customer, i, e.getPrice() );
		    reservations.put(new Integer(customer), r );					
		}
	    else 
		r.Items.add(i);
	    e.reduceAvailable(amt);
	    return true;
	}
      else
	throw new InvalidTransactionException(Xid, "No more items available" );
    
	     }

     public void dropReservations(int context, int c ){
	 Reservation r = (Reservation) reservations.get(new Integer(c));
	 ArrayList reserved=r.Items;	
	 for (int i=0;i<reserved.size();i++)
	     ((ReservableElement)items.get(reserved.get(i))).addAvailable(1);
	 reservations.remove(new Integer(c));
	     
     }

     public boolean shutdown(){return true;}
     public boolean selfDestruct(int diskWritesToWait) {
	 return true;
     }
     
    public static void main(String args[]) {
        System.setSecurityManager(new RMISecurityManager());
	String name ="";	
        if (args.length != 1) {
            System.err.println("Usage: RM.MyRM <name>");
	    // System.exit(-1);
	    name="testRM";
        }
        else name= args[0];
	
        // Bind the RM and begin processing requests.
        try {
            ResourceManager obj = new MyRM();
            Naming.rebind(name, obj);
	}
        catch (Exception e) {
            System.err.println(name + " not bound:" + e);
        }
	System.out.println(" MAIN DONE ");
    }


   
}

 class Reservation
    {
      public int customer;
	//an array list of ReservableElement for that reservation
      public ArrayList Items;
	public Reservation(int c,ReservableElement i, int price )
	{
	    this.customer = c;
	    Items = new ArrayList();
	    Items.add(i);
	}
    }
