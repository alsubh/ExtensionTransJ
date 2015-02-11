package umjdt.Events;

import java.util.Timer;
import java.util.TimerTask;
import umjdt.concepts.Timestamp;
import umjdt.concepts.Transaction;

public class TransactionCommitEvent extends TransactionEvent{
	
	private Timestamp CommitTime; 
	public TransactionCommitEvent(Transaction _localTransaction)
	{
		super.setTransaction(_localTransaction);
		setCommitTime(getLocalTime());
		setTransactionId(_localTransaction.getId());
		super.setTransactionId(_localTransaction.getId());
		setTimeout(_localTransaction.getTimeout());
		super.setTimeout(_localTransaction.getTimeout());
		super.setState("Committed");
		_localTransaction.setCurrentState(getState());
		setMarkBoundary("Commit");
		super.setMarkBoundary("Commit");
		setTransactionThread(_localTransaction.getTransactionThread());
		super.setTransactionThread(_localTransaction.getTransactionThread());
		
		setTimer(new Timer());
		getTimer().schedule(new CommitTask(), _localTransaction.getTimeout());
	}
	 public Timestamp getCommitTime() {
		return CommitTime;
	}
	public void setCommitTime(Timestamp commitTime) {
		CommitTime = commitTime;
	}
	class CommitTask extends TimerTask {
	    public void run() {
	      System.out.println("Time's Commit!");
	      //timer.cancel(); 
	      System.exit(0); //Stops everything 
	    }
	  }
}