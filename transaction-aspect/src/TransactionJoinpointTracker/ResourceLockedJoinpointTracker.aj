/**
 * 
 */
package TransactionJoinpointTracker;
import javax.transaction.xa.XAResource;

import umjdt.concepts.Transaction;
import umjdt.joinpoints.DelistResourceEventJP;
import umjdt.joinpoints.EnlistResourceEventJP;

/**
 * @author AnasAlsubh
 *
 */
public abstract aspect ResourceLockedJoinpointTracker extends TransactionTracker
{
	protected DelistResourceEventJP delistResourceEventJp=null;
	protected EnlistResourceEventJP enlistResourceEventJp=null;

	void around(Transaction _transaction, XAResource _resource): LockTransaction(_transaction, _resource)
	{
		proceed(_transaction, _resource);
		enlistResourceEventJp= new EnlistResourceEventJP();
		
		LockResourceJoinPoint(enlistResourceEventJp);
	}
	
	void around (Transaction _transaction, XAResource _resource): UnlockTransaction(_transaction, _resource)
	{
		proceed(_transaction, _resource);
		delistResourceEventJp = new DelistResourceEventJP();
		UnlockResourceJoinPoint(delistResourceEventJp);
	}
	
}
