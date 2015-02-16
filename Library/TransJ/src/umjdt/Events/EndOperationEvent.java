package umjdt.Events;

import umjdt.concepts.Operation;
import umjdt.concepts.Transaction;
import umjdt.util.OperationNumber;
import umjdt.util.Timestamp;

public class EndOperationEvent extends OperationEvent{

	private OperationNumber operationNur;
	private Timestamp endTimestamp;
	
	public EndOperationEvent(OperationNumber _opNur) {
		super(_opNur);
		// TODO Auto-generated constructor stub
		setOperationNur(_opNur);
		setEndTimestamp(getEndOperation().currentTimeStamp());
		setEndOperation(getEndTimestamp());
	}
	public EndOperationEvent(Transaction _transaction, Operation _operation, OperationNumber _opNur) {
		super(_transaction,_operation);
		setOperation(_operation);
		setOperationNur(_opNur);
		setEndTimestamp(getEndOperation().currentTimeStamp());
		setEndOperation(getEndTimestamp());
	}
	public EndOperationEvent(Operation _operation) {
		super(_operation);
		// TODO Auto-generated constructor stub
		setOperation(_operation);
		setOperationNur(_operation.getOperationNr());
		setEndTimestamp(getEndOperation().currentTimeStamp());
		setEndOperation(getEndTimestamp());
	}
	
	public OperationNumber getOperationNur() {
		return operationNur;
	}
	public void setOperationNur(OperationNumber operationNur) {
		this.operationNur = operationNur;
	}
	public Timestamp getEndTimestamp() {
		return endTimestamp;
	}
	public void setEndTimestamp(Timestamp endTimestamp) {
		this.endTimestamp = endTimestamp;
	}
}