package umjdt.contexts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;

import umjdt.concepts.SubTransaction;
import umjdt.concepts.TransId;
import umjdt.concepts.Transaction;
import umjdt.util.thread.TransactionThread;
import utilities.Participant;

public class Context implements Serializable{

	// collection of information 
		// TID, lock, snapshot, object of timestamp, and 
	
	private String name;
	private String type;
	private int numberOfParticipants = 1;
	private Participant workingParticipant = null;
	private Hashtable<String, Transaction> threads;
	
	public Context()
	{
		ContextThreads= new MultiValueMap<>();
		threads = new ArrayList<TransactionThread>();
	}
	
	public void assignParticipant(Participant member)
	{
		// log The context will be assigned a new participant -" + Thread.currentThread().getName
		workingParticipant = member;
		member.setCurrentContext(this);
		// log The participant (Thread.currentThread().getName()  is now working on behalf of the context).
	}
	
	public void dismissParticipant(Participant member)
	{
		// log The context will dismiss its participant -" + Thread.currentThread().getName()
		if(workingParticipant == member)
		{
			workingParticipant = null;
			member.setCurrentContext(null);
		}
		// log:  Thread.currentThread().getName() that has been dismissed from the context.
		//The context will now inactivate itself
		inactivateContext();
		//The context has been inactivated.
	}
	public boolean hasParticipant(Participant participant)
	{
		return (participant == workingParticipant);
	}
	
	public void inactivateContext()
	{
		ContextController.inactivateContext(this);
	}
	
	public void participantPerformsWork(Participant member, Object object, String methodName, Object[] args)
	{}
	
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void addContextThread (TransactionThread _transactionThread)
	{
		threads.add(_transactionThread);
	}
}