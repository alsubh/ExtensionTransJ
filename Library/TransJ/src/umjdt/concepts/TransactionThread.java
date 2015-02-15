package umjdt.concepts;

import java.util.*;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import umjdt.Events.Event;
import umjdt.Events.OperationEvent;
import umjdt.Events.TransactionEvent;
import umjdt.util.RunnableThread;
import umjdt.util.Timestamp;

public class TransactionThread extends RunnableThread{

	private List<TransactionEvent> events = new ArrayList<TransactionEvent>();	
	
	public TransactionThread(){
		getTimestamp();
	}
	
	public TransactionThread(String _name){
		getThisThread().setName(_name);
		getTimestamp();
	}
	
	public TransactionThread(Thread _thread, String _name){
		_thread.setName(_name);
		setThisThread(_thread); 
		getTimestamp();
	}
	
	public List<TransactionEvent> getEvents() {
		return events;
	}

	public Timestamp getTimestamp(){
		return new Timestamp();
	}
	public void setEvents(List<TransactionEvent> events) {
		this.events = events;	
	}
	
	public void addEvent(TransactionEvent event){
		events.add(event);
	}
	public void removeEvent(TransactionEvent event){
		events.remove(event);
	}
	
	public boolean contains(Event e) {
		return events.contains(e);
	}
	
	@Override
	public void run() {		
	}
}