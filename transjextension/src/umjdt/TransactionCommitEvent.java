package umjdt.Events;

import java.util.Timer;
import java.util.TimerTask;
import umjdt.concepts.Transaction;
import umjdt.util.Status;
import umjdt.util.Timestamp;

public class TransactionCommitEvent extends TransactionEvent{
	
	private Timestamp CommitTime; 
	
	public TransactionCommitEvent(Transaction _localTransaction)
	{
		super.setTransaction(_localTransaction);
		setCommitTime(getLocalTime());
		setEventType("CommitEvent");
		setTransactionId(_localTransaction.getId());
		super.setTransactionId(_localTransaction.getId());
		setTimeout(_localTransaction.getTimeout());
		super.setTimeout(_localTransaction.getTimeout());
		super.setStatus(Status.COMMITTED);
		setMarkBoundary("Commit");
		super.setMarkBoundary("Commit");
		setTheThread(_localTransaction.getTransactionThread());
		super.setTheThread(_localTransaction.getTransactionThread());
				
		
		setTimer(new Timer());
		getTimer().schedule(new CommitTask(), _localTransaction.getTimeout());
		
		_localTransaction.addEvent(this);
	}
	 
	public Timestamp getCommitTime() 
	{
		return CommitTime;
	}
	
	public void setCommitTime(Timestamp commitTime) 
	{
		CommitTime = commitTime;
	}
	
	class CommitTask extends TimerTask 
	{
	    public void run() 
	    {
	      System.out.println("Time's Commit!");
	     // TODO: call rollback  or aborting and then change the status
	    }
	}
}