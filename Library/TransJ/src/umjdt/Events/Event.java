package umjdt.Events;

import java.util.Timer;
import java.util.UUID;

import umjdt.concepts.TransId;
import umjdt.concepts.TransactionThread;
import umjdt.util.Timestamp;

public class Event {

		private TransactionThread transactionThread = new TransactionThread();
		private TransId transactionId;
		private String type;
		private int timeout;
		private String state;
		private Timer timer;
		private Timestamp minTime = new Timestamp();
		private Timestamp maxTime = new Timestamp();
		private Timestamp localTime = new Timestamp();

		public Event(){
		}
		public String getType() {
			return type;
		}
		public void setType(String _type) {
			this.type = _type;
		}
		
		public TransactionThread getTransctionThread() {
			return transactionThread;
		}
		public void setTransactionThread(TransactionThread _transactionThread) {
			this.transactionThread = _transactionThread;
		}
		public Timestamp getMinTime() {
			return minTime;
		}
		public void setMinTime(Timestamp minTime) {
			this.minTime = minTime;
		}
		public Timestamp getMaxTime() {
			return maxTime;
		}
		public void setMaxTime(Timestamp maxTime) {
			this.maxTime = maxTime;
		}
		public boolean threadEventHappensBefore(Event e)
		{
			if(e.getTransctionThread().getThisThread().getId() == this.getTransctionThread().getThisThread().getId())
				if(e.getLocalTime().compareTo(this.getLocalTime()) > 0)
						return true;
			return false;
		}
		
		public Timestamp getLocalTime() {
			return localTime;
		}
		
		public void setLocalTime(Timestamp localTime) {
			this.localTime = localTime;
		}
		
		public int getTimeout() {
			return timeout;
		}
		public void setTimeout(int timeout) {
			this.timeout = timeout;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public TransId getTransactionId() {
			return transactionId;
		}
		public void setTransactionId(TransId transactionId) {
			this.transactionId = transactionId;
		}
		public Timer getTimer() {
			return timer;
		}
		public void setTimer(Timer timer) {
			this.timer = timer;
		}
	}
