package umjdt.Events;

import umjdt.concepts.Operation;
import umjdt.concepts.ResourceManager;
import umjdt.concepts.Transaction;
import umjdt.util.AccessType;
import umjdt.util.LockType;
import umjdt.util.OperationNumber;
import umjdt.util.Timestamp;

public class OperationEvent extends Event{

	private Operation operation;
	private Timestamp beginOperation;
	private Timestamp endOperation;
	
	public OperationEvent()
	{ 
		super();
		setEventType("OperationEvent");
	}
	
	public Operation getOperation() 
	{
		return operation;
	}
	
	public void setOperation(Operation operation) 
	{
		this.operation = operation;
	}
	
	public boolean occursOn(Transaction _transaction, Operation _operation) 
	{
		// TODO Auto-generated method stub
		if(_transaction.getOperations().contains(_operation))
			return true;
		return false; 
	}
		
	public umjdt.util.Timestamp getBeginOperation() 
	{
		return beginOperation;
	}
	
	public void setBeginOperation(Timestamp beginOperation) 
	{
		this.beginOperation = beginOperation;
	}
	
	public umjdt.util.Timestamp getEndOperation() 
	{
		return endOperation;
	}
	
	public void setEndOperation(umjdt.util.Timestamp endOperation) 
	{
		this.endOperation = endOperation;
	}
}
