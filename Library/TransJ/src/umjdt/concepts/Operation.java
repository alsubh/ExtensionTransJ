package umjdt.concepts;

import java.lang.reflect.Method;

import umjdt.util.AccessType;
import umjdt.util.LockType;
import umjdt.util.OperationNumber;

public class Operation implements Cloneable{

	private String name = "";
    private OperationNumber operationNr; // keep unique id for all kinds operation in any thread
    private TransId transactionId;
	private AccessType type;
	private Method method;
	private TransactionThread operationThread; // associate transaction with specific thread
		
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

	public TransactionThread getOperationThread() {
		return operationThread;
	}

	public void setOperationThread(TransactionThread operationThread) {
		this.operationThread = operationThread;
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
}