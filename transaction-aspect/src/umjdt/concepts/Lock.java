package umjdt.concepts;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import umjdt.util.AccessType;

public class Lock implements umjdt.util.Lock
{
	
	private TID tid;
	private Thread owner;
	private boolean reenterant;
	private int transactions;

	public Lock()
	{
	}
	
	public Lock(TID _tid, Object __lock, Thread _owner)
	{
		this.owner=_owner;
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

