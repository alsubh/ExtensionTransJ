/**
 * 
 */
package TransactionJoinpointTracker;

import javax.transaction.Status;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import umjdt.joinpoints.AbortEventJP;
import umjdt.joinpoints.BeginEventJP;
import umjdt.joinpoints.CommitEventJP;
import umjdt.joinpoints.DelistResourceEventJP;
import umjdt.joinpoints.EndEventJP;
import umjdt.joinpoints.EnlistResourceEventJP;
import umjdt.joinpoints.lock.LockingJP;


/**
 * @author AnasAlsubh
 *
 */
public aspect TransactionTracker 
{
	private Logger logger = Logger.getLogger(TransactionTracker.class);
	protected BeginEventJP beginEventJp=null;
	protected EndEventJP endEventJp=null;
	protected CommitEventJP commitJp=null;
	protected AbortEventJP abortJp=null;
	protected DelistResourceEventJP delistResourceEventJp=null;
	protected EnlistResourceEventJP enlistResourceEventJp=null;
	
	/**
	 * Begin: Create a new transaction and associate it with the current thread.
	 * @param before begin:  timeout , _supportSubtransactions
	 * after begin : TransactionID, TransactionThread
	 */
	pointcut BeginTransaction(int timeout): 
		(execution(* javax..*Transaction*+.begin(..)) 
				|| (execution(* com.arjuna..BaseTransaction+.begin(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (execution(* com.arjuna..Transaction+.begin(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& args(timeout); 
	
	/**
	 *Complete the transaction represented by this Transaction object.
	 *Complete the transaction associated with the current thread. 
	 *When this method completes, the thread is no longer associated with a transaction. 
	 * @param TransactionID, Status, TransactionThread, parent transaction, transaction manager
	 */
	pointcut CommitTransaction(Transaction transaction, int status, boolean parent, TransactionManager tm ): 
		(execution(* javax..*Transaction*+.commit(..)) 
				|| (execution(* com.arjuna..BaseTransaction+.commit(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (execution(* com.arjuna..Transaction+.commit(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& args(transaction, status, parent, tm,..);
	
	pointcut AbortTransaction(Transaction transaction, int status, boolean parent, TransactionManager tm ): 
		(execution(* javax..*Transaction*+.rollback(..)) 
				|| (execution(* com.arjuna..BaseTransaction+.rollback(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (execution(* com.arjuna..Transaction*+.rollback(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& args(transaction, status, parent, tm,..);
	
	/**
	 * Enlist the resource specified with
	 * the transaction associated with the target Transaction object.
	 * @param Xid, Resource, Thread,  
	 */
	pointcut LockTransaction(Transaction transaction, Xid xid, XAResource resource, int state): 
		(execution(* javax..*Transaction*+.enlistResource(..))
				|| execution(* com.arjuna..*+.lock(..)))
				&& args(xid, resource, state, ..) && target(transaction); // TxInfo
	
	/**
	 * Disassociate the resource specified from 
	 * the transaction associated with the target Transaction object.
	 * @param : Resource, xid, 
	 */
	pointcut UnlockTransaction(Transaction transaction, Xid xid, XAResource resource, int state):
		(execution(* javax..*Transaction*+.delistResource(..))
				|| execution(* com.arjuna..*+.unlock(..)))
				&& args(xid, resource, state, ..) && target(transaction); // TxInfo;
	
	
	
	
    public void BeginJoinPoint(BeginEventJP _beginJp)
    {}

    public void CommitJoinPoint(EndEventJP _commitJp)
    {}
    
    public void AbortJoinPoint(EndEventJP _abortJp)
    {}
    
    public void LockJoinPoint(LockingJP _lockJp)
    {}
    
    public void UnlockJoinPoint(LockingJP _unlockJp)
    {}
    
    
    before(int timeout) : BeginTransaction(timeout)
    {
    	logger.log(Priority.INFO, "Before begin calling the new transaction");
    	//System.out.println("Before Begin ..........Anas");
    	
    }
}
