package umjdt.util;

import java.util.ArrayList;
import java.util.List;
import umjdt.Events.Event;
import umjdt.concepts.TransactionThread;


public class RunnableThread extends Thread implements Runnable{
	
	private List<Event> events = new ArrayList<Event>();
	private Thread thisThread;
	private String threadName;
	protected static int threadId=0;
	
	public RunnableThread(){
		Thread thread = new Thread();
		RunnableThread.threadId = threadId +1;
		setThisThread(thread);
		getTimestamp();
	}
	
	public RunnableThread(String _name){
		RunnableThread.threadId = threadId +1; 
		setThreadId(threadId);
		setThisThread(new Thread());
		getThisThread().setName(_name);
		getTimestamp();
	}
	
	public RunnableThread(Thread _thread, String _name){
		RunnableThread.threadId = threadId +1; 
		setThreadId(threadId);
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
	public static int getThreadId() {
		return threadId;
	}
	public static void setThreadId(int threadId) {
		TransactionThread.threadId = threadId;
	}
}
