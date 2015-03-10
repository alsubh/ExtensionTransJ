package umjdt.joinpoints;

import java.util.Timer;
import javax.transaction.Transaction;
import javax.transaction.xa.Xid;

import umjdt.util.Status;
import umjdt.util.Timestamp;

public class CommitEventJP extends EndEventJP
{
	private Timer timer;
			
	public CommitEventJP(Transaction transaction) 
	{
		super();
		initialization(transaction);	
	}

	public CommitEventJP(TransJP _transJp) 
	{
		super();
		setTransaction(_transJp.getTransaction());
		setTransactionId(_transJp.getTransactionId());
		initialization(_transJp.getTransaction());
	}
	
	private void initialization(Transaction _transaction)
	{
		umjdt.concepts.Transaction umjdtTransaction = (umjdt.concepts.Transaction) _transaction;
		setXatransaction(_transaction);
		setTid(umjdtTransaction.getTid());
		setManager(umjdtTransaction.getTransactionManager());
		setStatus(Status.COMMITTED);
		setTimeout(umjdtTransaction.getTimeout());
		setEndTime(new Timestamp().currentTimeStamp());
		
		setTimer(new Timer());
		getTimer().schedule(new BeginTask(), getTimeout());
		setEndDemarcate(this);
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
