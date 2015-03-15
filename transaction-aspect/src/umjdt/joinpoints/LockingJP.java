/**
 * 
 */
package umjdt.joinpoints;

import javax.annotation.Resources;

import com.arjuna.ats.internal.txoj.LockList;

import umjdt.concepts.Lock;
import umjdt.concepts.Resource;
import umjdt.concepts.TID;
import umjdt.concepts.Transaction;
import umjdt.util.AccessType;

/**
 * @author AnasAlsubh
 *
 */
public class LockingJP extends TransJP
{
	Lock lock =null;
	Resource resource= null;
	AccessType lockType;
	
	public LockingJP() 
	{
		super();
	}
	
	public LockingJP(TID _tid) 
	{
		super(_tid);
	}

	public LockingJP(Transaction _transaction) 
	{
		super(_transaction);
	}
	
	public LockingJP(TID _tid, Resource _resource)
	{
		super();
		super.setTid(_tid);
		this.resource= _resource;
	}
	
	public LockingJP(TID _tid, Resource resource, AccessType lockType) 
	{
		super();
		this.setTid(_tid);
		this.resource = resource;
		this.lockType = lockType;
	}

	public LockingJP(TransJP _transjp) 
	{
		super(_transjp);
	}
	
	public Lock get_lock() 
	{
		return lock;
	}

	public void set_lock(Lock _lock) 
	{
		this.lock = _lock;
	}

	public Resource getResource() 
	{
		return resource;
	}

	public void setResource(Resource resource) 
	{
		this.resource = resource;
	}

	public AccessType getLockType() 
	{
		return lockType;
	}

	public void setLockType(AccessType lockType) 
	{
		this.lockType = lockType;
	}
}
