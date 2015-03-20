package JavaTransaction;

import Resources.*;
import java.rmi.*;

/** 
 * Distributed Transaction System in Java.
 * 
 * failure reporting is done using two pieces, exceptions and boolean 
 * return values.  Exceptions are used for systemy things - like 
 * transactions that were forced to abort, or don't exist.  Return
 * values are used for operations that would affect the consistency
 * of the database, like the deletion of more cars than the database
 * knows about.    
 * If there is a boolean return value and you're not sure how it 
 * would be used in your implementation, ignore it.  I used boolean
 * return values in the interface generously to allow flexibility in 
 * implementation.  But don't forget to return true when the operation
 * has succeeded.
 */

public interface ResourceManager extends Remote {
	/**
	* Start transaction.
	*
	* @return a unique transaction ID.
  */
	public int start() throws RemoteException; // returns Xid
	/**
	* Commit transaction.
	*
	* @return success. 
  */
	public boolean commit(int transactionId) 
		throws RemoteException, 
			TransactionAbortedException,
			InvalidTransactionException; 
	/**
	* Abort transaction.
	*
	* @return nothing, but this may change.
  */
	public void abort(int transactionId) 
	    throws RemoteException;

	public boolean add(int Xid, ReservableElement i, int amount, int price) 
		throws RemoteException, 
			TransactionAbortedException,
			InvalidTransactionException; 
	/**
	* Delete the entire flight.
	*  deleteflight implies whole deletion of the flight.  
	*   all seats, all reservations.  It's undecided what will happen
	*   if a customer has a reservation on this flight, but one possibility
  *   is to delete the customer as well.  The other possibility is to 
  *   return failure.
	*
	* @return success.
  */
	public boolean delete(int Xid, ReservableElement i) 
		throws RemoteException, 
			TransactionAbortedException,
			InvalidTransactionException; 
		
 
	public boolean shutdown() 
		throws RemoteException;
	/** Call exit after a specified number of disk writes.
  * Support for this method requires a wrapper around the system's 
	* write to disk command that decrements the counter set by this method.
	* This counter should default to 0, which implies that the wrapper
	* will do nothing.  If the count is non-zero, the wrapper should decrement
  * the counter, see if it becomes zero, and if so, call exit(), otherwise
  * continue the write.
  *
  * This method is not part of a transaction.
  *
	* @return success
  */
	public boolean selfDestruct(int diskWritesToWait) 
		throws RemoteException;

	// query interface.
	/**
	* queryFlight returns the number of empty seats. 
	*/
	public int query(int Xid, ReservableElement i) 
		throws RemoteException, 
			TransactionAbortedException,
			InvalidTransactionException; 
	public int queryPrice(int Xid, ReservableElement i) 
		throws RemoteException, 
			TransactionAbortedException,
			InvalidTransactionException; 
    
    public int queryCustomer(int context,int customer) 
	throws RemoteException;
  /* return a bill */
	public String queryCustomerInfo(int context,int customer) 
		throws RemoteException, 
			TransactionAbortedException,
			InvalidTransactionException; 

 
    public boolean reserve(int Xid, int customer,ReservableElement i) 
	throws RemoteException, 
	       TransactionAbortedException,
	       InvalidTransactionException; 
    public void dropReservations(int context, int c )throws RemoteException,
							    TransactionAbortedException,
							    InvalidTransactionException; 
}
