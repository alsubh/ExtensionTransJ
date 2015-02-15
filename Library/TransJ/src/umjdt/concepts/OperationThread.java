package umjdt.concepts;

import java.util.ArrayList;
import java.util.List;

import umjdt.Events.OperationEvent;
import umjdt.util.RunnableThread;
import umjdt.util.Timestamp;

public class OperationThread extends RunnableThread{

	private List<OperationEvent> operationEvents = new ArrayList<OperationEvent>();	
	
	public OperationThread(){
		Thread thread = new Thread();
		setThisThread(thread);
		getTimestamp();
	}
	
	public OperationThread(String _name){
		setThisThread(new Thread());
		getThisThread().setName(_name);
		getTimestamp();
	}
	public OperationThread(Thread _thread, String _name){
		_thread.setName(_name);
		setThisThread(_thread);
		getTimestamp();
	}
	public List<OperationEvent> getOperationEvents() {
		return operationEvents;
	}
	public void setOperationEvents(List<OperationEvent> operationEvents) {
		this.operationEvents = operationEvents;
	}
	public Timestamp getTimestamp(){
		return new Timestamp();
	}
}
