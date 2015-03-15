/**
 * 
 */
package umjdt.joinpoints;

import java.util.Timer;

import umjdt.concepts.Resource;
import umjdt.concepts.TID;
import umjdt.concepts.Transaction;
import umjdt.util.AccessType;
import umjdt.util.Timestamp;

/**
 * @author AnasAlsubh
 *
 */
public class DelistResourceEventJP extends LockingJP
{
	private Timer timer;
	private Timestamp delistResourceTimestamp;
	
	public DelistResourceEventJP() 
	{
		super();
		this.delistResourceTimestamp = new Timestamp().currentTimeStamp();
	}

	public DelistResourceEventJP(TID _tid, Resource resource, AccessType lockType) 
	{
		super(_tid, resource, lockType);
		this.delistResourceTimestamp = new Timestamp().currentTimeStamp();
	}

	public DelistResourceEventJP(TID _tid, Resource _resource) 
	{
		super(_tid, _resource);
		this.delistResourceTimestamp = new Timestamp().currentTimeStamp();
	}

	public DelistResourceEventJP(TID _tid) 
	{
		super(_tid);
		this.delistResourceTimestamp = new Timestamp().currentTimeStamp();
	}

	public DelistResourceEventJP(Transaction _transaction) 
	{
		super(_transaction);
		this.delistResourceTimestamp = new Timestamp().currentTimeStamp();
	}

	public DelistResourceEventJP(TransJP _transjp) 
	{
		super(_transjp);
		this.delistResourceTimestamp = new Timestamp().currentTimeStamp();
	}
	
	public Timer getTimer() 
	{
		return timer;
	}

	public void setTimer(Timer timer) 
	{
		this.timer = timer;
	}
	
	public Timestamp getDelistResourceTimestamp() 
	{
		return delistResourceTimestamp;
	}
	
	public void setDelistResourceTimestamp(Timestamp delistResourceTimestamp) 
	{
		this.delistResourceTimestamp = delistResourceTimestamp;
	}
}
