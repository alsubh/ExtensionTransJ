/**
 * 
 */
package TransactionJoinpointTracker;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;

import umjdt.concepts.Resource;
import umjdt.concepts.Transaction;
import umjdt.joinpoints.DelistResourceEventJP;
import umjdt.joinpoints.EnlistResourceEventJP;
import umjdt.util.Timestamp;

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
		try 
		{
			enlistResourceEventJp= new EnlistResourceEventJP();
			enlistResourceEventJp.setTransaction(_transaction);
			enlistResourceEventJp.setResource(new Resource(_resource));
			enlistResourceEventJp.setEnlistResourceTimestamp(new Timestamp().currentTimeStamp());
			proceed(_transaction, _resource);
			LockResourceJoinPoint(enlistResourceEventJp);
		} 
		catch (XAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void around (Transaction _transaction, XAResource _resource): UnlockTransaction(_transaction, _resource)
	{
		try
		{
			
			proceed(_transaction, _resource);
			delistResourceEventJp = new DelistResourceEventJP();
			delistResourceEventJp.setTransaction(_transaction);
			delistResourceEventJp.setResource();
			delistResourceEventJp.setDelistResourceTimestamp(new Timestamp().currentTimeStamp());
			UnlockResourceJoinPoint(thisJoinPoint);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
}
