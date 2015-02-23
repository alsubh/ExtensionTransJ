package umjdt.Events;

import java.util.Timer;
import java.util.TimerTask;

import umjdt.Events.TransactionBeginEvent.BeginTask;
import umjdt.concepts.TransId;
import umjdt.concepts.Transaction;
import umjdt.util.Status;
import umjdt.util.Timestamp;
import umjdt.util.thread.TransactionThread;

public class TransactionBeginCommitEvent extends TransactionEvent{
	
	private Timestamp beginCommitTime; 
	
	public TransactionBeginCommitEvent(Transaction _localTransaction)
	{
		super.setTransaction(_localTransaction);
		setBeginCommitTime(getLocalTime());
		setTransactionId(_localTransaction.getId());
		super.setTransactionId(_localTransaction.getId());
		setTimeout(_localTransaction.getTimeout());
		super.setTimeout(_localTransaction.getTimeout());
		super.setStatus(Status.COMMITTING);
		setEventType("BeginCommit");
		setMarkBoundary("BeginCommit");
		super.setMarkBoundary("BeginCommit");
		setTheThread(_localTransaction.getTransactionThread());
		super.setTheThread(_localTransaction.getTransactionThread());
		
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