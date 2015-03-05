package umjdt.util;

import java.util.Hashtable;

import umjdt.concepts.TID;
import context.Context;

public class TransactionJPRegistry
{
	
	private static Hashtable<TID, Context> contextRegistry = new Hashtable<>();
	
	public Context lookup(TID cid)
	{
		Context result= new Context();
		return result;
	}

}
