package context;

import java.util.Hashtable;
import java.util.List;
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
	private Transaction transaction;
	private TID tid;
	private BackgroundThread contextThread;
	private TransactionType type;
	private Hashtable<TID, SubTransaction> childTransactions;
	
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
		setTid(_tid);
		setTransaction(_transaction);
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

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public TID getTid() {
		return tid;
	}

	public void setTid(TID tid) {
		this.tid = tid;
	}

	public UUID getJoinpointID() {
		return joinpointID;
	}

	public void setJoinpointID(UUID joinpointID) {
		this.joinpointID = joinpointID;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public Hashtable<TID, SubTransaction> getChildTransactions() {
		return childTransactions;
	}

	public void setChildTransactions(Hashtable<TID, SubTransaction> childTransactions) {
		this.childTransactions = childTransactions;
	}
}
