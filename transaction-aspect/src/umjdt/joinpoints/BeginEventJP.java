package umjdt.joinpoints;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

import com.arjuna.ats.jta.TransactionManager;
import com.sun.corba.se.impl.orbutil.closure.Constant;

import umjdt.concepts.TID;
import umjdt.concepts.Transaction;
import umjdt.util.BackgroundThread;
import umjdt.util.Constants;
import umjdt.util.Timestamp;
import umjdt.util.TransactionThread;

public class BeginEventJP extends TransJP
{
	private TID beginID;// make the event associated with right transaction
	private Timestamp beginTime; 
	private TransactionManager tm;
	
	public BeginEventJP(){}
	
	public BeginEventJP(Transaction transaction) 
	{
		super();
		if(transaction.getTId() == null)
		{
			transaction.setTId(new TID());
			beginID= transaction.getTId();
			//System.out.println(transaction.getTId());
		}
		else
			beginID= transaction.getTId();
		
		setBeginTime(new Timestamp().currentTimeStamp());
		setTransaction(transaction);
		setTimeout((int) Constants.TimeToWait);
		setTimer(new Timer());
		getTimer().schedule(new BeginTask(), transaction.getTimeout());
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

	public TID getBeginID() {
		return beginID;
	}

	public void setBeginID(TID beginID) {
		this.beginID = beginID;
	}

	public TransactionManager getTm() {
		return tm;
	}

	public void setTm(TransactionManager tm) {
		this.tm = tm;
	}

	class BeginTask extends TimerTask {
	    public void run() {
	    	logger.log(Level.INFO, BeginTask.class.toString());
	      System.out.println("Time's up!");
	      //System.exit(0); //Stops everything 
	    }
	 }	
}
