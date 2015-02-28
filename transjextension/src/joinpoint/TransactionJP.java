package joinpoint;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import transaction.SubTransaction;
import transaction.TID;
import transaction.Transaction;
import umjdt.Event;
import umjdt.TransactionBeginEvent;
import umjdt.TransactionEvent;
import utilities.Timestamp;
import utilities.TransactionType;

public class TransactionJP extends TransJP
{
	private TID tid;
	private TransactionType type;
	private Transaction transaction;
	private List<SubTransaction> childTransactions;
	private List<TransactionEvent> transactionEvents;
	
	public TransactionJP() 
	{
		super();
		this.setTimestamp(new Timestamp().currentTimeStamp());
		this.childTransactions= new ArrayList<SubTransaction>();
	
		// add the begin event when begin the transaction
		this.transactionEvents= new ArrayList<TransactionEvent>();
		TransactionEvent event = new TransactionBeginEvent(getTransaction());
		this.addEvent(event);
	}
	public TransactionJP(TransJP _transJp)
	{
		super(_transJp);
		setTimestamp(new Timestamp().currentTimeStamp());
		this.childTransactions= new ArrayList<SubTransaction>();
		
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
}
