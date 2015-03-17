/**
 * 
 */
package TransactionJoinpointTracker;

import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import umjdt.concepts.Lock;
import umjdt.joinpoints.AbortResourceEventJP;
import umjdt.joinpoints.CommitResourceEventJP;
import umjdt.joinpoints.EndHoldingResourceEventJP;
import umjdt.joinpoints.StartHoldingResourceEventJP;

/**
 * @author AnasAlsubh
 *
 */
public aspect ResourceHoldingJoinpointTracker
{
	/**
	 * The resource manager is responsible for associating the global transaction
	 * to all work performed on its data between the start and end method invocations.
	 * @param xid: A global transaction identifier to be associated with the resource.
	 * @param target (resource)
	 * @param flags One of TMSUCCESS, TMFAIL, or TMSUSPEND.
	 */
	private StartHoldingResourceEventJP startResourceEventjp= null;
	private EndHoldingResourceEventJP endResourceEventjp =null;
	
	
	/*
	 * Starts work on behalf of a transaction branch specified in xid.
	 * If TMJOIN is specified, the start applies to joining a transaction previously seen by the RM.
	 * @param xid A global transaction identifier to be associated with the resource.
	 * @param flags One of TMNOFLAGS, TMJOIN, or TMRESUME.
	 */
	pointcut StartHoldingResource(Xid xid, XAResource resource): 
		execution(* javax..XAResource+.start(..)) && target(resource) && args(xid);
		//javax.transaction.xa.XAResource+
	
	
	/**
	 * Ends the work performed on behalf of a transaction branch. 
	 * The resource manager disassociates the XA resource from the transaction branch specified and lets the transaction complete.
	 * @param xid
	 * @param resource
	 */
	pointcut EndHoldingResource(Xid xid, XAResource resource): 
		execution(* javax..XAResource+.end(..)) && target(resource) && args(xid);
	

	
	void around(Xid _xid, XAResource _resource): StartHoldingResource(_xid, _resource)
	{
		proceed(_xid, _resource);
		startResourceEventjp = new StartHoldingResourceEventJP();
		StartResourceJoinPoint(startResourceEventjp);
	}
	
	void around(Xid _xid, XAResource _resource): EndHoldingResource(_xid, _resource)
	{
		proceed(_xid, _resource);
		endResourceEventjp = new EndHoldingResourceEventJP();
		EndResourceJoinPoint(endResourceEventjp);
	}
		
	// Hold
    public void StartResourceJoinPoint(StartHoldingResourceEventJP _startResourceJp)
    {}

    // UnHold
    public void EndResourceJoinPoint(EndHoldingResourceEventJP _endResourceJp)
    {}
    
    
    /***
     * Commits the global transaction specified by xid.
     * @param xid A global transaction identifier
     * @pram boolean (onePhase If true, the resource manager should use a one-phase commit protocol to commit the work done on behalf of xid.)
     * @param resource(target)
     */
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
    
    //Defined for methods
	pointcut Lock(): execution(* *+.*lock*(..)) ;
	pointcut Unlock(): execution(* *+.*unlock*(..)) || execution(* *+.*release*(..));
	
	Object around(): Lock(){
		Object lock= proceed();
		endResourceEventjp = new EndHoldingResourceEventJP();
		EndResourceJoinPoint(endResourceEventjp);
		return lock;
	}
	
	void around(): Unlock(){
		proceed();
		endResourceEventjp = new EndHoldingResourceEventJP();
		EndResourceJoinPoint(endResourceEventjp);
	}	
}
