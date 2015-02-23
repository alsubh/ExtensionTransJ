package umjdt.util.thread;

import java.util.ArrayList;
import java.util.List;
import umjdt.Events.Event;
import umjdt.util.Timestamp;


public class BackgroundThread extends Thread implements Runnable{
	
	private List<Event> events = new ArrayList<Event>();
	private Thread thisThread;
	private String threadName;
	
	public BackgroundThread(){
		Thread thread = new Thread();
		setThisThread(thread);
		getTimestamp();
	}
	
	public BackgroundThread(String _name){
		setThisThread(new Thread());
		getThisThread().setName(_name);
		getTimestamp();
	}
	
	public BackgroundThread(Thread _thread, String _name){
		_thread.setName(_name);
		setThisThread(_thread); 
		getTimestamp();
	}
	
	public Timestamp getTimestamp(){
		return new Timestamp();
	}
	
	public void addEvent(Event event){
		events.add(event);
	}
	public void removeEvent(Event event){
		events.remove(event);
	}
	
	public boolean contains(Event e) {
		return events.contains(e);
	}
	
	@Override
	public void run() {		
	}
	
	public Thread getThisThread() {
		return thisThread;
	}
	
	public void setThisThread(Thread thisThread) {
		this.thisThread = thisThread;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
}
