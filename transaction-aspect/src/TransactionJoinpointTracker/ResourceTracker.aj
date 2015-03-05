/**
 * 
 */
package TransactionJoinpointTracker;
import javax.transaction.xa.Xid;

import umjdt.joinpoints.EndResourceEventJP;
import umjdt.joinpoints.StartResourceEventJP;

/**
 * @author AnasAlsubh
 *
 */
public aspect ResourceTracker 
{

	/**
	 * The transaction manager uses the start method to associate the global transaction 
	 * with the resource, and it uses the end method to disassociate the transaction
	 * from the resource.
	 * @param xid, resource, 
	 */
	pointcut StartLockResource(Xid xid, ): 
		execution(* javax..*+.start(..)) && args(_startEventJP);
		//javax.transaction.xa.XAResource+
	/**
	 * The resource manager is responsible for associating the global transaction
	 * to all work performed on its data between the start and end method invocations.
	 * Ends the work performed on behalf of a transaction branch. 
	 * The resource manager disassociates the XA resource from 
	 * the transaction branch specified and lets the transaction complete.
	 * @param 
	 */
	pointcut EndTransaction(EndResourceEventJP _endEventJP): 
		execution(* javax..*+.end(..)) && args(_endEventJP);
		
	/** At transaction commit time, the resource managers are informed by 
	 * the transaction manager to prepare, commit, or rollback 
	 * a transaction according to the two-phase commit protocol.
	 */
	//pointcut CommitResource(CommitResourceEventJP _startEventJP): 
		//execution(* javax.transaction.xa.XAResource+.commit(..)) && args(_startEventJP);
	
	
    public void StartResourceJoinPoint(StartResourceEventJP _startResourceJp)
    {}

    public void EndResourceJoinPoint(EndResourceEventJP _endResourceJp)
    {}
}
