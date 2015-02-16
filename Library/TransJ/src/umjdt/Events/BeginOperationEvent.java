package umjdt.Events;

import umjdt.concepts.*;
import umjdt.util.OperationNumber;
import umjdt.util.Timestamp;

import java.util.*;

public class BeginOperationEvent extends OperationEvent{
 
	private OperationNumber operationNur;
	private Timestamp beginTimestamp;
	
	public BeginOperationEvent(OperationNumber _opNur) {
		super(_opNur);
		// TODO Auto-generated constructor stub
		setOperationNur(_opNur);
		setType("BeginOperation");
		setBeginTimestamp(getBeginOperation().currentTimeStamp());
		setBeginOperation(getBeginTimestamp());
	}
	
	public BeginOperationEvent(Transaction _transaction, Operation _operation, OperationNumber _opNur) {
		super(_transaction,_operation);
		setOperationNur(_opNur);
		setType("BeginOperation");
		setOperation(_operation);
		setBeginTimestamp(getBeginOperation().currentTimeStamp());
		setBeginOperation(getBeginTimestamp());
	}
	
	public BeginOperationEvent(Operation _operation) {
		super(_operation);
		// TODO Auto-generated constructor stub
		setOperation(_operation);
		setType("BeginOperation");
		setOperationNur(_operation.getOperationNr());
		setBeginTimestamp(getBeginOperation().currentTimeStamp());
		setBeginOperation(getBeginTimestamp());
	}
	
	
	public OperationNumber getOperationNur() {
		return operationNur;
	}
	public void setOperationNur(OperationNumber operationNur) {
		this.operationNur = operationNur;
	}
	public Timestamp getBeginTimestamp() {
		return beginTimestamp;
	}
	public void setBeginTimestamp(Timestamp beginTimestamp) {
		this.beginTimestamp = beginTimestamp;
	}
	
}
