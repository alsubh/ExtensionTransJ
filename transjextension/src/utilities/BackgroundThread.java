package utilities;

import java.util.Hashtable;
import java.util.UUID;

import joinpoint.TransJP;

import context.Context;

public class BackgroundThread
{
	private static BackgroundThread instance;
	
	static Context context;
	private TransJP transJp;
	private Hashtable<String, Thread> childThreads;
	private String threadlevel;
	private Thread thread;
	private String threadName;
	
	private BackgroundThread(String _threadNameId)
	{
		initilization(_threadNameId);
	}
	
	private BackgroundThread(String _threadNameId, Context _context)
	{
		initilization(_threadNameId);
		context = _context;
	}

	private BackgroundThread(String _threadNameId, TransJP _transjp)
	{
		initilization(_threadNameId);
		transJp = _transjp;
	}

	private void initilization(String _threadNameId) 
	{
		setThread(Thread.currentThread());
		setThreadlevel(TransactionType.stringForm(TransactionType.TOP_LEVEL));
		context =new Context();
		childThreads= new Hashtable<>();
		
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
}