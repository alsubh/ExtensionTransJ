package umjdt.joinpoints;

import context.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import umjdt.concepts.Transaction;
import umjdt.util.BackgroundThread;
import umjdt.util.Status;
import umjdt.util.Timestamp;

public class TransJP extends EventJP
{
	Logger logger = Logger.getLogger(TransJP.class.toString());

	private UUID JpID;
	private Transaction transaction; 
	private BackgroundThread theJPThread; // run the this joinpoint on the thread 	
	private String status;// commit or abort(rollback)
	private BeginEventJP beginDemarcate;
	private EndEventJP endDemarcate;
	
	public TransJP()
	{
		initailization();
	}
	
	public TransJP(TransJP _transJp)
	{
		initailization();
		this.setLocalTime(_transJp.getLocalTime());
	}
		
	private void initailization() 
	{
		this.JpID= UUID.randomUUID();
		this.theJPThread = BackgroundThread.getInstance(JpID.toString());
	}
		
	public boolean occurredIn(Context _context, TransJP _tJP)
	{
		boolean result=false;
		if(_context.getTransJp().equals(_tJP))
		{
			result=true;
		}
		return result;
	}


	public BackgroundThread getTheJPThread() 
	{
		return theJPThread;
	}

	public void setTheJPThread(BackgroundThread theJPThread)
	{
		this.theJPThread = theJPThread;
	}
	
//	public boolean addEvent(EventJP TransJpEvent)
//	{
//		boolean result=false;
//		if (TransJpEvent !=null)
//		{
//			this.events.add(TransJpEvent);
//		}
//		return result;
//	} 
			
	public boolean threadEventHappensBefore(EventJP e)
	{
		if(e.getLocalTime().compareTo(this.getLocalTime()) > 0)
					return true;
		return false;
	}
	
	public Logger getLogger() 
	{
		return logger;
	}

	public void setLogger(Logger logger) 
	{
		this.logger = logger;
	}

	public UUID getTransJpID() 
	{
		return JpID;
	}

	public void setTransJpID(UUID transJpID) 
	{
		this.JpID = transJpID;
	}
	
	public Transaction getTransaction() 
	{
		return transaction;
	}

	public void setTransaction(Transaction transaction) 
	{
		this.transaction = transaction;
	}

	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}

	public BeginEventJP getBeginDemarcate() {
		return beginDemarcate;
	}

	public void setBeginDemarcate(BeginEventJP beginDemarcate) {
		this.beginDemarcate = beginDemarcate;
	}

	public EndEventJP getEndDemarcate() {
		return endDemarcate;
	}

	public void setEndDemarcate(EndEventJP endDemarcate) {
		this.endDemarcate = endDemarcate;
	}
}
