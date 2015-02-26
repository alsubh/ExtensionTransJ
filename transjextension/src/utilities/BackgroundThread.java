package utilities;

import java.util.Hashtable;

import joinpoint.TransJP;

import context.Context;

public class BackgroundThread extends Thread implements Runnable 
{
	private String name;
	private Context context;
	private TransJP transJp;
	private Hashtable<String, Thread> childThreads;
	
	public BackgroundThread(String nameId)
	{
		super(nameId);
		context =new Context();
		childThreads= new Hashtable<>();
	}
	
	public BackgroundThread(String _threaNameId, Context _context)
	{
		super(_threaNameId);
		this.context =new Context();
		childThreads= new Hashtable<>();
	}
	
	public BackgroundThread(String nameId, TransJP _transjp)
	{
		super(nameId);
		this.transJp = _transjp;
		childThreads= new Hashtable<>();
	}
	public void addChildThread(String childThreadName, Thread _thread)
	{
		childThreads.put(childThreadName, _thread);
	}
	private static void processImplementation(final Context context, final String itemId) 
	{
	    
	}
	
	@Override
	public void run()
	{
		processImplementation(context,itemId);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Context getContext() {
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
}