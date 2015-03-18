/**
 * 
 */
package TransactionJoinpointTracker;

import javax.transaction.Status;
import javax.transaction.TransactionManager;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import umjdt.concepts.TID;
import umjdt.concepts.Transaction;
import umjdt.joinpoints.AbortEventJP;
import umjdt.joinpoints.BeginEventJP;
import umjdt.joinpoints.CommitEventJP;
import umjdt.joinpoints.DelistResourceEventJP;
import umjdt.joinpoints.EndEventJP;
import umjdt.joinpoints.EnlistResourceEventJP;
import umjdt.joinpoints.lock.LockingJP;
import umjdt.joinpoints.transaction.InnerTransactionJP;
import umjdt.joinpoints.transaction.OuterTransactionJP;


/**
 * @author AnasAlsubh
 *
 */
public abstract aspect TransactionTracker 
{
	private Logger logger = Logger.getLogger(TransactionTracker.class);
	
	protected BeginEventJP begin_Joinpoint=null;
	protected EndEventJP end_Jopinpoint=null;
	protected CommitEventJP commit_Joinpoint=null;
	protected AbortEventJP abort_Joinpoint=null;
	protected DelistResourceEventJP delistResource_Joinpoint=null;
	protected EnlistResourceEventJP enlistResource_Joinpoint=null;
	
	/**
	 * Begin: Create a new transaction and associate it with the current thread.
	 * @param before begin:  timeout , _supportSubtransactions
	 * after begin : TransactionID, TransactionThread
	 * 
	 * //arjuna.ats.jta.transaction.Transaction+
	 * //ats.internal.jta.transaction.arjunacore.BaseTransaction.
	 */
	pointcut BeginTransaction(): 
		call(* javax..*Transaction*+.begin(..)) || 
		call(* com.arjuna..BaseTransaction+.begin(..)) ||
		call(* com.arjuna..*Transaction*+.begin(..)); 
	
	/**
	 *Complete the transaction represented by this Transaction object.
	 *Complete the transaction associated with the current thread. 
	 *When this method completes, the thread is no longer associated with a transaction. 
	 * @param TransactionID, Status, TransactionThread, parent transaction, transaction manager
	 */
	pointcut CommitTransaction(Transaction _transaction): 
		(execution(* javax..*Transaction*+.commit(..))
				|| (execution(* com.arjuna..BaseTransaction+.commit(..))) 
				|| (execution(* com.arjuna..*Transaction*+.commit(..)))) 
				&& target (_transaction);
	
	pointcut AbortTransaction(Transaction _transaction): 
		(execution(* javax..*Transaction*+.rollback(..)) 
				|| (execution(* com.arjuna..BaseTransaction+.rollback(..)))
				|| (execution(* com.arjuna..*Transaction*+.rollback(..))))
				&& target (_transaction);
	
	/**
	 * Enlist the resource specified with
	 * the transaction associated with the target Transaction object.
	 * @param Xid, Resource, Thread,  
	 */
	pointcut LockTransaction(Transaction transaction , XAResource resource): 
		(execution(* javax..*Transaction*+.enlistResource(..)) || execution(* com.arjuna..*+.lock(..)))
		&& args(resource) && target(transaction); // TxInfo
	
	/**
	 * Disassociate the resource specified from 
	 * the transaction associated with the target Transaction object.
	 * @param : Resource, xid, 
	 */
	pointcut UnlockTransaction(Transaction transaction):
		(execution(* javax..*Transaction*+.delistResource(..)) || execution(* com.arjuna..*+.unlock(..))) 
		&& target(transaction); // TxInfo;
	
    /**Synchronization 
     * The beforeCompletion method is called by the TM prior to the start of the 2PC process. 
     * This call is executed with the transaction context of the transaction that is being committed.
     * @param target (Transaction)
     */
	pointcut BeforeCompletion (): call(* javax..*+.beforeCompletion(..)) && target(Transaction);
	
	/**Synchronization
	 * This method is called by the TM after the transaction is committed or rolled back.
	 * @param status The status of the transaction completion.
	 */
    pointcut AfterCompletion (int state): call(* javax..*+.afterCompletion(..)) && target(Transaction) && args(state);
	
	public void BeginJoinPoint(BeginEventJP _beginJp){}

    public void CommitJoinPoint(EndEventJP _commitJp){}
    
    public void AbortJoinPoint(EndEventJP _abortJp){}
    
    public void LockResourceJoinPoint(EnlistResourceEventJP _enlistResourceJp){}
    
    public void UnlockResourceJoinPoint(DelistResourceEventJP _delistResourceJp){}
    
    public void BeforeCompletion(InnerTransactionJP _innerTransactionJP){}
    public void AfterCompletion(OuterTransactionJP _outerTransactionJP){}
}
