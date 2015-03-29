package umjdt.util;

import java.util.Hashtable;

import umjdt.concepts.Xid;
import context.Context;

public class TransactionJPRegistry
{
	
	private static Hashtable<Xid, Context> contextRegistry = new Hashtable<>();
	
	public Context lookup(Xid cid)
	{
		Context result= new Context();
		return result;
	}

}
