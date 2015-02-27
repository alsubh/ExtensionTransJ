package umjdt;

import java.util.Timer;
import java.util.TimerTask;
import transaction.Transaction;
import utilities.Status;
import utilities.Timestamp;

public class TransactionCommitEvent extends TransactionEvent{
	
	private Timestamp CommitTime; 
	
	public TransactionCommitEvent(Transaction _localTransaction)
	{
		super.setTransaction(_localTransaction);
		setCommitTime(getLocalTime());
		setEventType("CommitEvent");
		setTransactionId(_localTransaction.getTId());
		super.setTransactionId(_localTransaction.getTId());
		setTimeout(_localTransaction.getTimeout());
		super.setTimeout(_localTransaction.getTimeout());
		super.setStatus(Status.COMMITTED);
		setMarkBoundary("Commit");
		super.setMarkBoundary("Commit");			
		
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