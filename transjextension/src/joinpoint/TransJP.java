package joinpoint;

import context.Context;
import transaction.Transaction;
import umjdt.Event;
import utilities.TransactionThread;
import utilities.TransactionType;
import java.util.List;
import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;

public class TransJP 
{
	Logger logger = Logger.getLogger(TransJP.class.toString());

	private Context context;	
	private TransactionThread theThread;
	private Transaction transaction;
	private List<Event> events;
	private TransactionType type;
	
	public TransJP()
	{
		
	}
	
	public TransJP(Transaction _transaction)
	{
		this.setTransaction(_transaction);
	}
	
	public TransJP(TransJP _transJp)
	{
		this.transaction =_transJp.getTransaction();
		this.type= _transJp.getType();
		this.events=_transJp.getEvents();
	}
	
	public void set(TransJP _transJp)
	{
		this.transaction =_transJp.getTransaction();
		this.type= _transJp.getType();
		this.events= _transJp.getEvents();
	}
	
	public Transaction getTransaction() 
	{
		return transaction;
	}
	
	public void setTransaction(Transaction transaction) 
	{
		this.transaction = transaction;
	}


	public Context getContext() {
		return context;
	}


	public void setContext(Context context) {
		this.context = context;
	}


	public TransactionType getType() {
		return type;
	}


	public void setType(TransactionType type) {
		this.type = type;
	}


	public List<Event> getEvents() {
		return events;
	}


	public void setEvents(List<Event> events) {
		this.events = events;
	}


	public TransactionThread getTheThread() {
		return theThread;
	}


	public void setTheThread(TransactionThread theThread) {
		this.theThread = theThread;
	}
}
