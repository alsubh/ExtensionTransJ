package joinpoint;

import context.Context;
import transaction.TID;
import transaction.Transaction;
import umjdt.Event;
import umjdt.TransactionBeginEvent;
import utilities.BackgroundThread;
import utilities.Status;
import utilities.Timestamp;
import utilities.TransactionThread;
import utilities.TransactionType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import javax.transaction.SystemException;
import org.aspectj.lang.JoinPoint;

public class TransJP
{
	Logger logger = Logger.getLogger(TransJP.class.toString());

	private UUID uuid;
	private Transaction transaction;
	private BackgroundThread theJPThread;
	private List<Event> events= new ArrayList<Event>();;
	private Timestamp timestamp;
	private int status;
	private TID tid;
	private TransactionType type;
	
	public TransJP(String _threadNameID)
	{
		initailization();
	}
	
	public TransJP(TID _tid, Transaction _transaction, TransactionType _type)
	{
		initailization();
		this.setTransaction(_transaction);
		this.setTid(_tid);
		this.setType(_type);
		try 
		{
			this.setStatus(_transaction.getStatus());
		}
		catch (SystemException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TransJP(TransJP _transJp)
	{
		initailization();
		this.transaction =_transJp.getTransaction();
		this.events=_transJp.getEvents();
		this.setTid(_transJp.getTid());
		this.setType(_transJp.getType());	
	}
	
	private void initailization() 
	{
		this.uuid= UUID.randomUUID();
		this.theJPThread = BackgroundThread.getInstance(uuid.toString());
		this.setTimestamp(timestamp.currentTimeStamp());
		// add begin event to the transaction
		this.events.add(new TransactionBeginEvent(this.getTransaction()));
	}
	
	public void set(TransJP _transJp)
	{
		this.transaction =_transJp.getTransaction();
		this.events= _transJp.getEvents();
	}
	
	public boolean occuerIn(Context _context, TransJP _tJP)
	{
		boolean result=false;
		if(_context.getTransJp().equals(_tJP))
		{
			result=true;
		}
		return result;
	}
	public Transaction getTransaction() 
	{
		return transaction;
	}
	
	public void setTransaction(Transaction transaction) 
	{
		this.transaction = transaction;
	}

	public List<Event> getEvents()
	{
		return events;
	}

	public void setEvents(List<Event> events) 
	{
		this.events = events;
	}

	public BackgroundThread getTheJPThread() 
	{
		return theJPThread;
	}

	public void setTheJPThread(BackgroundThread theJPThread)
	{
		this.theJPThread = theJPThread;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
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
	public TransactionType getType() {
		return type;
	}
	public void setType(TransactionType type) {
		this.type = type;
	}
}
