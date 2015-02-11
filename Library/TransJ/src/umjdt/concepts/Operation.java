package umjdt.concepts;

import java.lang.reflect.Method;

import umjdt.util.AccessType;

public class Operation implements Cloneable{

	private String name = "";
	private static int operationId=0;// keep unique id for all kinds operation in any thread
	private AccessType type;
	private Method method;
	// return data 
	private Object data = new Object();
	private Transaction transaction; 
	private TransactionThread transactionThread; 
		
	public Operation(){
		Operation.operationId= operationId+1;
		setOperationId(operationId);
	}
	
	public Operation(Method _method){
		Operation.operationId= operationId+1;
		setOperationId(operationId);
		setMethod(_method);
	}
		
	public Operation(String _name, AccessType _type)
	{
		Operation.operationId= operationId+1;
		setOperationId(operationId);
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

	public Object getData() {
		return data;
	}

	public void setData(Object _data) {
		this.data = _data;
	}
	
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public TransactionThread getTransactionThread() {
		return transactionThread;
	}

	public void setTransactionThread(TransactionThread transactionThread) {
		this.transactionThread = transactionThread;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOperationId() {
		return operationId;
	}

	public void setOperationId(int operationId) {
		this.operationId = operationId;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}


}