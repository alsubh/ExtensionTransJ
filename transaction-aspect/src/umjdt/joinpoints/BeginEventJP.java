package umjdt.joinpoints;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import umjdt.concepts.TID;
import umjdt.concepts.Transaction;
import umjdt.util.BackgroundThread;
import umjdt.util.Constants;
import umjdt.util.Timestamp;

public class BeginEventJP extends TransJP
{
	private TransactionManager manager;
	private UserTransaction user;
	private Timestamp beginTime; 
	private int timeout;
	private TID tid;
	
	public BeginEventJP()
	{
		super();
		setTimeout((int) Constants.TimeToWait);
		startThread();
	}
	
	public BeginEventJP(int _timeout)
	{
		super();
		super.setTimeout(_timeout);
		startThread();
	}
	
	public BeginEventJP(TID _tid) 
	{
		super(_tid);
		startThread();
	}

	public BeginEventJP(Transaction _transaction) 
	{
		super(_transaction);
		startThread();
	}

	public BeginEventJP(TransJP _transjp) 
	{
		super(_transjp);
		startThread();
	}

	public BeginEventJP(TID _tid, Transaction _transaction, TransactionManager _manager, UserTransaction _user, BackgroundThread _thread, int _timeout) 
	{
		super();
		this.tid= _tid;
		super.setTransaction(_transaction);
		this.manager = _manager;
		this.user= _user;
		this.setUser(_user);
		this.beginTime = new Timestamp().currentTimeStamp();
		super.setBeginDemarcate(this);
		super.setThread(_thread);
		startThread();
	}	

	private void startThread() 
	{
		if(getThread() ==null)
		{
			super.setThread(new BackgroundThread(Thread.currentThread()));
			super.getThread().start();
		}
		else
		{
			//Start new thread 
			super.getThread().start();
		}
	}
	
	public TransactionManager getManager() 
	{
		return manager;
	}

	public void setManager(TransactionManager manager) {
		this.manager = manager;
	}

	public UserTransaction getUser() {
		return user;
	}

	public void setUser(UserTransaction user) {
		this.user = user;
	}

	public Timestamp getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public TID getTid() {
		return tid;
	}

	public void setTid(TID tid) {
		this.tid = tid;
	}

	class BeginTask extends TimerTask 
	{
	    public void run() {
	    	logger.log(Level.INFO, BeginTask.class.toString());
	      System.out.println("Time's up!");
	      //System.exit(0); //Stops everything 
	    }
	 }	
}
