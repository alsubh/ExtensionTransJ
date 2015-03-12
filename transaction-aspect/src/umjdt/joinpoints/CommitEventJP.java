package umjdt.joinpoints;

import java.util.List;
import java.util.Timer;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import umjdt.concepts.Resource;
import umjdt.concepts.TID;
import umjdt.concepts.Transaction;
import umjdt.util.Timestamp;

public class CommitEventJP extends EndEventJP
{
	private Timer timer;
	private Timestamp commitTimestamp;
		
	public CommitEventJP() 
	{
		super();
		this.commitTimestamp= new Timestamp();
		if(super.getThread() !=null)
		{
			super.getThread().stop();
		}
	}

	public CommitEventJP(TID _tid)
	{
		super.setTid(_tid);
		this.commitTimestamp= new Timestamp().currentTimeStamp();
		super.setEndDemarcate(this);
		if(super.getThread() !=null)
		{
			super.getThread().stop();
		}	}
	
	public CommitEventJP(Transaction _transaction)
	{
		super();
		super.setTransaction(_transaction);
		this.commitTimestamp= new Timestamp().currentTimeStamp();
		super.setEndDemarcate(this);
		if(super.getThread() !=null)
		{
			super.getThread().stop();
		}	}
	
	public CommitEventJP(TID _tid, umjdt.concepts.Transaction _transaction,
			TransactionManager _manager, UserTransaction _user, int _timeout,
			int _status, List<umjdt.concepts.Transaction> transactionlist,
			List<Resource> resources, Timestamp _endTime, Thread _thread) 
	{
		super(_tid, _transaction, _manager, _user, _timeout, _status, transactionlist,
				resources, _endTime, _thread);
		
		this.commitTimestamp= new Timestamp().currentTimeStamp();
		super.setStatus(_status);
		super.setEndDemarcate(this);
		if(super.getThread() !=null)
		{
			super.getThread().stop();
		}
	}
	
	public Timer getTimer() 
	{
		return timer;
	}

	public void setTimer(Timer timer) 
	{
		this.timer = timer;
	}

	public Timestamp getCommitTimestamp() {
		return commitTimestamp;
	}

	public void setCommitTimestamp(Timestamp commitTimestamp) {
		this.commitTimestamp = commitTimestamp;
	}
}
