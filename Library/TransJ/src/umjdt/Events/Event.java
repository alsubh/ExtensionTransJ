package umjdt.Events;

import java.util.Timer;
import java.util.UUID;

import umjdt.concepts.TransId;
import umjdt.concepts.TransactionThread;
import umjdt.util.Timestamp;

public class Event {

		private Thread theThread = new TransactionThread();
		private TransId transactionId;
		private String eventType;
		private int timeout;
		private Timer timer;
		private Timestamp minTime = new Timestamp();
		private Timestamp maxTime = new Timestamp();
		private Timestamp localTime = new Timestamp();

		public Event()
		{
			
		}
				
		public Timestamp getMinTime() 
		{
			return minTime;
		}
		
		public void setMinTime(Timestamp minTime) 
		{
			this.minTime = minTime;
		}
		
		public Timestamp getMaxTime() 
		{
			return maxTime;
		}
		
		public void setMaxTime(Timestamp maxTime) 
		{
			this.maxTime = maxTime;
		}
		
		public boolean threadEventHappensBefore(Event e)
		{
			if(e.getTheThread().getId() == this.getTheThread().getId())
				if(e.getLocalTime().compareTo(this.getLocalTime()) > 0)
						return true;
			return false;
		}
		
		public Timestamp getLocalTime() 
		{
			return localTime;
		}
		
		public void setLocalTime(Timestamp localTime) 
		{
			this.localTime = localTime;
		}
		
		public int getTimeout() 
		{
			return timeout;
		}
		public void setTimeout(int timeout) 
		{
			this.timeout = timeout;
		}
		public TransId getTransactionId() 
		{
			return transactionId;
		}
		public void setTransactionId(TransId transactionId) 
		{
			this.transactionId = transactionId;
		}
		public Timer getTimer() 
		{
			return timer;
		}
		public void setTimer(Timer timer)
		{
			this.timer = timer;
		}

		public String getEventType() 
		{
			return eventType;
		}

		public void setEventType(String eventType)
		{
			this.eventType = eventType;
		}

		public Thread getTheThread() {
			return theThread;
		}

		public void setTheThread(Thread theThread) {
			this.theThread = theThread;
		}
	}
