package joinpoint.transaction;

import java.util.List;

import joinpoint.TransJP;
import transaction.SubTransaction;
import transaction.TID;
import transaction.Transaction;
import umjdt.TransactionEvent;
import utilities.Status;
import utilities.Timestamp;

public class AbortEventJP extends TransactionJP
{
	private TID tid;
	private Timestamp abortTime; 
	
	public AbortEventJP() 
	{
		super();
		this.setAbortTime(new Timestamp().currentTimeStamp());
		setStatus(Status.stringForm(Status.ABORTED));
	}

	public AbortEventJP(TID _tid, Transaction _transaction,
			Timestamp _timestamp, Status _status,
			List<SubTransaction> _subtransactions,
			List<TransactionEvent> _transactionEvents) 
	{
		super(_tid, _transaction, _timestamp, _status, _subtransactions,
				_transactionEvents);
		this.setAbortTime(new Timestamp().currentTimeStamp());
		this.setTidEvent(_tid);
	}

	public AbortEventJP(TransJP _transJp) 
	{
		super(_transJp);
		this.setAbortTime(new Timestamp().currentTimeStamp());
		setStatus(Status.stringForm(Status.ABORTED));
	}
	
	@Override
	public boolean occurOn(Transaction _transaction)
	{
		boolean result= false;
		if(_transaction.equals(transaction))
		{
			result = true;
		}
		return result;
	}
	
	public AbortEventJP(TransactionJP _localTransaction)
	{
		super.setTransaction(_localTransaction);
		setAbortTime(getLocalTime());
		setTransactionId(_localTransaction.getId());
		super.setTransactionId(_localTransaction.getId());
		setTimeout(_localTransaction.getTimeout());
		super.setTimeout(_localTransaction.getTimeout());
		
		setMarkBoundary("Abort");
		super.setMarkBoundary("Abort");
		setTheThread(_localTransaction.getTransactionThread());
		super.setTheThread(_localTransaction.getTransactionThread());
		
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
	public TID getTidEvent() {
		return tidEvent;
	}

	public void setTidEvent(TID tidEvent) {
		this.tidEvent = tidEvent;
	}
	class CommitTask extends TimerTask {
	    public void run() {
	      System.out.println("Time's Commit!");
	      //timer.cancel(); 
	      System.exit(0); //Stops everything 
	    }
	  }
}
