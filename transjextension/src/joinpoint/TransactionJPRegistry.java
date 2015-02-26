package joinpoint;

import java.util.Hashtable;

import context.Context;

import transaction.TID;

public class TransactionJPRegistry
{
	
	private static Hashtable<TID, Context> contextRegistry = new Hashtable<>();
	
	public Context lookup(TID cid)
	{
		Context result= new Context();
		return result;
	}

}
