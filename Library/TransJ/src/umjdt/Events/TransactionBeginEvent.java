package umjdt.Events;

import java.util.Timer;
import java.util.TimerTask;

import umjdt.concepts.Transaction;
import umjdt.concepts.TransactionThread;
import umjdt.util.Status;
import umjdt.util.Timestamp;

public class TransactionBeginEvent extends TransactionEvent{
	
	private Timestamp beginTime; 
	private TransactionThread transactionThread;
	
	
	public TransactionBeginEvent(Transaction _transaction)
	{
		iniilization(_transaction);
		// add transaction to a new thread
		transactionThread = new TransactionThread();
		_transaction.setTransactionThread(transactionThread);
		
		_transaction.addEvent(this);
	}
	
	public TransactionBeginEvent(String _threadName,Transaction _transaction)
	{
		iniilization(_transaction);
		// add transaction to a new thread
		transactionThread = new TransactionThread(_threadName);
		_transaction.setTransactionThread(transactionThread);
		
		_transaction.addEvent(this);
	}
	
	private void iniilization(Transaction _transaction)
	{
		super.setTransaction(_transaction);
		setBeginTime(getLocalTime());
		setEventType("BeginEvent");
		setTransactionId(_transaction.getId());
		super.setTransactionId(_transaction.getId());
		setTimeout(_transaction.getTimeout());
		super.setTimeout(_transaction.getTimeout());
		setStatus(Status.CREATED); // begin
		setMarkBoundary("Begin");
		super.setMarkBoundary("Begin");
		
		setTimer(new Timer());
		getTimer().schedule(new BeginTask(), _transaction.getTimeout());
		
		_transaction.addEvent(this);

	}

	public Timestamp getBeginTime() 
	{
		return beginTime;
	}
	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}
	 class BeginTask extends TimerTask {
	    public void run() {
	      System.out.println("Time's up!");
	      //timer.cancel(); 
	      System.exit(0); //Stops everything 
	    }
	 }
}