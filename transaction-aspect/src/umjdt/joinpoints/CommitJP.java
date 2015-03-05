package umjdt.joinpoints;

import java.util.Timer;
import java.util.UUID;
import umjdt.concepts.Transaction;
import umjdt.util.BackgroundThread;
import umjdt.util.Status;
import umjdt.util.Timestamp;

public class CommitJP extends EndEventJP
{
	private Timer timer;
	
	public CommitJP(Transaction transaction) 
	{
		super(transaction);
		super.setEndID(transaction.getTId());
		super.setStatus(Status.stringForm(Status.COMMITTED));
		super.setEndTime(new Timestamp().currentTimeStamp());
		super.setTransaction(transaction);
		initialization(transaction);	
	}

	public CommitJP(TransJP _transJp) 
	{
		super(_transJp);
		super.setTransaction(_transJp.getTransaction());
		super.setStatus(Status.stringForm(Status.COMMITTED));
		super.setTransactionId(_transJp.getTransactionId());
		
		super.setEndID(_transJp.getTransactionId());
		super.setEndTime(_transJp.getLocalTime());
		initialization(_transJp.getTransaction());
	}
	
	private void initialization(Transaction _transaction)
	{
		super.setEventType("Commit");
		super.setTransactionId(_transaction.getTId());
		super.setTimeout(_transaction.getTimeout());
		super.setBkThread(BackgroundThread.getInstance(getEndID().toString(), this));
		
		setTimer(new Timer());
		getTimer().schedule(new EndTask(), _transaction.getTimeout());
		super.setEndEvent(this);
		addEvent(getEndEvent());
	}
	
	public Timer getTimer() 
	{
		return timer;
	}

	public void setTimer(Timer timer) 
	{
		this.timer = timer;
	}
}
