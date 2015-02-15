package umjdt.Events;

import umjdt.concepts.Operation;
import umjdt.concepts.ResourceManager;
import umjdt.concepts.Transaction;
import umjdt.util.AccessType;
import umjdt.util.LockType;
import umjdt.util.OperationNumber;
import umjdt.util.Timestamp;

public class OperationEvent extends Event{

	private Transaction transaction;
	private Operation operation;
	private ResourceManager resourceManager;
	private Timestamp beginOperation;
	private Timestamp endOperation;
	private OperationNumber operationNumber;
	
	public OperationEvent(OperationNumber _opNur)
	{
		setOperationNumber(_opNur);
	}
	
	public OperationEvent(Operation _operation){ 
		setOperation(_operation);
	}
	public OperationEvent(Transaction _transaction, Operation _operation){ 
		setTransaction(_transaction);
		setOperation(_operation);
		
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	public boolean occursOn(Transaction _transaction, Operation _operation) {
		// TODO Auto-generated method stub
		if(_transaction.getOperations().contains(_operation))
			return true;
		return false; 
	}
	public ResourceManager getResourceManager() {
		return resourceManager;
	}
	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}
	public umjdt.util.Timestamp getBeginOperation() {
		return beginOperation;
	}
	public void setBeginOperation(Timestamp beginOperation) {
		this.beginOperation = beginOperation;
	}
	public umjdt.util.Timestamp getEndOperation() {
		return endOperation;
	}
	public void setEndOperation(umjdt.util.Timestamp endOperation) {
		this.endOperation = endOperation;
	}
	public OperationNumber getOperationNumber() {
		return operationNumber;
	}
	public void setOperationNumber(OperationNumber operationNumber) {
		this.operationNumber = operationNumber;
	}
}
