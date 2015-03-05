/**
 * 
 */
package TransactionJoinpointTracker;

import org.apache.log4j.Logger;

import umjdt.joinpoints.AbortJP;
import umjdt.joinpoints.BeginEventJP;
import umjdt.joinpoints.CommitJP;
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
	
	// timeout , _supportSubtransactions
	pointcut BeginTransaction(int timeout): 
		(execution(* javax..*.*Transaction*+.begin(..)) 
				|| execution(* com.arjuna.ats.jta.transaction.Transaction+.begin(..))) 
				&& args(timeout);
	
	pointcut CommitTransaction(CommitJP _commitJP): 
		execution(* javax..*.Transaction+.commit(..)) && args(_commitJP);
	
	pointcut AbortTransaction(AbortJP _abortJP): 
		execution(* javax..*.Transaction+.rollback(..)) && args(_abortJP);
	
	/**
	 * Enlist the resource specified with
	 * the transaction associated with the target Transaction object.
	 * @param _beginEventJP
	 */
	pointcut LockTransaction(EnlistResourceEventJP _lockJP): 
		execution(* javax..*.Transaction+.enlistResource(..)) && args(_lockJP)
		|| execution(* com.arjuna..*+.lock(..)) && args(_lockJP);
	
	/**
	 * Disassociate the resource specified from 
	 * the transaction associated with the target Transaction object.
	 * @param : Resource, xid, 
	 */
	pointcut UnlockTransaction(DelistResourceEventJP _unlockJP): 
		execution(* javax..*.Transaction+.delistResource(..)) && args(_unlockJP)
		|| execution(* com.arjuna..*+.unlock(..)) && args(_unlockJP);
	
		
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
}
