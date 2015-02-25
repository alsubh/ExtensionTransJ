package umjdt;

import transaction.Operation;
import transaction.Transaction;
import utilities.Timestamp;


public class OperationEvent extends Event
{
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
		
	public Timestamp getBeginOperation() 
	{
		return beginOperation;
	}
	
	public void setBeginOperation(Timestamp beginOperation) 
	{
		this.beginOperation = beginOperation;
	}
	
	public Timestamp getEndOperation() 
	{
		return endOperation;
	}
	
	public void setEndOperation(Timestamp endOperation) 
	{
		this.endOperation = endOperation;
	}
}
