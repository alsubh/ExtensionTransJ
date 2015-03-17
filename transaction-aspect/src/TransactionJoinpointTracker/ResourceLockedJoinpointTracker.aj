/**
 * 
 */
package TransactionJoinpointTracker;
import java.util.HashMap;

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

	boolean around(Transaction _transaction, XAResource _resource): LockTransaction(_transaction, _resource)
	{
		boolean result = false;
		try 
		{
			result= proceed(_transaction, _resource);
			enlistResourceEventJp= new EnlistResourceEventJP();
			enlistResourceEventJp.setTransaction(_transaction);
			enlistResourceEventJp.setTid(_transaction.getTid());
			enlistResourceEventJp.setResource(new Resource(_resource));
			enlistResourceEventJp.setEnlistResourceTimestamp(new Timestamp().currentTimeStamp());
			enlistResourceEventJp.setEnlistResourceJP(thisJoinPoint);
			LockResourceJoinPoint(enlistResourceEventJp);
			
		} 
		catch (XAException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	boolean around (Transaction _transaction): UnlockTransaction(_transaction)
	{
		boolean result= false;
		try
		{
			
			result= proceed(_transaction);
			delistResourceEventJp = new DelistResourceEventJP();
			delistResourceEventJp.setDelistResourceJP(thisJoinPoint);
			delistResourceEventJp.setTransaction(_transaction);
			Resource resource =(Resource) _transaction.getResources().get(_transaction.getTid().getXid());
			delistResourceEventJp.setDelistResourceTimestamp(new Timestamp().currentTimeStamp());
			UnlockResourceJoinPoint(delistResourceEventJp);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return result;
	}
	
}
