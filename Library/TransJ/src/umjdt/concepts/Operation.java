package umjdt.concepts;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import umjdt.Events.OperationEvent;
import umjdt.Events.TransactionEvent;
import umjdt.util.AccessType;
import umjdt.util.LockType;
import umjdt.util.OperationNumber;

public class Operation implements Cloneable, Serializable{

	private String name = "";
    private OperationNumber operationNr; // keep unique id for all kinds operation in any thread
    private TransId transactionId;
	private AccessType type;
	private Method method;
	private TransactionThread thread; // associate transaction with specific thread
	private List<OperationEvent> operationEvents = new ArrayList<OperationEvent>(); // add the begin and end events 
	
	public Operation(){
		setOperationNr(OperationNumber.Create());
	}
	
	public Operation(TransId _transactionId){
		setOperationNr(OperationNumber.Create(_transactionId));
	}
	
	public Operation(TransId _transactionId, Method _method){
		setOperationNr(OperationNumber.Create(_transactionId));
		setMethod(_method);
	}
		
	public Operation(TransId _transactionId, String _name, AccessType _type)
	{
		setOperationNr(OperationNumber.Create(_transactionId));
		this.setName(_name);
		this.setType(_type);		
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

	public TransId getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(TransId transactionId) {
		this.transactionId = transactionId;
	}

	public List<OperationEvent> getOperationEvents() {
		return operationEvents;
	}

	public void setOperationEvents(List<OperationEvent> operationEvents) {
		this.operationEvents = operationEvents;
	}

	public TransactionThread getThread() {
		return thread;
	}

	public void setThread(TransactionThread thread) {
		this.thread = thread;
	}
	
	public void joinTo(Transaction _transaction){
		// add operation thread to the its transaction thread
		if(this.getThread() == null)
		{
			thread = new TransactionThread();
		}
		_transaction.getMultiOperationMap().put(_transaction.getTransactionThread(), this.getThread());
	}
}