package umjdt.Events;

import java.util.TimerTask;
import umjdt.concepts.*;
import umjdt.util.LockType;
import umjdt.util.Timestamp;

public class RequestLockEvent extends LockEvent{

	private Timestamp requestTime;
	private LockType lockType;
	
	public RequestLockEvent(Lock _lock, Timestamp _requestTime) {
		super(_lock);
		setLocking(_lock);
		setRequestTime(getLocalTime());
	}

	public LockType getLockType() {
		return lockType;
	}

	public void setLockType(LockType lockType) {
		this.lockType = lockType;
	}

	class LockTask extends TimerTask {
	    public void run() {
	      System.out.println("Request lock !");
	      // try again 
	    }
	 }
	 public Timestamp getRequestTime() {
		return requestTime;
	}
	
	 public void setRequestTime(Timestamp requestLockTime) {
		this.requestTime = requestLockTime;
	}
}
