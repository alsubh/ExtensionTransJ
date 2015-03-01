package joinpoint.transaction;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import joinpoint.TransJP;

import transaction.SubTransaction;
import transaction.TID;
import transaction.Transaction;
import umjdt.Event;
import umjdt.TransactionBeginEvent;
import umjdt.TransactionEvent;
import utilities.BackgroundThread;
import utilities.Status;
import utilities.Timestamp;
import utilities.TransactionType;

public class TransactionJP extends TransJP
{
	private UUID transJpID;
	private TID tid;
	private TransactionType type;
	private Transaction transaction;
	private List<SubTransaction> childTransactions;
	private List<TransactionEvent> transactionEvents; 
	private Status status;
	
	public TransactionJP() 
	{
		super();
		this.setTransJPID(UUID.randomUUID());
		this.setTimestamp(new Timestamp().currentTimeStamp());
		this.childTransactions= new ArrayList<SubTransaction>();
	
		// add the begin event when begin the transaction
		this.transactionEvents= new ArrayList<TransactionEvent>();
		TransactionEvent event = new TransactionBeginEvent(getTransaction());
		this.addEvent(event);
	}
	
	public TransactionJP(TID _tid, Transaction _transaction, Timestamp _timestamp, 
			Status _status, List<SubTransaction> _subtransactions, List<TransactionEvent> _transactionEvents)
	{
		this.setTransJPID(UUID.randomUUID());
		this.setTid(_tid);
		this.setTransaction(_transaction);
		this.setTimestamp(_timestamp);
		this.setStatus(_status.toString());
		this.setChildTransactions(_subtransactions);
		this.setTransactionEvents(_transactionEvents);
	}

	public TransactionJP(TransJP _transJp)
	{
		super(_transJp);
		this.setTransJPID(UUID.randomUUID());
		setTimestamp(new Timestamp().currentTimeStamp());
		this.childTransactions= new ArrayList<SubTransaction>();
		this.setStatus(_transJp.getStatus());
		// add the begin event when begin the transaction
		this.transactionEvents= new ArrayList<TransactionEvent>();
		TransactionEvent event = new TransactionBeginEvent(getTransaction());
		this.addEvent(event);
	}
	
	public TID getTid() {
		return tid;
	}
	public void setTid(TID tid) {
		this.tid = tid;
	}
	public TransactionType getType() {
		return type;
	}
	public void setType(TransactionType type) {
		this.type = type;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public List<TransactionEvent> getTransactionEvents() {
		return transactionEvents;
	}
	public void setTransactionEvents(List<TransactionEvent> transactionEvents) {
		this.transactionEvents = transactionEvents;
	}
	public List<SubTransaction> getChildTransactions() {
		return childTransactions;
	}
	public void setChildTransactions(List<SubTransaction> childTransactions) {
		this.childTransactions = childTransactions;
	}
	
	@Override
	public boolean addEvent(Event event)
	{
		boolean result=false;
		if(event.getClass() == TransactionEvent.class)
		{
			this.transactionEvents.add((TransactionEvent) event);
		}
		return result;
	}

	public UUID getTransJPID() {
		return transJpID;
	}

	public void setTransJPID(UUID _transJPID) {
		this.transJpID = _transJPID;
	}
}
