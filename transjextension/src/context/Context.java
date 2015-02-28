package context;

import java.util.UUID;
import transaction.*;
import utilities.BackgroundThread;
import utilities.TransactionType;
import joinpoint.TransJP;

public class Context 
{
	private UUID contextID;
	private UUID joinpointID;
	private TransJP transJp;
	
	private BackgroundThread contextThread;
	
	public Context()
	{
		initialization();
	}
	
	public Context(TransJP _transJp)
	{
		setTransJp(_transJp);
		initialization();
	}
	
	public Context(TID _tid, Transaction _transaction, TransJP _transJP, TransactionType _type)
	{
		setTransJp(_transJP);
		initialization();
	}

	private void initialization() 
	{
		contextID = UUID.randomUUID();
		setContextThread(BackgroundThread.getInstance(contextID.toString(), this));
	}

	public BackgroundThread getContextThread() 
	{
		return contextThread;
	}

	public void setContextThread(BackgroundThread contextThread) 
	{
		this.contextThread = contextThread;
	}

	public TransJP getTransJp() 
	{
		return transJp;
	}

	public void setTransJp(TransJP transJp) 
	{
		this.transJp = transJp;
	}

	public UUID getJoinpointID() {
		return joinpointID;
	}

	public void setJoinpointID(UUID joinpointID) {
		this.joinpointID = joinpointID;
	}
}
