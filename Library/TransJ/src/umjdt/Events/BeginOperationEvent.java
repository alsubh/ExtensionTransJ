package umjdt.Events;

import umjdt.concepts.*;
import umjdt.util.OperationNumber;
import umjdt.util.Timestamp;

import java.util.*;

public class BeginOperationEvent extends OperationEvent{
 
	private Timestamp beginTimestamp;
		
	public BeginOperationEvent(Operation _operation)
	{
		super();
		// TODO Auto-generated constructor stub
		setOperation(_operation);
		setEventType("BeginOperation");
		setBeginTimestamp(beginTimestamp.currentTimeStamp());
		setBeginOperation(getBeginTimestamp());
	}
	
	public Timestamp getBeginTimestamp() 
	{
		return beginTimestamp;
	}
	
	public void setBeginTimestamp(Timestamp beginTimestamp) 
	{
		this.beginTimestamp = beginTimestamp;
	}
	
}
