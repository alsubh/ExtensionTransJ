package umjdt.Events;

import umjdt.concepts.Operation;
import umjdt.concepts.Transaction;

public class EndOperationEvent extends OperationEvent{

	public EndOperationEvent(Transaction _transaction, Operation _operation) {
		super(_transaction,_operation);
		setTransaction(_transaction);
		setOperation(_operation);
		setEndOperation(getEndOperation().currentTimeStamp());
	}
	public EndOperationEvent(Operation _operation) {
		super(_operation);
		// TODO Auto-generated constructor stub
		setOperation(_operation);
		setEndOperation(getEndOperation().currentTimeStamp());
	}
}