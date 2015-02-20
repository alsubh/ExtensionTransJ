package umjdt.concepts;

import java.io.Serializable;
import java.util.*;

import javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener;

import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionImple;
import com.google.common.collect.HashMultimap;
import umjdt.Events.TransactionEvent;
import umjdt.util.CheckedTransaction;
import umjdt.util.thread.BKThreadTransaction;
import umjdt.util.thread.ThreadUtil;
import umjdt.util.*;

public class Transaction extends TransactionImple implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private TransId id;
	private String currentState;
	private int timeout;
	private TransactionThread transactionThread;// make the transaction associated with current thread
	private int transactionStatus;
    private int transactionType;
    private Transaction parentTransaction;
    private CheckedTransaction _checkedTransaction;
    private TransactionManager transactionManager= new TransactionManager();
    private List<TransactionEvent> events = new ArrayList<TransactionEvent>();
	private List<Operation> operations = new ArrayList<Operation>();
	
	//private MultiMap<TransactionThread, ?> multiOperationMap = new MultiValueMap(); // add the operation threads of the transaction  
	//private HashMultimap<Transaction, List<Operation>> multiOperationMap;
	private HashMultimap<Transaction, List<SubTransaction>> childTransactionMap;
	private HashMap resources = new HashMap();
	
	private Hashtable<String, Thread> _childThreads;
    private Hashtable<Transaction, SubTransaction> _ChildTransactions;
	
	public Transaction() {
		super();
		//this.multiOperationMap = HashMultimap.create();
		this.childTransactionMap=HashMultimap.create();
	}

	public Transaction(int timeout) {
		super(timeout);
		//this.multiOperationMap = HashMultimap.create();
		this.childTransactionMap=HashMultimap.create();
	}
	
	
	//Register the current thread with the transaction. This operation is not affected by the state of the transaction.
	public boolean addThread()
	{
		return addThread(Thread.currentThread());
	}
	public boolean addThread(Thread _thread)
	{
		if(_thread !=null)
		{
			BKThreadTransaction.pushTransaction(this);
			return true;
		}
		return false;
	}
	
		
	 /**
     * Remove a child transaction.
     *
     * @return <code>true</code> if successful, <code>false</code>
     *         otherwise.
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
                _ChildTransactions.remove(trans);
                result = true;
            }
        }

        criticalEnd();

        return result;
    }
    
	// @return the number of threads associated with this transaction.
	public final int activeThreads ()
	{
		 if (_childThreads != null)
			 return _childThreads.size();
		 else
			 return 0;
	}


    /**
     * Add the specified CheckedTransaction object to this transaction.
     *
     * @see com.arjuna.ats.arjuna.coordinator.CheckedTransaction
     */

    protected final synchronized void setCheckedTransaction (CheckedTransaction c)
    {
        criticalStart();

        _checkedTransaction = c;

        criticalEnd();
    }

    /**
     * @return the Uid that the transaction's intentions list will be saved
     *         under.
     */

    public TransId getSavingUid ()
    {
        return getId();
    }

    public String toString ()
    {
        return new String("Transaction: " + getId() + " status: "
                + Status.stringForm(transactionStatus));
    }
    
    /**
     * The following function returns the transId of the top-level atomic action. If
     * this is the top-level transaction then it is equivalent to calling
     * getId().
     *
     * @return the top-level transaction's <code>Uid</code>.
     */

    public final TransId topLevelActionUid ()
    {
        Transaction root = this;

        while (root.parent() != null)
            root = root.parent();

        return root.getId();
    }

    /**
     * @return a reference to the top-level transaction. If this is the
     *         top-level transaction then a reference to itself will be
     *         returned.
     */

    public final Transaction topLevelAction ()
    {
        Transaction root = this;

        while (root.parent() != null)
            root = root.parent();

        return root;
    }

    /**
     * Overloaded version of activate -- sets up the store, performs read_state
     * followed by restore_state. The store root is <code>null</code>.
     *
     * @return <code>true</code> if successful, <code>false</code>
     *         otherwise.
     */

   
    /**
     * Add the current thread to the list of threads associated with this
     * transaction.
     *
     * @return <code>true</code> if successful, <code>false</code>
     *         otherwise.
     */

    public final boolean addChildThread () // current thread
    {
        return addChildThread(Thread.currentThread());
    }

    /**
     * Add the specified thread to the list of threads associated with this
     * transaction.
     *
     * @return <code>true</code> if successful, <code>false</code>
     *         otherwise.
     */

    public final boolean addChildThread (Thread t)
    {
        if (t == null)
            return false;

        boolean result = false;

        criticalStart();

        synchronized (this)
        {
            if (transactionStatus <= Status.ABORTING)
            {
                if (_childThreads == null)
                    _childThreads = new Hashtable<String, Thread>();

                _childThreads.put(ThreadUtil.getThreadId(t), t); // makes sure so we don't get
                // duplicates

                result = true;
            }
        }

        criticalEnd();

        return result;
    }

    /*
      * Can be done at any time (Is this correct?)
      */

    /**
     * Remove a child thread. The current thread is removed.
     *
     * @return <code>true</code> if successful, <code>false</code>
     *         otherwise.
     */

    public final boolean removeChildThread () // current thread
    {
        return removeChildThread(ThreadUtil.getThreadId());
    }

    /**
     * Remove the specified thread from the transaction.
     *
     * @return <code>true</code> if successful, <code>false</code>
     *         otherwise.
     */

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
     * Add a new child action to the atomic action.
     *
     * @return <code>true</code> if successful, <code>false</code>
     *         otherwise.
     */

    public final boolean addChildTransaction (Transaction act)
    {

        if (act == null)
            return false;

        boolean result = false;

        criticalStart();

        synchronized (this)
        {
            /*
                * Must be <= as we sometimes need to do processing during commit
                * phase.
                */

            if (transactionStatus <= Status.ABORTING)
            {
                if (_ChildTransactions == null)
                    _ChildTransactions = new Hashtable<Transaction, Transaction>();

                _ChildTransactions.put(act, act);
                result = true;
            }
        }

        criticalEnd();

        return result;
    }
    
    public final boolean isAncestor (TransId ancestor)
    {
        boolean res = false;
        
        if (getId().equals(ancestor)) /* actions are their own ancestors */
            res = true;
        else
        {
            if ((parentTransaction != null) && (transactionType != TransactionType.TOP_LEVEL))
                res = parentTransaction.isAncestor(ancestor);
        }

        return res;
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
    
    // we have in JTA transaction an equal method to check the similarity between transaction
 	@Override
 	public boolean equals(Object _obj){ 
 		Transaction  tempTransaction = (Transaction)_obj;
 		if(tempTransaction.getId().equals(this.getId()))
 			return true;
 		return false;
 	}

    public final int typeOfTransaction ()
    {
        return transactionType;
    }

    public String getBeginState(){
		return "begin";			
	}

	public String getCommitState(){
		return "commit";			
	}
	
	public String getAbortState(){
		return "rollback";			
	}
	
	public TransId getId(){
		return id;
	}

	public void setId(TransId _id) {
		this.id = _id;
	}
	
	public List<TransactionEvent> getEvents() {
		return events;
	}
	public void setEvents(List<TransactionEvent> _events) {
		this.events = _events;
	}
	
	public void addEvent(TransactionEvent _event){		
		events.add(_event);
	}
	
	public void removeEvent(TransactionEvent _event){
		events.remove(_event);
	}
		
	public String getCurrentState() {
		return currentState;
	}
	
	public void setCurrentState(String _currentState) {
		this.currentState = _currentState;
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

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public TransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public ResourceManager getResourceManager() {
		return resourceManager;
	}

	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	public TwoPhaseCommitProtocol getTwoPhaseCommitProtocol() {
		return twoPhaseCommitProtocol;
	}

	public void setTwoPhaseCommitProtocol(
			TwoPhaseCommitProtocol twoPhaseCommitProtocol) {
		this.twoPhaseCommitProtocol = twoPhaseCommitProtocol;
	}

	public TransactionThread getTransactionThread() {
		return transactionThread;
	}

	public void setTransactionThread(TransactionThread transactionThread) {
		this.transactionThread = transactionThread;
	}
	public List<TransactionManager> getListTMs() {
		return listTMs;
	}

	public void setListTMs(List<TransactionManager> listTMs) {
		this.listTMs = listTMs;
	}

	public List<ResourceManager> getListRMs() {
		return listRMs;
	}

	public void setListRMs(List<ResourceManager> listRMs) {
		this.listRMs = listRMs;
	}

	public HashMultimap<Transaction, List<Operation>> getMultiOperationMap() {
		return multiOperationMap;
	}

	public void setMultiOperationMap(HashMultimap<Transaction, List<Operation>> multiOperationMap) {
		this.multiOperationMap = multiOperationMap;
	}

	public HashMultimap<Transaction, List<SubTransaction>> getChildTransactionMap() {
		return childTransactionMap;
	}

	public void setChildTransactionMap(HashMultimap<Transaction, List<SubTransaction>> multiSubTransactionMap) {
		this.childTransactionMap = multiSubTransactionMap;
	}
}