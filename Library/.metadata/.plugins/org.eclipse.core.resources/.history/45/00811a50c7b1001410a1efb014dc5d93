package umjdt.Events;

import java.util.Timer;
import java.util.TimerTask;

import umjdt.Events.TransactionBeginEvent.BeginTask;
import umjdt.concepts.Timestamp;
import umjdt.concepts.TransId;
import umjdt.concepts.Transaction;
import umjdt.concepts.TransactionThread;

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
		super.setState("BeginCommit");
		_localTransaction.setCurrentState(getState());
		setMarkBoundary("BeginCommit");
		super.setMarkBoundary("BeginCommit");
		setTransactionThread(_localTransaction.getTransactionThread());
		super.setTransactionThread(_localTransaction.getTransactionThread());
		
		setTimer(new Timer());
		getTimer().schedule(new BeginCommitTask(), _localTransaction.getTimeout());
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