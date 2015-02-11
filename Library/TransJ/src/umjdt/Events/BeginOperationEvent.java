package umjdt.Events;

import umjdt.concepts.*;
import java.util.*;

public class BeginOperationEvent extends OperationEvent{
 
	public BeginOperationEvent(Transaction _transaction, Operation _operation) {
		super(_transaction,_operation);
		setTransaction(_transaction);
		setOperation(_operation);
		setBeginOperation(getBeginOperation().currentTimeStamp());
	}
	public BeginOperationEvent(Operation _operation) {
		super(_operation);
		// TODO Auto-generated constructor stub
		setOperation(_operation);
		setBeginOperation(getBeginOperation().currentTimeStamp());
	}
}
