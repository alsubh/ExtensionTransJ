/**
 * 
 */
package TransactionJoinpointTracker;

<<<<<<< HEAD
import org.apache.log4j.Logger;
=======
import java.util.Map;

import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

import com.arjuna.ats.arjuna.common.Uid;
import com.arjuna.ats.internal.jta.transaction.arjunacore.BaseTransaction;
import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionImple;
import com.arjuna.ats.internal.jta.xa.TxInfo;
>>>>>>> a4f88eb857dcc783b6bd1a3a8e9566d37c3c731e

import umjdt.joinpoints.BeginEventJP;

/**
 * @author AnasAlsubh
 *
 */
public abstract aspect InitiatorJPTracker
{
	private Logger logger = Logger.getLogger(TransactionTracker.class);
	protected BeginEventJP beginEventJp=null;
	
	/**
	 * Begin: Create a new transaction and associate it with the current thread.
	 * @param before begin:  timeout , _supportSubtransactions
	 * after begin : TransactionID, TransactionThread
<<<<<<< HEAD
	 */
	 
	pointcut BeginTransactionStyle1(): 
		(call(* javax..*Transaction*+.begin(..)) 
				|| (call(* com.arjuna..BaseTransaction+.begin(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (call(* com.arjuna..Transaction+.begin(..))));//arjuna.ats.jta.transaction.Transaction+ 

	
	pointcut BeginTransactionStyle2(int timeout): 
		(call(* javax..*Transaction*+.begin(..)) 
				|| (call(* com.arjuna..BaseTransaction+.begin(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (call(* com.arjuna..Transaction+.begin(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& args(timeout); 
	
	
	pointcut BeginTransactionStyle3(): 
		(execution(* javax..*Transaction*+.begin(..)) 
				|| (execution(* com.arjuna..BaseTransaction+.begin(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (execution(* com.arjuna..Transaction+.begin(..))));//arjuna.ats.jta.transaction.Transaction+ 
				 
	
	
	pointcut BeginTransactionStyle4(int timeout): 
		(execution(* javax..*Transaction*+.begin(..)) 
				|| (execution(* com.arjuna..BaseTransaction+.begin(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (execution(* com.arjuna..Transaction+.begin(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& args(timeout); 
=======
	 * 
	 * javax.transaction.Transaction+;
	 * ats.internal.jta.transaction.arjunacore.BaseTransaction.
	 * arjuna.ats.jta.transaction.Transaction+
	 * 
	 */
	 
	pointcut BeginTransactionStyle(): call(* *Transaction*+.begin(..));
	pointcut BeginTransactionServerStyle1(TransactionManager tm):call(* *Transaction*+.begin(..)) && target(tm);
	pointcut BeginTransactionServerStyle2(TransactionManager tm, int timeout): call(* *Transaction*+.begin(..)) && target(tm) && args(timeout); 
	pointcut BeginTransactionServerStyle3(TransactionManager tm): execution(* *Transaction*+.begin(..)) && target(tm);
	pointcut BeginTransactionServerStyle4(TransactionManager tm,int timeout): execution(* *Transaction*+.begin(..)) && target(tm) && args(timeout);
	
	pointcut BeginTransactionClientStyle(UserTransaction utx):call(* *Transaction*+.begin(..))&& target(utx);
	
	before() : BeginTransactionStyle()
	{
		BeginEventJP beginEventJp = new BeginEventJP();
		passContextInfo(beginEventJp, null, null, 0);
	}
	
	after() : BeginTransactionStyle()
	{
		BeginEventJP beginEventJp;
		Transaction transaction;
		TransactionManager tm;
		TransactionImple t =new TransactionImple(0);
		int status;
		Uid transactionUid;
		Map<Uid, Transaction> subtransactions;
		Map<XAResource, TxInfo> resources;
		Xid globalID;
		
		try
		{ 
			beginEventJp = new BeginEventJP();
			transaction = TransactionImple.getTransaction();
			transactionUid = TransactionImple.getTransaction().get_uid();
			globalID =TransactionImple.getTransaction().getTxId();
			status= transaction.getStatus();
			subtransactions = TransactionImple.getTransactions();
			resources = TransactionImple.getTransaction().getResources();
			
			
			passContextInfo(beginEventJp, tm, transaction, status);
		} 
		catch (SystemException e) 
		{
			e.printStackTrace();
		}
	}

	before(TransactionManager tm) : BeginTransactionStyle3(tm)
	{
		BeginEventJP beginEventJp = new BeginEventJP();
		passContextInfo(beginEventJp, tm, null, 0);
	}
	
	after(TransactionManager tm) returning (Transaction transaction): 
		BeginTransactionStyle3(tm)
	{
		BeginEventJP beginEventJp;
		try
		{ 
			beginEventJp = new BeginEventJP();
			transaction = tm.getTransaction();
			int status= transaction.getStatus();
			passContextInfo(beginEventJp, tm, transaction, status);
		} 
		catch (SystemException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void passContextInfo(BeginEventJP _beginEventJp,
			TransactionManager _tm, Transaction _transaction, int _status)
	{
		BeginEventJP beginJp= beginEventJp;
		beginJp.setTm(_tm);
		beginJp.setTransaction(_transaction);
		beginJp.setStatus(_status);
	}
>>>>>>> a4f88eb857dcc783b6bd1a3a8e9566d37c3c731e
}
