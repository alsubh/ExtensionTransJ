package umjdt;

import java.util.Timer;
import java.util.TimerTask;

import transaction.Transaction;
import utilities.Status;
import utilities.Timestamp;


public class TransactionAbortEvent extends TransactionEvent
{
	
	private Timestamp abortTime; 
	
	public TransactionAbortEvent(Transaction _localTransaction)
	{
		super.setTransaction(_localTransaction);
		setAbortTime(getLocalTime());
		setTransactionId(_localTransaction.getTId());
		super.setTransactionId(_localTransaction.getTId());
		setTimeout(_localTransaction.getTimeout());
		super.setTimeout(_localTransaction.getTimeout());
		super.setStatus(Status.ABORTED);
		setMarkBoundary("Abort");
		super.setMarkBoundary("Abort");
		
		setTimer(new Timer());
		getTimer().schedule(new CommitTask(), _localTransaction.getTimeout());

		_localTransaction.addEvent(this);

	}
	
	public Timestamp getAbortTime() {
		return abortTime;
	}
	public void setAbortTime(Timestamp abortTime) {
		this.abortTime = abortTime;
	}
	class CommitTask extends TimerTask {
	    public void run() {
	      System.out.println("Time's Commit!");
	      //timer.cancel(); 
	      System.exit(0); //Stops everything 
	    }
	  }
}
