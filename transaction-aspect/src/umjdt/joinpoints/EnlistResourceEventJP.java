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
public class EnlistResourceEventJP extends LockingJP {
	private Timer timer;
	private Timestamp enlistResourceTimestamp;

	public EnlistResourceEventJP() {
		super();
		enlistResourceTimestamp = new Timestamp().currentTimeStamp();
	}

	public EnlistResourceEventJP(TID _tid) {
		super(_tid);
		enlistResourceTimestamp = new Timestamp().currentTimeStamp();
	}

	public EnlistResourceEventJP(Transaction _transaction) {
		super(_transaction);
		enlistResourceTimestamp = new Timestamp().currentTimeStamp();
	}

	public EnlistResourceEventJP(TID _tid, Resource _resource) {
		super(_tid, _resource);
		enlistResourceTimestamp = new Timestamp().currentTimeStamp();
	}

	public EnlistResourceEventJP(TID _tid, Resource resource,
			AccessType lockType) {
		super(_tid, resource, lockType);
		enlistResourceTimestamp = new Timestamp().currentTimeStamp();
	}

	public EnlistResourceEventJP(TransJP _transjp) {
		super(_transjp);
		enlistResourceTimestamp = new Timestamp().currentTimeStamp();
	}

	@Override
	public Timer getTimer() {
		return timer;
	}

	@Override
	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public Timestamp getEnlistResourceTimestamp() {
		return enlistResourceTimestamp;
	}

	public void setEnlistResourceTimestamp(Timestamp enlistResourceTimestamp) {
		this.enlistResourceTimestamp = enlistResourceTimestamp;
	}
}
