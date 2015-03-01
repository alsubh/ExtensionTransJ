package context;

import java.util.List;
import java.util.UUID;

import joinpoint.TransJP;
import joinpoint.transaction.TransactionJP;
import transaction.Operation;
import transaction.Resource;
import transaction.TID;
import transaction.Transaction;
import utilities.BackgroundThread;
import utilities.Participant;
import utilities.Status;
import utilities.Timestamp;
import utilities.TransactionType;


public class TransactionContext extends Context
{
	private UUID tcid;
	private TransactionJP transactionJp;
	private List<Resource> resources;
	private List<Operation> operations;
	// Transaction Manager, Resource Manager, or coordinator
	private Participant participant;
	private Status status;
		
	// Thread of the joinpoints 
	private BackgroundThread transactionContextThread;
	
	public TransactionContext() 
	{
		super();
		this.setCid(UUID.randomUUID());
	}

	public TransactionContext(TransJP _transJp)
	{
		super(_transJp);
		this.setCid(UUID.randomUUID());
		this.transactionJp.setEvents(_transJp.getEvents());
		transactionContextThread = BackgroundThread.getInstance(getCid().toString(), this);
	}
	
	public TransactionContext(UUID _tcid, TransactionJP _transactionJp,
			List<Resource> _resources, List<Operation> _operations, Participant _participant)
	{
		super();
		this.setCid(UUID.randomUUID());
		this.setTransactionJp(_transactionJp);
		this.setResources(_resources);
		this.setOperations(_operations);
		this.setParticipant(_participant);
	}

	public UUID getCid() 
	{
		return tcid;
	}

	public void setCid(UUID cid) 
	{
		this.tcid = cid;
	}

	public TransactionJP getTransactionJp() 
	{
		return transactionJp;
	}

	public void setTransactionJp(TransactionJP transactionJp) 
	{
		this.transactionJp = transactionJp;
	}

	public List<Resource> getResources() 
	{
		return resources;
	}

	public void setResources(List<Resource> resources) 
	{
		this.resources = resources;
	}

	public Participant getParticipant() 
	{
		return participant;
	}

	public void setParticipant(Participant participant) 
	{
		this.participant = participant;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	
	public BackgroundThread getTransactionContextThread() {
		return transactionContextThread;
	}

	public void setTransactionContextThread(BackgroundThread transactionContextThread) {
		this.transactionContextThread = transactionContextThread;
	}
	
	public boolean occurOn(TransactionJP _transactionJp)
	{
		boolean result= false;
		if(_transactionJp.equals(_transactionJp))
		{
			result= true;
		}
		return result;
	}
}
