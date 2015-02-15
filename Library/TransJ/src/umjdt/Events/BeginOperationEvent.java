package umjdt.Events;

import umjdt.concepts.*;
import umjdt.util.OperationNumber;

import java.util.*;

public class BeginOperationEvent extends OperationEvent{
 
	private OperationNumber operationNur;
	
	
	public BeginOperationEvent(OperationNumber _opNur) {
		super(_opNur);
		// TODO Auto-generated constructor stub
		setOperationNur(_opNur);
		setBeginOperation(getBeginOperation().currentTimeStamp());
	}
	public BeginOperationEvent(Transaction _transaction, Operation _operation, OperationNumber _opNur) {
		super(_transaction,_operation);
		setTransaction(_transaction);
		setOperationNur(_opNur);
		setOperation(_operation);
		setBeginOperation(getBeginOperation().currentTimeStamp());
	}
	public BeginOperationEvent(Operation _operation) {
		super(_operation);
		// TODO Auto-generated constructor stub
		setOperation(_operation);
		setOperationNur(_operation.getOperationNr());
		setBeginOperation(getBeginOperation().currentTimeStamp());
	}
	
	
	public OperationNumber getOperationNur() {
		return operationNur;
	}
	public void setOperationNur(OperationNumber operationNur) {
		this.operationNur = operationNur;
	}
}
