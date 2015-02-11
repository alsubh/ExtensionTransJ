package umjdt.Events;

import java.util.Timer;
import java.util.TimerTask;
import umjdt.concepts.*;
import umjdt.util.LockType;
import umjdt.util.Timestamp;

public class ResultLockRequestEvent extends LockEvent {

	private Timestamp resultTime;
	private LockType lockType;
	
	public ResultLockRequestEvent(Lock _lock, long _resultTime) {
		super(_lock);
		setLocking(_lock);
		setResultTime(getLocalTime());
		
		setTimer(new Timer());
		getTimer().schedule(new LockTask(), _resultTime);
	}

	public LockType getLockType() {
		return lockType;
	}

	public void setLockType(LockType lockType) {
		this.lockType = lockType;
	}

	class LockTask extends TimerTask {
	    public void run() {
	      System.out.println("Result of Lock (Grant/Denied)  !");
	      // check status of the resource
	    }
	 }
	 public Timestamp getResultTime() {
		return resultTime;
	}
	
	 public void setResultTime(Timestamp _resulttLockTime) {
		this.resultTime = _resulttLockTime;
	}
}