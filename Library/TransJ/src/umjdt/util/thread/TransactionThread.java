package umjdt.util.thread;

import java.util.*;
import umjdt.Events.Event;
import umjdt.Events.OperationEvent;
import umjdt.Events.TransactionEvent;
import umjdt.util.Timestamp;

public class TransactionThread extends BackgroundThread{

	private List<TransactionEvent> events = new ArrayList<TransactionEvent>();	
	private Timestamp timestamp;
	
	public TransactionThread()
	{
		setTimestamp(getTimestamp().currentTimeStamp());
	}
	
	public TransactionThread(String _name)
	{
		getThisThread().setName(_name);
		setTimestamp(new Timestamp());
	}
	
	public TransactionThread(Thread _thread, String _name)
	{
		_thread.setName(_name);
		setThisThread(_thread); 
		setTimestamp(new Timestamp());
	}
	
	public List<TransactionEvent> getEvents() 
	{
		return events;
	}
	
	public void setEvents(List<TransactionEvent> events) 
	{
		this.events = events;	
	}
	
	public void addEvent(TransactionEvent event)
	{
		events.add(event);
	}
	public void removeEvent(TransactionEvent event)
	{
		events.remove(event);
	}
	
	public boolean contains(Event e) 
	{
		return events.contains(e);
	}
	
	@Override
	public void run() 
	{
		
	}

	public void setTimestamp(Timestamp timestamp) 
	{
		this.timestamp = timestamp;
	}
}