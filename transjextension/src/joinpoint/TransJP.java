package joinpoint;

import context.Context;
import umjdt.Event;
import umjdt.TransactionEvent;
import utilities.BackgroundThread;
import utilities.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class TransJP
{
	Logger logger = Logger.getLogger(TransJP.class.toString());

	private UUID uuid;
	private Timestamp timestamp;
	private BackgroundThread theJPThread;	
	private List<Event> events;
	private String status;
	
	public TransJP()
	{
		initailization();
	}
	
	public TransJP(TransJP _transJp)
	{
		initailization();
		this.events=_transJp.getEvents();
	}
		
	private void initailization() 
	{
		this.uuid= UUID.randomUUID();
		this.theJPThread = BackgroundThread.getInstance(uuid.toString());
		this.setTimestamp(timestamp.currentTimeStamp());
		this.events= new ArrayList<Event>();
	}
	
	public void set(TransJP _transJp)
	{
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean addEvent(Event event)
	{
		boolean result=false;
		if (event !=null)
		{
			this.events.add(event);
		}
		return result;
	} 
}
