package umjdt.joinpoints.transaction;

import baseaspect.AbortTransactionAspect;
import umjdt.joinpoints.TransJP;
import umjdt.concepts.TID;
import umjdt.concepts.Transaction;
import umjdt.joinpoints.AbortEventJP;
import umjdt.joinpoints.BeginEventJP;
import umjdt.joinpoints.CommitEventJP;
import umjdt.util.BackgroundThread;

public class InnerTransactionJP extends TransJP
{
	
	private BeginEventJP beginJp;
	private CommitEventJP commitJp;
	private AbortEventJP abortJp;
	private int status;

	
	public InnerTransactionJP()
	{
		super.setTransaction(getTransaction());
		super.setTid(getTid());
		super.setStatus(getStatus());
	}
	
	public InnerTransactionJP(TID _tid, Transaction _transaction, BackgroundThread _thread)
	{
		super.setTransaction(_transaction);
		super.setTid(_tid);
		super.setThread(_thread);
	}
	
	public InnerTransactionJP(TID _tid, Transaction _transaction, BackgroundThread _thread, BeginEventJP _beginJp, CommitEventJP _commitJp)
	{
		super.setTransaction(_transaction);
		super.setTid(_tid);
		super.setThread(_thread);
		this.beginJp= _beginJp;
		this.commitJp= _commitJp;
		super.setBeginDemarcate(_beginJp);
		super.setEndDemarcate(_commitJp);
	}
	
	public InnerTransactionJP(TID _tid, Transaction _transaction, BackgroundThread _thread, BeginEventJP _beginJp, AbortEventJP _abortJp)
	{
		super.setTransaction(_transaction);
		super.setTid(_tid);
		super.setThread(_thread);
		this.beginJp= _beginJp;
		this.abortJp= _abortJp;
		super.setBeginDemarcate(_beginJp);
		super.setEndDemarcate(_abortJp);
	}
	
	public InnerTransactionJP(TransJP _transJp)
	{
		super(_transJp);
		super.setTransaction(_transJp.getTransaction());
		super.setTid(_transJp.getTid());
		super.setThread(_transJp.getThread());
		this.beginJp= _transJp.getBeginDemarcate();
		if(_transJp.getEndDemarcate().getClass().equals(AbortEventJP.class))
			this.commitJp= (CommitEventJP) _transJp.getEndDemarcate();
		else if(_transJp.getEndDemarcate().getClass().equals(AbortEventJP.class))
			this.abortJp= (AbortEventJP) _transJp.getEndDemarcate();
	}
	
	public BeginEventJP getBeginJp() {
		return beginJp;
	}
	public void setBeginJp(BeginEventJP beginJp) {
		this.beginJp = beginJp;
	}

	public CommitEventJP getCommitJp() {
		return commitJp;
	}
	public void setCommitJp(CommitEventJP commitJp) {
		this.commitJp = commitJp;
	}
	public AbortEventJP getAbortJp() {
		return abortJp;
	}
	public void setAbortJp(AbortEventJP abortJp) {
		this.abortJp = abortJp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}	
}
