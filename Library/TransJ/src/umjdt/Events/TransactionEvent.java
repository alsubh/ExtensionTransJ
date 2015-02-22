package umjdt.Events;

import java.util.logging.Logger;
import umjdt.concepts.*;

public class TransactionEvent extends Event
{	
	private Transaction transaction;
	private String markBoundary;
	private TransactionManager transactionmanager= new TransactionManager();
	private int status;
	
	Logger log = Logger.getLogger(TransactionEvent.class.getName());
	
	public TransactionEvent()
	{		
		super();
		setEventType("TransactionEvent");
		setTheThread(Thread.currentThread());
		log.info("Creating Transaction Event");
	}
	
	public Transaction getTransaction() 
	{
		return transaction;
	}

	public void setTransaction(Transaction _transaction) 
	{
		this.transaction = _transaction;
	}

	public boolean involves(Operation _operation) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean occursOn(Transaction _transaction) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	public String getMarkBoundary() 
	{
		return markBoundary;
	}

	public void setMarkBoundary(String markBoundary) 
	{
		this.markBoundary = markBoundary;
	}

	public TransactionManager getTransactionmanager() {
		return transactionmanager;
	}

	public void setTransactionmanager(TransactionManager transactionmanager) {
		this.transactionmanager = transactionmanager;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int _status) {
		this.status = _status;
	}
}
