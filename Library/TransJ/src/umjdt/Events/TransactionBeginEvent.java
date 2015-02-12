package umjdt.Events;

import java.util.Timer;
import java.util.TimerTask;

import umjdt.concepts.Transaction;
import umjdt.concepts.TransactionThread;
import umjdt.util.Timestamp;

public class TransactionBeginEvent extends TransactionEvent{
	
	private Timestamp beginTime; 
	TransactionThread transactionThread;
	
	
	public TransactionBeginEvent(Transaction _transaction)
	{
		iniilization(_transaction);
		// add transaction to a new thread
		transactionThread = new TransactionThread();
		_transaction.setTransactionThread(transactionThread);
	}
	public TransactionBeginEvent(String _threadName,Transaction _transaction)
	{
		iniilization(_transaction);
		// add transaction to a new thread
		transactionThread = new TransactionThread(_threadName);
		_transaction.setTransactionThread(transactionThread);
	}
	private void iniilization(Transaction _transaction){
		super.setTransaction(_transaction);
		setBeginTime(getLocalTime());
		setTransactionId(_transaction.getId());
		super.setTransactionId(_transaction.getId());
		setTimeout(_transaction.getTimeout());
		super.setTimeout(_transaction.getTimeout());
		setState("Begin");
		_transaction.setCurrentState(getState());
		setMarkBoundary("Begin");
		super.setMarkBoundary("Begin");
		
		setTimer(new Timer());
		getTimer().schedule(new BeginTask(), _transaction.getTimeout());
		
	}
	public Timestamp getBeginTime() {
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