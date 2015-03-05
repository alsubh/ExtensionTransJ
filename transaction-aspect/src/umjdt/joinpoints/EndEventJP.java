package umjdt.joinpoints;

import java.util.TimerTask;
import java.util.logging.Level;

import umjdt.concepts.TID;
import umjdt.concepts.Transaction;
import umjdt.util.BackgroundThread;
import umjdt.util.Timestamp;
import umjdt.util.TransactionThread;

public class EndEventJP extends TransJP
{
	private TID endID; //make the event associate with the transaction
	private Timestamp endTime; 
	private TransactionThread transactionThread;
	private BackgroundThread bkThread;
	//set the end of event abort or commit (only one complete and disjoint) 
	private EndEventJP endEvent;
	private String typeOfEnd;
	
	public EndEventJP(Transaction transaction) 
	{
		super();
	}

	public EndEventJP(TransJP _transJp) 
	{
		super(_transJp);
	}

	public Timestamp getEndTime() 
	{
		return endTime;
	}
	
	public void setEndTime(Timestamp _endTime) 
	{
		this.endTime = _endTime;
	}
	
	public TransactionThread getTransactionThread() 
	{
		return transactionThread;
	}

	public void setTransactionThread(TransactionThread transactionThread) 
	{
		this.transactionThread = transactionThread;
	}
	
	public BackgroundThread getBkThread() 
	{
		return bkThread;
	}

	public void setBkThread(BackgroundThread bkThread) 
	{
		this.bkThread = bkThread;
	}
	
	public TID getEndID() 
	{
		return endID;
	}

	public void setEndID(TID _endID) 
	{
		this.endID = _endID;
	}
	
	public EndEventJP getEndEvent() 
	{
		return endEvent;
	}

	public void setEndEvent(EndEventJP event) 
	{
		this.endEvent = event;
	}
	public String getTypeOfEnd() 
	{
		return typeOfEnd;
	}

	public void setTypeOfEnd(String typeOfEnd) 
	{
		this.typeOfEnd = typeOfEnd;
	}
	
	class EndTask extends TimerTask 
	{
	    public void run() {
	    	logger.log(Level.INFO, EndTask.class.toString());
	      System.out.println(" End Time!");
	      //System.exit(0); //Stops everything 
	    }
	 }
}
