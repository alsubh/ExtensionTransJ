package umjdt.joinpoints;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

import javax.transaction.TransactionManager;
import umjdt.concepts.TID;
import umjdt.concepts.Transaction;
import umjdt.util.Constants;
import umjdt.util.Timestamp;

public class BeginEventJP extends TransJP
{
	private javax.transaction.Transaction xatransaction;
	private TransactionManager manager;
	private Timestamp beginTime; 
	private int timeout;
	private int status;
	private TID tid;
			
	public BeginEventJP()
	{
		setTimeout((int) Constants.TimeToWait);
	}
	
	public BeginEventJP(Transaction transaction) 
	{
		super();
		setXatransaction(transaction);
		setTid(transaction.getTid());
		setManager(transaction.getTransactionManager());
		setStatus(transaction.getStatus());
		setTimeout(transaction.getTimeout());
		setBeginTime(new Timestamp().currentTimeStamp());
		setTimer(new Timer());
		getTimer().schedule(new BeginTask(), getTimeout());
		setBeginDemarcate(this);
	}

	public Timestamp getBeginTime() 
	{
		return beginTime;
	}
	public void setBeginTime(Timestamp _beginTime) 
	{
		this.beginTime = _beginTime;
	}

	public TransactionManager getTm() {
		return manager;
	}

	public void setManager(TransactionManager tm) {
		this.manager = tm;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public TID getTid() {
		return tid;
	}

	public void setTid(TID tid) {
		this.tid = tid;
	}
	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public javax.transaction.Transaction getXatransaction() {
		return xatransaction;
	}

	public void setXatransaction(javax.transaction.Transaction xatransaction) {
		this.xatransaction = xatransaction;
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
