package umjdt.joinpoints;

import java.util.List;
import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import umjdt.Events.Event;
import umjdt.concepts.Transaction;
import umjdt.util.TransactionType;

public class TransJP
{
	Logger logger = Logger.getLogger(TransJP.class.toString());
	
	private Transaction tranaction;
	private List<Event> events;
	private TransactionType type;
	
	public TransJP()
	{
		
	}
	
	public TransJP(TransJP _transJp)
	{
		this.tranaction =_transJp.getTranaction();
		this.type= _transJp.getType();
		this.events=_transJp.getEvents();
	}
	
	public void set(TransJP _transJp)
	{
		this.tranaction =_transJp.getTranaction();
		this.type= _transJp.getType();
		this.events= _transJp.getEvents();
	}

	public Transaction getTranaction() {
		return tranaction;
	}

	public void setTranaction(Transaction tranaction) {
		this.tranaction = tranaction;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
}
