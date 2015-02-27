package umjdt;

import java.util.Timer;
import java.util.TimerTask;
import transaction.Transaction;
import utilities.Status;
import utilities.Timestamp;
import utilities.TransactionThread;


public class TransactionBeginEvent extends TransactionEvent
{
	
	private Timestamp beginTime; 
	private TransactionThread transactionThread;
	
	
	public TransactionBeginEvent(Transaction _transaction)
	{
		iniilization(_transaction);
		// add transaction to a new thread
		transactionThread = TransactionThread.currentTransaction().getTransactionThread(); 
		_transaction.addEvent(this);
	}
	
	public TransactionBeginEvent(String _threadName,Transaction _transaction)
	{
		iniilization(_transaction);
		// add transaction to a new thread
		transactionThread = TransactionThread.currentTransaction().getTransactionThread();
		_transaction.addEvent(this);
	}
	
	private void iniilization(Transaction _transaction)
	{
		super.setTransaction(_transaction);
		setBeginTime(getLocalTime());
		setEventType("BeginEvent");
		setTransactionId(_transaction.getTId());
		super.setTransactionId(_transaction.getTId());
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
	public void setBeginTime(Timestamp _beginTime) 
	{
		this.beginTime = _beginTime;
	}
	 class BeginTask extends TimerTask {
	    public void run() {
	      System.out.println("Time's up!");
	      //timer.cancel(); 
	      System.exit(0); //Stops everything 
	    }
	 }
}