package umjdt.concepts;

import java.io.Serializable;
import java.util.*;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;

import umjdt.Events.Event;
import umjdt.Events.TransactionEvent;
import umjdt.util.CheckedTransaction;
import umjdt.util.thread.ThreadUtil;
import umjdt.util.*;

public class Transaction implements Serializable{//baseTransaciton

	private static final long serialVersionUID = 1L;
	private TransId id;
	private String currentState;
	private int timeout;
	
	private TransactionThread transactionThread;// association Transaction with thread
	private MultiMap<TransactionThread, ?> multiOperationMap = new MultiValueMap(); // add the operation threads of the transaction  
	
	private List<TransactionEvent> events = new ArrayList<TransactionEvent>();
	private List<TransactionManager> listTMs = new ArrayList<>();
	private List<ResourceManager> listRMs= new ArrayList<>();
	private List<Operation> operations = new ArrayList<Operation>();
	private List<Resource> resources = new ArrayList<Resource>();
	
	private TransactionManager transactionManager = new TransactionManager();
	private ResourceManager resourceManager= new ResourceManager();
	private TwoPhaseCommitProtocol twoPhaseCommitProtocol = new TwoPhaseCommitProtocol();
	
	private Hashtable<String, Thread> _childThreads;
    private Hashtable<Transaction, Transaction> _ChildTransactions;
	 	
	public Transaction(TransId _id, String _currentState, int _timeout){	
		super();
		setId(_id);
		setCurrentState(_currentState);
		setTimeout(_timeout);
	}
	public Transaction(TransId _id, String _currentState){	
		super();
		setId(_id);
		setCurrentState(_currentState);
	}
	public Transaction(TransId _id){
		super();
		this.setId(_id);		
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
	
	// we have in JTA transaction an equal method to check the similarity between transaction
	@Override
	public boolean equals(Object _obj){ 
		Transaction  tempTransaction = (Transaction)_obj;
		if(tempTransaction.getId().equals(this.getId()))
			return true;
		return false;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public List<Resource> getResources() {
		return resources;
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
	public MultiMap<TransactionThread, ?> getMultiOperationMap() {
		return multiOperationMap;
	}
	public void setMultiOperationMap(MultiMap<TransactionThread, ?> multiOperationMap) {
		this.multiOperationMap = multiOperationMap;
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
     * Remove a child action.
     *
     * @return <code>true</code> if successful, <code>false</code>
     *         otherwise.
     */

    public final boolean removeChildTransaction (Transaction act)
    {
        if (act == null)
            return false;

        boolean result = false;

        criticalStart();

        synchronized (this)
        {
            if (_ChildTransactions != null)
            {
                _ChildTransactions.remove(act);
                result = true;
            }
        }

        criticalEnd();

        return result;
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

    public Uid getSavingUid ()
    {
        return get_uid();
    }

    /**
     * Overloads Object.toString()
     */

    public String toString ()
    {
        return new String("Transaction: " + get_uid() + " status: "
                + TransactionStatus.stringForm(transactionStatus));
    }
    
    /**
     * The following function returns the Uid of the top-level atomic action. If
     * this is the top-level transaction then it is equivalent to calling
     * get_uid().
     *
     * @return the top-level transaction's <code>Uid</code>.
     */

    public final Uid topLevelActionUid ()
    {
        Transaction root = this;

        while (root.parent() != null)
            root = root.parent();

        return root.get_uid();
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

    public boolean activate ()
    {
        return activate(null);
    }

    /**
     * Overloaded version of activate -- sets up the store, performs read_state
     * followed by restore_state. The root of the object store to use is
     * specified in the <code>root</code> parameter.
     *
     * @return <code>true</code> if successful, <code>false</code>
     *         otherwise.
     */

    public boolean activate (String root)
    {
        boolean restored = false;

        // Set up store
        ParticipantStore aaStore = getStore();

        if (aaStore == null)
            return false;

        try
        {
            // Read object state

            InputObjectState oState = aaStore.read_committed(getSavingUid(), type());

            if (oState != null)
            {
                synchronized (this)
                {
                    restored = restore_state(oState, ObjectType.ANDPERSISTENT);
                }

                oState = null;
            }
            else {
                restored = false;
            }

            return restored;
        }
        catch (ObjectStoreException e)
        {
            return false;
        }
    }

    /**
     * This operation deactivates a persistent object. It behaves in a similar
     * manner to the activate operation, but has an extra argument which defines
     * whether the object's state should be committed or written as a shadow.
     *
     * The root of the object store is <code>null</code>. It is assumed that
     * this is being called during a transaction commit.
     *
     * @return <code>true</code> on success, <code>false</code> otherwise.
     * 
     */

    public boolean deactivate ()
    {
        boolean deactivated = false;

        // Set up store
        ParticipantStore aaStore = getStore();

        if (aaStore == null)
            return false;

        try
        {
            // Write object state
            OutputObjectState oState = new OutputObjectState();

            if (save_state(oState, ObjectType.ANDPERSISTENT))
            {
                deactivated = aaStore.write_committed(getSavingUid(), type(), oState);

                oState = null;
            }
            else
            {
                deactivated = false;
            }

            /** If we failed to deactivate then output warning * */
            if (!deactivated) {
            }
        }
        catch (ObjectStoreException e)
        {
            deactivated = false;
        }

        return deactivated;
    }

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
            if (transactionStatus <= TransactionStatus.ABORTING)
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

            if (transactionStatus <= TransactionStatus.ABORTING)
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
    
    /**
     * @return the depth of the current transaction hierarchy.
     */

    public final int hierarchyDepth ()
    {
        if (currentHierarchy != null)
            return currentHierarchy.depth();
        else
            return 0; /* should never happen */
    }

    /**
     * boolean function that checks whether the Uid passed as an argument is the
     * Uid for an ancestor of the current atomic action.
     *
     * @return <code>true</code> if the parameter represents an ancestor,
     *         <code>false</code> otherwise.
     */

    public final boolean isAncestor (Uid ancestor)
    {
        boolean res = false;
        
        if (get_uid().equals(ancestor)) /* actions are their own ancestors */
            res = true;
        else
        {
            if ((parentTransaction != null) && (transactionType != ActionType.TOP_LEVEL))
                res = parentTransaction.isAncestor(ancestor);
        }

        return res;
    }

    /**
     * @return a reference to the parent Transaction
     */

    public final Transaction parent ()
    {
        if (transactionType == ActionType.NESTED)
            return parentTransaction;
        else
            return null;
    }

    public final int typeOfAction ()
    {
        return transactionType;
    }
    
    private int transactionStatus;
    private int transactionType;
    private Transaction parentTransaction;
    private int heuristicDecision;
    private CheckedTransaction _checkedTransaction; // control what happens if threads active when terminating.
    private boolean pastFirstParticipant;  // remember where we are (were) in committing during recovery
    private boolean internalError; // is there an error internal to the TM (such as write log errors, for example)

}