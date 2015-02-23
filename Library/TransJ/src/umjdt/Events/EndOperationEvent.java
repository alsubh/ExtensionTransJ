package umjdt.Events;

import umjdt.concepts.Operation;
import umjdt.concepts.Transaction;
import umjdt.util.OperationNumber;
import umjdt.util.Timestamp;

public class EndOperationEvent extends OperationEvent{

	private Timestamp endTimestamp;
	
	public EndOperationEvent(Operation _operation)
	{
		super();
		// TODO Auto-generated constructor stub
		setOperation(_operation);
		setEventType("EndOperation");
		setEndOperation(endTimestamp.currentTimeStamp());
		setEndOperation(getEndTimestamp());
	}
	
	public Timestamp getEndTimestamp() 
	{
		return endTimestamp;
	}

	public void setEndTimestamp(Timestamp endTimestamp) 
	{
		this.endTimestamp = endTimestamp;
	}
	
	
	/**
	 * put the operation events for operations on thread on context 
	 */
}