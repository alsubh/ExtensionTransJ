package Tester;

import javax.transaction.NotSupportedException;

import com.arjuna.ats.internal.arjuna.objectstore.CacheStore;
import com.arjuna.ats.internal.jta.transaction.arjunacore.BaseTransaction;
import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionImple;
import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionManagerImple;
import com.arjuna.ats.internal.jta.xa.XID;
import com.arjuna.ats.jta.TransactionManager;
import com.arjuna.ats.jta.xa.XidImple;
import com.arjuna.mw.wsas.exceptions.SystemException;
import com.sun.beans.util.Cache;

import umjdt.concepts.TransId;
import umjdt.concepts.Transaction;
import java.lang.Object.*;
import org.infinispan.transaction.lookup.JBossStandaloneJTAManagerLookup;

public class test2 
{
	TransId  id;
	XidImple xidImp = new XidImple();
		
	public void test1() throws Exception 
	{	
	
			//TransactionImple tImp= new TransactionImple(50);
			TransactionManagerImple tm = new TransactionManagerImple();
			//JBossStandaloneJTAManagerLookup lookup = new JBossStandaloneJTAManagerLookup();
			//TransactionManager tmm= (TransactionManager) lookup.getTransactionManager();
			tm.begin();
			int status= tm.getStatus();
			System.out.println(status);
			int timeout= tm.getTimeout();
			System.out.println(timeout);
			javax.transaction.Transaction trans= tm.getTransaction();
			System.out.println(trans.getStatus());
//			XID xid =  (XID) tm.getTransaction().commit();
//			System.out.println("xid=" + xid);
//			System.out.println("xid=" + xid.toString());
			
//			id= new TransId(xid);
			Transaction t1 = new Transaction(id);
	
			XID xid2 = xidImp.getXID();
			System.out.println("xid=" + xid2);
			System.out.println("xid=" + xid2.toString());
			
			TransId id2= new TransId(xid2);
			Transaction t2 = new Transaction(id2);
	}
	
	public static void main(String[] args) throws Exception 
	{
		test2 c2 =  new test2();
		c2.test1();
	}
}
