package umjdt.concepts;

import java.util.ArrayList;
import java.util.List;

import umjdt.Events.OperationEvent;
import umjdt.util.Timestamp;
import umjdt.util.thread.BackgroundThread;

public class OperationThread extends BackgroundThread{

	private List<OperationEvent> operationEvents = new ArrayList<OperationEvent>();	
	private Timestamp timestamp;

	public OperationThread(){
		Thread thread = new Thread();
		setThisThread(thread);
		setTimestamp(new Timestamp());
	}
	
	public OperationThread(String _name){
		setThisThread(new Thread());
		getThisThread().setName(_name);
		setTimestamp(new Timestamp());
	}
	
	public OperationThread(Thread _thread, String _name){
		_thread.setName(_name);
		setThisThread(_thread);
		setTimestamp(new Timestamp());
	}
	
	public List<OperationEvent> getOperationEvents() {
		return operationEvents;
	}
	
	public void setOperationEvents(List<OperationEvent> operationEvents) {
		this.operationEvents = operationEvents;
	}
	
	public Timestamp getTimestamp(){
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public void run() {		
	}
}
