package umjdt;

import java.util.Timer;
import java.util.TimerTask;

import transaction.Transaction;
import utilities.Status;
import utilities.Timestamp;


public class TransactionBeginCommitEvent extends TransactionEvent{
	
	private Timestamp beginCommitTime; 
	
	public TransactionBeginCommitEvent(Transaction _localTransaction)
	{
		super.setTransaction(_localTransaction);
		setBeginCommitTime(getLocalTime());
		setTransactionId(_localTransaction.getTId());
		super.setTransactionId(_localTransaction.getTId());
		setTimeout(_localTransaction.getTimeout());
		super.setTimeout(_localTransaction.getTimeout());
		super.setStatus(Status.COMMITTING);
		setEventType("BeginCommit");
		setMarkBoundary("BeginCommit");
		super.setMarkBoundary("BeginCommit");
		
		setTimer(new Timer());
		getTimer().schedule(new BeginCommitTask(), _localTransaction.getTimeout());
		
		_localTransaction.addEvent(this);

	}
	 public Timestamp getBeginCommitTime() {
		return beginCommitTime;
	}
	public void setBeginCommitTime(Timestamp beginCommitTime) {
		this.beginCommitTime = beginCommitTime;
	}
	class BeginCommitTask extends TimerTask {
	    public void run() {
	      System.out.println("Time's Begin Committing!");
	      //timer.cancel(); 
	      System.exit(0); //Stops everything 
	    }
	  }
}