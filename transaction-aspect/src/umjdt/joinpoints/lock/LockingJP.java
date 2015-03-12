package umjdt.joinpoints.lock;

import umjdt.concepts.Operation;
import umjdt.concepts.Resource;
import umjdt.concepts.TID;
import umjdt.concepts.Transaction;
import umjdt.concepts.lock;
import umjdt.joinpoints.EndResourceEventJP;
import umjdt.joinpoints.StartResourceEventJP;
import umjdt.joinpoints.TransJP;
import umjdt.util.AccessType;

public class LockingJP extends TransJP
{
	private TID tid;
	private lock _lock;
	private Resource resource;
	private Operation operation;
	private AccessType lockType;
	private StartResourceEventJP startResourceEventJp;
	private EndResourceEventJP endResourceEventJp;
	
	public LockingJP(TID _tid) 
	{
		super(_tid);
	}

	public LockingJP(Transaction _transaction) 
	{
		super(_transaction);
	}

	public LockingJP(TransJP _transjp) 
	{
		super(_transjp);
	}
	
	public LockingJP(lock _lock, TID _tid, Resource _resource, AccessType _lockType) 
	{
		super();
		this._lock = _lock;
		this.tid = _tid;
		this.resource = _resource;
		this.lockType = _lockType;
	}
	
	public LockingJP(TID _tid, AccessType _lockType, StartResourceEventJP _startResourceEventJp, EndResourceEventJP _endResourceEventJp) 
	{
		super();
		this.tid = _tid;
		this.lockType = _lockType;
		this.startResourceEventJp = _startResourceEventJp;
		this.endResourceEventJp = _endResourceEventJp;
	}

	public lock get_lock() {
		return _lock;
	}

	public void set_lock(lock _lock) {
		this._lock = _lock;
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

	public AccessType getLockType() {
		return lockType;
	}

	public void setLockType(AccessType lockType) {
		this.lockType = lockType;
	}
	public StartResourceEventJP getStartResourceEventJp() {
		return startResourceEventJp;
	}

	public void setStartResourceEventJp(StartResourceEventJP startResourceEventJp) {
		this.startResourceEventJp = startResourceEventJp;
	}

	public EndResourceEventJP getEndResourceEventJp() {
		return endResourceEventJp;
	}

	public void setEndResourceEventJp(EndResourceEventJP endResourceEventJp) {
		this.endResourceEventJp = endResourceEventJp;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}
}
