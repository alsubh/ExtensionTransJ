/**
 * 
 */
package TransactionJoinpointTracker;

import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import umjdt.concepts.Lock;
import umjdt.joinpoints.AbortResourceEventJP;
import umjdt.joinpoints.CommitResourceEventJP;
import umjdt.joinpoints.EndResourceEventJP;
import umjdt.joinpoints.StartResourceEventJP;

/**
 * @author AnasAlsubh
 *
 */
public aspect LockingJoinpointTracker extends TransactionTracker
{

	/**
	 * The resource manager is responsible for associating the global transaction
	 * to all work performed on its data between the start and end method invocations.
	 * Ends the work performed on behalf of a transaction branch. 
	 * The resource manager disassociates the XA resource from 
	 * the transaction branch specified and lets the transaction complete.
	 * @param xid: A global transaction identifier to be associated with the resource.
	 * @param resource, timeout
	 */
	private StartResourceEventJP startResourceEventjp= null;
	private EndResourceEventJP endResourceEventjp =null;
	
	
	pointcut StartLockResource(Xid xid, XAResource resource): 
		execution(* javax..*+.start(..)) && target(resource) && args(xid);
		//javax.transaction.xa.XAResource+
	
	pointcut EndLockResource(Xid xid, XAResource resource): 
		execution(* javax..*+.end(..)) && target(resource) && args(xid);
	
	pointcut Lock(): execution(* *+.*lock*(..)) ;
	pointcut Unlock(): execution(* *+.*unlock*(..)) || execution(* *+.*release*(..));
	
	
	void around(Xid _xid, XAResource _resource): StartLockResource(_xid, _resource)
	{
		proceed(_xid, _resource);
		startResourceEventjp = new StartResourceEventJP();
		StartResourceJoinPoint(startResourceEventjp);
	}
	
	void around(Xid _xid, XAResource _resource): EndLockResource(_xid, _resource)
	{
		proceed(_xid, _resource);
		endResourceEventjp = new EndResourceEventJP();
		EndResourceJoinPoint(endResourceEventjp);
	}
	
	Object around(): Lock(){
		Object lock= proceed();
		endResourceEventjp = new EndResourceEventJP();
		EndResourceJoinPoint(endResourceEventjp);
		return lock;
	}
	
	void around(): Unlock(){
		proceed();
		endResourceEventjp = new EndResourceEventJP();
		EndResourceJoinPoint(endResourceEventjp);
	}	
	
	// Hold
    public void StartResourceJoinPoint(StartResourceEventJP _startResourceJp)
    {}

    // UnHold
    public void EndResourceJoinPoint(EndResourceEventJP _endResourceJp)
    {}
    
    
    pointcut CommitResource(Xid xid, XAResource resource): 
		execution(* javax.transaction.xa.XAResource+.commit(..)) && args(xid, resource, ..);
    /**
     * Informs the resource manager to roll back work done on 
     * behalf of a transaction branch.
     * @param xid
     * @param resource
     */
    pointcut AbortResource(Xid xid, XAResource resource):
    	execution(* javax.transaction.xa.XAResource+.rollback(..)) && args(xid, resource, ..);

    public void CommitResourceJoinPoint(CommitResourceEventJP _commitResourceJp)
    {}

    public void AbortResourceJoinPoint(AbortResourceEventJP _abortResourceJp)
    {}
}
