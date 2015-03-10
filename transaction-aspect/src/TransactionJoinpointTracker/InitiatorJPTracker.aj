/**
 * 
 */
package TransactionJoinpointTracker;

import java.util.Map;

import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import javax.transaction.xa.Xid;

import org.apache.log4j.Logger;

import com.arjuna.ats.arjuna.common.Uid;
import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionImple;

import umjdt.concepts.TID;
import umjdt.joinpoints.BeginEventJP;

/**
 * @author AnasAlsubh
 *
 */
public abstract aspect InitiatorJPTracker
{
	private Logger logger = Logger.getLogger(TransactionTracker.class);
	protected BeginEventJP beginEventJp=null;
	private Transaction transaction= null;
	private Uid transactionUid= null;;
	private Xid globalID=null;
	private int status=0;
	private int timeout=-1;
	
	/**
	 * Begin: Create a new transaction and associate it with the current thread.
	 * @param before begin:  timeout , _supportSubtransactions
	 * after begin : TransactionID, TransactionThread
	 * 
	 * javax.transaction.Transaction+;
	 * ats.internal.jta.transaction.arjunacore.BaseTransaction.
	 * arjuna.ats.jta.transaction.Transaction+
	 * 
	 */
	 
	pointcut BeginTransactionStyle(): 
		(call(* javax..*Transaction*+.begin(..)) 
				|| (call(* com.arjuna..BaseTransaction+.begin(..))) 
				|| (call(* com.arjuna..Transaction+.begin(..))))
				&& target(Transaction);
	
	pointcut BeginTransactionStyle2(int timeout): 	
		(call(* javax..*Transaction*+.begin(..)) 
			|| (call(* com.arjuna..BaseTransaction+.begin(..))) 
			|| (call(* com.arjuna..Transaction+.begin(..)))) && args(timeout);
	
	/*
	pointcut BeginTransactionServerStyle1(TransactionManager tm):call(* *Transaction*+.begin(..)) && target(tm);
	pointcut BeginTransactionServerStyle2(TransactionManager tm, int timeout): call(* *Transaction*+.begin(..)) && target(tm) && args(timeout); 
	pointcut BeginTransactionServerStyle3(TransactionManager tm): execution(* *Transaction*+.begin(..)) && target(tm);
	pointcut BeginTransactionServerStyle4(TransactionManager tm,int timeout): execution(* *Transaction*+.begin(..)) && target(tm) && args(timeout);
	pointcut BeginTransactionClientStyle(UserTransaction utx):call(* *Transaction*+.begin(..))&& target(utx);
	*/
	
	before() : BeginTransactionStyle()
	{
		BeginEventJP beginEventJp = new BeginEventJP();
		passContextInfo(beginEventJp, thisJoinPoint.getTarget(),null, null,null,0,0);
	}
	
	after(): BeginTransactionStyle()
	{
		Object target= thisJoinPoint.getTarget();	
		beginContextInfo(target,-1);
	}

	Transaction around(): BeginTransactionStyle()
	{
		Transaction result= proceed();
		beginContextInfo(thisJoinPoint.getTarget(),-1);
		return result;
	}
	
	
	before(int time) : BeginTransactionStyle2(time)
	{
		BeginEventJP beginEventJp = new BeginEventJP();
		passContextInfo(beginEventJp, thisJoinPoint.getTarget(),null, null,null,0,time);
	}
	
	after(int time): BeginTransactionStyle2(time)
	{
		Object target= thisJoinPoint.getTarget();	
		beginContextInfo(target, time);
	}

	void around(int time): BeginTransactionStyle2(time)
	{
		proceed(time);
		beginContextInfo(thisJoinPoint.getTarget(), time);
	}
	
	
	/**
	 * @param target
	 */
	private void beginContextInfo(Object target, int time) 
	{
		beginEventJp = new BeginEventJP();
		
		try
		{ 
			//transaction currently associated with thread.
			transaction = TransactionImple.getTransaction();			
			transactionUid = TransactionImple.getTransaction().get_uid();
			globalID = TransactionImple.getTransaction().getTxId();
			if(time < 0)
				timeout = TransactionImple.getTransaction().getTimeout();
			else
				timeout = time;
			status =transaction.getStatus();
			passContextInfo(beginEventJp, target, transaction, transactionUid, globalID, status, timeout);
		} 
		catch (SystemException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void passContextInfo(BeginEventJP eventJp, Object _target, Transaction _transaction, Uid _uid, Xid _globalxid, int _status, int _timeout) 
	{	
		BeginEventJP beginJp= eventJp;
		
		if((_target !=null) && (_target.getClass().equals(TransactionManager.class)))
		{
			beginJp.setManager((TransactionManager)_target);
		}
		beginJp.setXatransaction(_transaction);
		beginJp.setStatus(_status);
		beginJp.setTimeout(_timeout);
		beginJp.setTid(new TID(_globalxid, _uid));
	}
}
