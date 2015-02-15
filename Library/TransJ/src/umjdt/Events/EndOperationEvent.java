package umjdt.Events;

import umjdt.concepts.Operation;
import umjdt.concepts.Transaction;
import umjdt.util.OperationNumber;

public class EndOperationEvent extends OperationEvent{

	private OperationNumber operationNur;
	
	
	public EndOperationEvent(OperationNumber _opNur) {
		super(_opNur);
		// TODO Auto-generated constructor stub
		setOperationNur(_opNur);
		setBeginOperation(getBeginOperation().currentTimeStamp());
	}
	public EndOperationEvent(Transaction _transaction, Operation _operation, OperationNumber _opNur) {
		super(_transaction,_operation);
		setTransaction(_transaction);
		setOperation(_operation);
		setOperationNur(_opNur);
		setEndOperation(getBeginOperation().currentTimeStamp());
	}
	public EndOperationEvent(Operation _operation) {
		super(_operation);
		// TODO Auto-generated constructor stub
		setOperation(_operation);
		setOperationNur(_operation.getOperationNr());
		setEndOperation(getBeginOperation().currentTimeStamp());
	}
	
	public OperationNumber getOperationNur() {
		return operationNur;
	}
	public void setOperationNur(OperationNumber operationNur) {
		this.operationNur = operationNur;
	}
}