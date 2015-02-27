package transaction;

import java.io.Serializable;
import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionImple;
import umjdt.TransactionEvent;
import utilities.*;

import java.util.*;
import java.util.logging.Logger;

public class Transaction extends TransactionImple implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger log = Logger.getLogger(this.getClass().getName()); 
	
	private TID tid;
	private int status;
	private int timeout;
	private Timestamp timestamp;
	private int transactionType;
	private Transaction parentTransaction;
	private TransactionThread transactionThread;// make the transaction associated with current thread
	private TransactionManager transactionManager= new TransactionManager();
	private List<TransactionEvent> events = new ArrayList<TransactionEvent>();
	private List<Operation> operations = new ArrayList<Operation>();
	private HashMap resources;
	private Hashtable<TID, SubTransaction> _ChildTransactions;
	private Hashtable<String, Thread> _childThreads;
	
	
	public Transaction() 
	{
		super();
		//this.multiOperationMap = HashMultimap.create();
		this.resources = new HashMap();
		this._ChildTransactions=new Hashtable<TID, SubTransaction>();
		this._childThreads= new Hashtable<String, Thread>();
		this.timestamp = new Timestamp();
		addThread();
	}

	public Transaction(int timeout) 
	{
		super(timeout);
		//this.multiOperationMap = HashMultimap.create();
		this.resources = new HashMap();
		this._ChildTransactions=new Hashtable<TID, SubTransaction>();
		this._childThreads= new Hashtable<String, Thread>();
		this.timestamp = new Timestamp(timeout);
		addThread();
	}
	
	/**
	 * Register the current thread with the transaction. This operation is not affected by the state of the transaction.
	 */
	public boolean addThread()
	{
		return addThread(TransactionThread.currentTransaction());
	}
	
	public boolean addThread(Transaction _transaction)
	{
		if(_transaction !=null)
		{
			TransactionThread.pushTransaction(this);
			return true;
		}
		return false;
	}
		
	 /**
     * Remove a child transaction.
     */
    public final boolean removeChildTransaction (Transaction trans)
    {
        if (trans == null)
            return false;

        boolean result = false;

        criticalStart();

        synchronized (this)
        {
            if (_ChildTransactions != null)
            {
                _ChildTransactions.remove(trans.getTId());
                result = true;
            }
        }
        criticalEnd();
        return result;
    }
    
	/**
	 * @return the number of threads associated with this transaction.
	 * 
	 */
	public final int activeThreads ()
	{
		 if (_childThreads != null)
			 return _childThreads.size();
		 else
			 return 0;
	}
    
	/**
     * @return the TID that the transaction's intentions list will be saved under.
     */
    public TID getSavingId ()
    {
        return getTId();
    }

    public String toString ()
    {
        return new String("Transaction: " + getTId() + " status: "
                + Status.stringForm(status));
    }
    
    /**
     * The following function returns the TID of the top-level transaction. If
     * this is the top-level transaction then it is equivalent to calling
     * getId().
     *
     */

    public final TID topLevelActionId ()
    {
        Transaction root = this;

        while (root.parent() != null)
            root = root.parent();

        return root.getTId();
    }

    /**
     * @return a reference to the top-level transaction. If this is the
     *         top-level transaction then a reference to itself will be
     *         returned.
     */

    public final Transaction topLevelTransaction ()
    {
        Transaction root = this;

        while (root.parent() != null)
            root = root.parent();

        return root;
    }
    
    /**
     * @return a reference to the parent Transaction
     */

    public final Transaction parent ()
    {
        if (transactionType == TransactionType.NESTED)
            return parentTransaction;
        else
            return null;
    }
   
    /**
     * Add the current thread to the list of threads associated with this
     * transaction.
     */
    public final boolean addChildThread ()
    {
        return addChildThread(Thread.currentThread());
    }
    
    /**
     * Add the specified thread to the list of threads associated with this
     * transaction.
     */
    public final boolean addChildThread (Thread t)
    {
        if (t == null)
            return false;

        boolean result = false;

        criticalStart();

        synchronized (this)
        {
            if (status <= Status.ABORTING)
            {
                if (_childThreads == null)
                    _childThreads = new Hashtable<String, Thread>();
                _childThreads.put(ThreadUtil.getThreadId(t), t); // makes sure so we don't get duplicates

                result = true;
            }
        }
        criticalEnd();
        return result;
    }

    /**
     * Remove a child thread. The current thread is removed.
     */
    public final boolean removeChildThread ()
    {
        return removeChildThread(ThreadUtil.getThreadId());
    }

    /**
     * Defines the start of a critical region by setting the critical flag. If
     * the signal handler is called the class variable abortAndExit is set. The
     * value of this variable is checked in the corresponding operation to end
     * the critical region.
     */

    protected final void criticalStart ()
    {
        //	_lock.lock();
    }

    /**
     * Defines the end of a critical region by resetting the critical flag. If
     * the signal handler is called the class variable abortAndExit is set. The
     * value of this variable is checked when ending the critical region.
     */

    protected final void criticalEnd ()
    {
        //	_lock.unlock();
    }
    
    public final boolean removeChildThread (String threadId)
    {

        if (threadId == null)
            return false;

        boolean result = false;

        criticalStart();

        synchronized (this)
        {
            if (_childThreads != null)
            {
                _childThreads.remove(threadId);
                result = true;
            }
        }

        criticalEnd();

        return result;
    }

    /**
     * Add a new child transaction to the transaction.
     */
    public final boolean addChildTransaction (SubTransaction trans)
    {
        if (trans == null)
            return false;

        boolean result = false;
        criticalStart();
        synchronized (this)
        {
               /*
                * Must be <= as we sometimes need to do processing during commit phase.
               */

            if (status <= Status.ABORTING)
            {
                if (_ChildTransactions == null)
                    _ChildTransactions = new Hashtable<TID, SubTransaction>();
                _ChildTransactions.put(trans.getTId(), trans);
                result = true;
            }
        }

        criticalEnd();
        return result;
    }
    
    public final boolean isAncestor (TID ancestor)
    {
        boolean res = false;
        
        if (getTId().equals(ancestor)) /* actions are their own ancestors */
            res = true;
        else
        {
            if ((parentTransaction != null) && (transactionType != TransactionType.TOP_LEVEL))
                res = parentTransaction.isAncestor(ancestor);
        }
        return res;
    }
    
 	@Override
 	public boolean equals(Object _obj){ 
 		Transaction  tempTransaction = (Transaction)_obj;
 		if(tempTransaction.getTId().equals(this.getTId()))
 			return true;
 		return false;
 	}

    public final int typeOfTransaction ()
    {
        return transactionType;
    }
	
	public TID getTId(){
		return tid;
	}

	public void setTId(TID _id) {
		this.tid = _id;
	}
	
	public int getTimeout() 
	{
		return timeout;
	}

	public void setTimeout(int timeout) 
	{
		this.timeout = timeout;
	}
	public List<Operation> getOperations() 
	{
		return operations;
	}

	public void setOperations(List<Operation> operations) 
	{
		this.operations = operations;
	}

	public TransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public HashMap getResources() {
		return resources;
	}

	public void setResources(HashMap resources) {
		this.resources = resources;
	}

	public TransactionThread getTransactionThread() {
		return transactionThread;
	}

	public void setTransactionThread(TransactionThread _transactionThread) {
		this.transactionThread = _transactionThread;
	}
	
	public boolean addEvent(TransactionEvent event)
	{
		if(event !=null)
		{
			events.add(event);
			return true;
		}
		return false;
	}
	
	public int getTransactionType()
	{
		if(this.getChildTransactions()> 1)
			return TransactionType.NESTED;
		return TransactionType.FLAT;
	}
	
	public int getChildTransactions()
	{
		int number = this._ChildTransactions.size();
		return number;
	}
}