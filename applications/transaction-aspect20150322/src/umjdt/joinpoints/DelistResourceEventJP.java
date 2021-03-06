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
public class DelistResourceEventJP {

	private Timer timer;
	private Timestamp delistResourceTimestamp;
	private JoinPoint delistResourceJP;
	private TID tid;
	private Resource resource;
	private Transaction transaction;
	private AccessType accesskind;
	private int state;

	public DelistResourceEventJP() {
		super();
		this.delistResourceTimestamp = new Timestamp().currentTimeStamp();
	}

	public DelistResourceEventJP(TID _tid, Resource _resource,
			AccessType lockType) {
		this.delistResourceTimestamp = new Timestamp().currentTimeStamp();
		this.tid = _tid;
		this.resource = _resource;
		this.accesskind = lockType;

	}

	public DelistResourceEventJP(TID _tid, Resource _resource) {
		this.delistResourceTimestamp = new Timestamp().currentTimeStamp();
		this.tid = _tid;
		this.resource = _resource;
	}

	public DelistResourceEventJP(TID _tid) {
		this.delistResourceTimestamp = new Timestamp().currentTimeStamp();
		this.tid = _tid;
	}

	public DelistResourceEventJP(Transaction _transaction) {
		this.delistResourceTimestamp = new Timestamp().currentTimeStamp();
		this.transaction = _transaction;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public Timestamp getDelistResourceTimestamp() {
		return delistResourceTimestamp;
	}

	public void setDelistResourceTimestamp(Timestamp delistResourceTimestamp) {
		this.delistResourceTimestamp = delistResourceTimestamp;
	}

	public JoinPoint getDelistResourceJP() {
		return delistResourceJP;
	}

	public void setDelistResourceJP(JoinPoint delistResourceJP) {
		this.delistResourceJP = delistResourceJP;
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
