/**
 * 
 */
package umjdt.joinpoints;

import java.util.Timer;

import org.aspectj.lang.JoinPoint;

import umjdt.concepts.Resource;
import umjdt.concepts.TID;
import umjdt.concepts.Transaction;
import umjdt.util.AccessType;
import umjdt.util.Timestamp;

/**
 * @author AnasAlsubh
 * 
 */
public class EnlistResourceEventJP {

	private Timer timer;
	private Timestamp enlistResourceTimestamp;
	private JoinPoint enlistResourceJP;
	private TID tid;
	private Resource resource;
	private Transaction transaction;
	private AccessType accessKind;
	private int state;

	public EnlistResourceEventJP() {
		enlistResourceTimestamp = new Timestamp().currentTimeStamp();
	}

	public EnlistResourceEventJP(TID _tid) {
		enlistResourceTimestamp = new Timestamp().currentTimeStamp();
		this.tid = _tid;
	}

	public EnlistResourceEventJP(Transaction _transaction) {
		enlistResourceTimestamp = new Timestamp().currentTimeStamp();
		this.setTransaction(_transaction);
	}

	public EnlistResourceEventJP(TID _tid, Resource _resource) {
		enlistResourceTimestamp = new Timestamp().currentTimeStamp();
		this.setTid(_tid);
		this.setResource(_resource);
	}

	public EnlistResourceEventJP(TID _tid, Resource _resource,
			AccessType lockType) {
		enlistResourceTimestamp = new Timestamp().currentTimeStamp();
		this.setTid(_tid);
		this.setResource(_resource);
		this.accessKind = lockType;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public Timestamp getEnlistResourceTimestamp() {
		return enlistResourceTimestamp;
	}

	public void setEnlistResourceTimestamp(Timestamp enlistResourceTimestamp) {
		this.enlistResourceTimestamp = enlistResourceTimestamp;
	}

	public JoinPoint getEnlistResourceJP() {
		return enlistResourceJP;
	}

	public void setEnlistResourceJP(JoinPoint enlistResourceJP) {
		this.enlistResourceJP = enlistResourceJP;
	}

	public TID getTid() {
		return tid;
	}

	public void setTid(TID tid) {
		this.tid = tid;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
