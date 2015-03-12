/**
 * 
 */
package TransactionJoinpointTracker;

import java.util.Map;

import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import javax.transaction.xa.Xid;

import org.apache.log4j.Logger;

import com.arjuna.ats.arjuna.common.Uid;
import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionImple;

import umjdt.concepts.TID;
import umjdt.concepts.Transaction;
import umjdt.joinpoints.BeginEventJP;

/**
 * @author AnasAlsubh
 *
 */
public abstract aspect InitiatorJoinpointTracker
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
	 * after begin : TransactionID, TransactionThread
	 * 
	 * javax.transaction.Transaction+;
	 * ats.internal.jta.transaction.arjunacore.BaseTransaction.
	 * arjuna.ats.jta.transaction.Transaction+
	 * 
	 */
	 
	pointcut BeginTransactionStyle(): call(* javax..*Transaction*+.begin(..)) || call(* com.arjuna..BaseTransaction+.begin(..)) || call(* com.arjuna..*Transaction*+.begin(..));
	
	pointcut BeginTransactionStyle2(int timeout): (call(* javax..*Transaction*+.begin(..)) || (call(* com.arjuna..BaseTransaction+.begin(..))) || (call(* com.arjuna..Transaction+.begin(..)))) && args(timeout);
	
	/*
	pointcut BeginTransactionServerStyle1(TransactionManager tm):call(* *Transaction*+.begin(..)) && target(tm);
	pointcut BeginTransactionServerStyle2(TransactionManager tm, iuint timeout): call(* *Transaction*+.begin(..)) && target(tm) && args(timeout); 
	pointcut BeginTransactionServerStyle3(TransactionManager tm): execution(* *Transaction*+.begin(..)) && target(tm);
	pointcut BeginTransactionServerStyle4(TransactionManager tm,int timeout): execution(* *Transaction*+.begin(..)) && target(tm) && args(timeout);
	pointcut BeginTransactionClientStyle(UserTransaction utx):call(* *Transaction*+.begin(..))&& target(utx);
	*/
		
	before() : BeginTransactionStyle()
	{
		BeginEventJP beginEventJp = new BeginEventJP();
		passContextInfo(beginEventJp, thisJoinPoint.getTarget(),null, null,null,0,0);
	}
	
	//after 
	void around() throws SystemException: BeginTransactionStyle()
	{
		proceed();
		Object target= thisJoinPoint.getTarget();	
		beginContextInfo(target,-1);
	}
	
	before(int time) : BeginTransactionStyle2(time)
	{
		BeginEventJP beginEventJp = new BeginEventJP(time);
		passContextInfo(beginEventJp, thisJoinPoint.getTarget(),null, null,null,0,time);
	}
	
	//after
	void around(int time) throws SystemException: BeginTransactionStyle2(time)
	{
		proceed(time);
		Object target= thisJoinPoint.getTarget();	
		beginContextInfo(target, time);
	}
	
	private void beginContextInfo(Object target, int time) throws SystemException 
	{
		beginEventJp = new BeginEventJP();
		//transaction currently associated with thread.
		transaction = (Transaction) TransactionImple.getTransaction();			
		transactionUid = TransactionImple.getTransaction().get_uid();
		globalID = TransactionImple.getTransaction().getTxId();
		status =TransactionImple.getTransaction().getStatus();
		if(time < 0)
			timeout = TransactionImple.getTransaction().getTimeout();
		else
			timeout = time;
		
		passContextInfo(beginEventJp, target, transaction, transactionUid, globalID, status, timeout);		
	}
	
	private void passContextInfo(BeginEventJP eventJp, Object _target, Transaction _transaction, Uid _uid, Xid _globalxid, int _status, int _timeout) 
	{	
		BeginEventJP beginJp= eventJp;
		
		if(_target !=null)
		{
			if(_target.getClass().equals(TransactionManager.class))
			{
				beginJp.setManager((TransactionManager)_target);
			}
			else if(_target.getClass().equals(UserTransaction.class))
			{
				beginJp.setUser((UserTransaction)_target);
			}
		}
		beginJp.setTid(new TID(_globalxid, _uid));
		beginJp.setTransaction(_transaction);
		beginJp.setStatus(_status);
		beginJp.setTimeout(_timeout);
		
		Begin(beginJp);
	}
	
	public void Begin(BeginEventJP _beginEventJp)
	{
		
	}	
}
