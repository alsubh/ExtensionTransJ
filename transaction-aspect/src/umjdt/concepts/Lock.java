package umjdt.concepts;

import java.io.Serializable;

import com.arjuna.ats.arjuna.common.Uid;
import com.arjuna.ats.txoj.*;

public class lock extends Lock implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private TID tid;
	private Object _lock;
	private Thread owner;
	private boolean reenterant;
	private int transactions;

	public lock()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public lock(int arg0) 
	{
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public lock(Uid storeUid) 
	{
		super(storeUid);
		// TODO Auto-generated constructor stub
	}
	
	public lock(TID _tid, Object __lock, Thread _owner)
	{
		super(_tid.getUid());
		this._lock= __lock;
		this.owner=_owner;
	}
	
	public Object get_lock() {
		return _lock;
	}

	public void set_lock(Object _lock) {
		this._lock = _lock;
	}

	public Thread getOwner() {
		return owner;
	}

	public void setOwner(Thread owner) {
		this.owner = owner;
	}

	public boolean isReenterant() {
		return reenterant;
	}

	public void setReenterant(boolean reenterant) {
		this.reenterant = reenterant;
	}

	public int getTransactions() {
		return transactions;
	}

	public void setTransactions(int transactions) {
		this.transactions = transactions;
	}
}

