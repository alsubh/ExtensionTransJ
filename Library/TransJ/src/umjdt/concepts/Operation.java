package umjdt.concepts;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import umjdt.Events.OperationEvent;
import umjdt.util.AccessType;
import umjdt.util.LockType;
import umjdt.util.OperationNumber;
import umjdt.util.Timestamp;

public class Operation implements Cloneable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name = "";
    private OperationNumber operationNr; // keep unique id for all kinds operation in any thread
	private AccessType type;
	private Method method;
	private Thread thread;
	private Timestamp timestamp;
	private List<OperationEvent> operationEvents = new ArrayList<OperationEvent>(); // add the begin and end events 
	private Lock lock;
	private LockType lockType;
	private Resource resource; // path of the resource
	
	public Operation()
	{
		this.setOperationNr(OperationNumber.Create());
		setThread(Thread.currentThread());
		setTimestamp(new Timestamp().currentTimeStamp());
	}
	
	public Operation(TransId _transactionId)
	{
		setOperationNr(OperationNumber.Create(_transactionId));
		setThread(Thread.currentThread());
		setTimestamp(new Timestamp().currentTimeStamp());
	}
	
	public Operation(TransId _transactionId, Method _method)
	{
		setOperationNr(OperationNumber.Create(_transactionId));
		setMethod(_method);
		setThread(Thread.currentThread());
		setTimestamp(new Timestamp().currentTimeStamp());
	}
		
	public Operation(TransId _transactionId, short _opNmr)
	{
		setOperationNr(OperationNumber.Create(_transactionId, _opNmr));
		setThread(Thread.currentThread());
		setTimestamp(new Timestamp().currentTimeStamp());
	}

	public Operation(TransId _transactionId, String _name, AccessType _type)
	{
		setOperationNr(OperationNumber.Create(_transactionId));
		this.setName(_name);
		this.setType(_type);
		setThread(Thread.currentThread());
		setTimestamp(new Timestamp().currentTimeStamp());
	}
	
	public String getName(){
		return this.name;
	}

	public AccessType getType() {
		return type;
	}

	public void setType(AccessType _type) {
		this.type = _type;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public OperationNumber getOperationNr() {
		return operationNr;
	}

	public void setOperationNr(OperationNumber operationNr) {
		this.operationNr = operationNr;
	}

	public List<OperationEvent> getOperationEvents() {
		return operationEvents;
	}

	public void setOperationEvents(List<OperationEvent> operationEvents) {
		this.operationEvents = operationEvents;
	}

	public void setThread(TransactionThread thread) {
		this.thread = thread;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public LockType getLockType() {
		return lockType;
	}

	public void setLockType(LockType lockType) {
		this.lockType = lockType;
	}

	public Lock getLock() {
		return lock;
	}

	public void setLock(Lock lock) {
		this.lock = lock;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}
	
	public void enlistOperationTo(Transaction _transaction){
		if(this.getThread() == null)
		{
			setThread(new TransactionThread());
		}
		_transaction.getOperations().add(this);
	}
	
	public boolean addEvent(OperationEvent event)
	{
		if(event !=null)
		{
			operationEvents.add(event);
			return true;
		}
		return false;
	}
	// in the context aspect, the thread of operation will be a list of the transaction thread
}