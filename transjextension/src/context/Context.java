package context;

import java.util.UUID;
import transaction.*;
import utilities.BackgroundThread;
import utilities.TransactionType;
import joinpoint.TransJP;
import joinpoint.transaction.TransactionJP;

public class Context 
{
	private UUID contextID;
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
	
	public boolean occurOn(TransJP _transJp)
	{
		boolean result= false;
		
		return result;
	}
}
