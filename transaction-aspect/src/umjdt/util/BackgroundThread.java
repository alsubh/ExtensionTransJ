package umjdt.util;

import java.util.Hashtable;
import java.util.UUID;

import umjdt.concepts.Transaction;
import umjdt.joinpoints.TransJP;

import context.Context;

public class BackgroundThread
{
	private static BackgroundThread instance;
	
	private Context context;
	private Transaction transaction;
	private TransJP transJp;
	private Hashtable<String, Thread> childThreads;
	private String threadlevel;
	private Thread thread;
	private String threadName;
	
	private BackgroundThread(String _threadNameId)
	{
		initilization(_threadNameId);
	}
	
	private BackgroundThread(String _threadNameId, Transaction _transaction)
	{
		initilization(_threadNameId);
		this.setTransaction(_transaction);
	}
	
	private BackgroundThread(String _threadNameId, Context _context)
	{
		this.initilization(_threadNameId);
		this.setContext(_context);
	}

	private BackgroundThread(String _threadNameId, TransJP _transjp)
	{
		this.initilization(_threadNameId);
		this.setTransJp( _transjp);
	}

	private void initilization(String _threadNameId) 
	{
		this.setThread(Thread.currentThread());
		this.setThreadlevel(TransactionType.stringForm(TransactionType.TOP_LEVEL));
		this.context =new Context();
		this.childThreads= new Hashtable<>();
	}
	
	public static synchronized BackgroundThread getInstance(String _threadNameId)
	{
        if(instance == null)
        {
            instance = new BackgroundThread(_threadNameId);
        }
        return instance;
    }
	
	public static synchronized BackgroundThread getInstance(String _threadNameId, Context _context)
	{
        if(instance == null){
            instance = new BackgroundThread(_threadNameId, _context);
        }
        return instance;
    }
	
	public static synchronized BackgroundThread getInstance(String _threadNameId, TransJP _transjp)
	{
        if(instance == null){
            instance = new BackgroundThread(_threadNameId, _transjp);
        }
        return instance;
    }
	
	public static synchronized BackgroundThread getInstance(String _threadNameId, Transaction _transaction)
	{
        if(instance == null)
        {
            instance = new BackgroundThread(_threadNameId, _transaction);
        }
        return instance;
    }
	
	public  void addChildThread(String childThreadName, Thread _thread)
	{
		this.childThreads.put(childThreadName, _thread);
	}
	
	public Context getContext() 
	{
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Hashtable<String, Thread> getChildThreads() {
		return childThreads;
	}

	public void setChildThreads(Hashtable<String, Thread> childThreads) {
		this.childThreads = childThreads;
	}

	public TransJP getTransJp() {
		return transJp;
	}

	public void setTransJp(TransJP transJp) {
		this.transJp = transJp;
	}

	public String getThreadlevel() {
		return threadlevel;
	}

	public void setThreadlevel(String threadlevel) {
		this.threadlevel = threadlevel;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public String getName() {
		return threadName;
	}

	public void setName(String name) {
		this.threadName = name;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
}