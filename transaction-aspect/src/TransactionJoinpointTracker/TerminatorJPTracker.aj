/**
 * 
 */
package TransactionJoinpointTracker;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.apache.log4j.Logger;
import org.omg.CORBA.IMP_LIMIT;

import com.arjuna.ats.arjuna.common.Uid;
import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionImple;
import com.arjuna.ats.internal.jta.xa.TxInfo;

import umjdt.concepts.Resource;
import umjdt.concepts.TID;
import umjdt.joinpoints.AbortEventJP;
import umjdt.joinpoints.BeginEventJP;
import umjdt.joinpoints.CommitEventJP;
import umjdt.joinpoints.EndEventJP;

/**
 * @author AnasAlsubh
 *
 */
public abstract aspect TerminatorJPTracker 
{
	private Logger logger = Logger.getLogger(TransactionTracker.class);
	protected EndEventJP endEventJp=null;
	protected CommitEventJP commitJp=null;
	protected AbortEventJP abortJp=null;
	
	/**
	 *Complete the transaction represented by this Transaction object.
	 *Complete the transaction associated with the current thread. 
	 *When this method completes, the thread is no longer associated with a transaction. 
	 * @param TransactionID, Status, TransactionThread, parent transaction, transaction manager
	 */

	pointcut CommitTransactionStyle(Uid uid): 
		(call(* javax..*Transaction*+.commit(..)) 
				|| (call(* com.arjuna..BaseTransaction+.commit(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (call(* com.arjuna..Transaction+.commit(..)))) && args(uid,..);
	
	pointcut CommitTransactionStyle1(Transaction transaction): 
		(call(* javax..*Transaction*+.commit(..)) 
				|| (call(* com.arjuna..BaseTransaction+.commit(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (call(* com.arjuna..Transaction+.commit(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& target(transaction);

	pointcut CommitTransactionStyle2(Transaction transaction): 
		(execution(* javax..*Transaction*+.commit(..)) 
				|| (execution(* com.arjuna..BaseTransaction+.commit(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (execution(* com.arjuna..Transaction+.commit(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& target(transaction);

	pointcut CommitTransactionStyle3(Transaction transaction, int status): 
		(call(* javax..*Transaction*+.commit(..)) 
				|| (call(* com.arjuna..BaseTransaction+.commit(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (call(* com.arjuna..Transaction+.commit(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& target(transaction) && args( status,..);

	pointcut CommitTransactionStyle4(Transaction transaction, int status): 
		(execution(* javax..*Transaction*+.commit(..)) 
				|| (execution(* com.arjuna..BaseTransaction+.commit(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (execution(* com.arjuna..Transaction+.commit(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& target(transaction) && args(status,..);

	pointcut CommitTransactionStyle5(Transaction transaction, int status, boolean parent, TransactionManager tm ): 
		(call(* javax..*Transaction*+.commit(..)) 
				|| (call(* com.arjuna..BaseTransaction+.commit(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (call(* com.arjuna..Transaction+.commit(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& target(transaction) && args(status, parent, tm,..);
	
	pointcut CommitTransactionStyle6(Transaction transaction, int status, boolean parent, TransactionManager tm ): 
		(execution(* javax..*Transaction*+.commit(..)) 
				|| (execution(* com.arjuna..BaseTransaction+.commit(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (execution(* com.arjuna..Transaction+.commit(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& target(transaction) && args(status, parent, tm,..);

	pointcut CommitTransactionStyle7(Transaction transaction, TransactionManager tm ): 
		(call(* javax..*Transaction*+.commit(..)) 
				|| (call(* com.arjuna..BaseTransaction+.commit(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (call(* com.arjuna..Transaction+.commit(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& target(transaction) && args(tm,..);
	
	pointcut CommitTransactionStyle8(Transaction transaction, TransactionManager tm ): 
		(execution(* javax..*Transaction*+.commit(..)) 
				|| (execution(* com.arjuna..BaseTransaction+.commit(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (execution(* com.arjuna..Transaction+.commit(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& target(transaction) && args(tm,..);
		
	pointcut AbortTransactionStyle1(Transaction transaction): 
		(call(* javax..*Transaction*+.rollback(..)) 
				|| (call(* com.arjuna..BaseTransaction+.rollback(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (call(* com.arjuna..Transaction+.rollback(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& target(transaction);

	pointcut AbortTransactionStyle2(Transaction transaction): 
		(execution(* javax..*Transaction*+.rollback(..)) 
				|| (execution(* com.arjuna..BaseTransaction+.rollback(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (execution(* com.arjuna..Transaction+.rollback(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& target(transaction);

	pointcut AbortTransactionStyle3(Transaction transaction, int status): 
		(call(* javax..*Transaction*+.rollback(..)) 
				|| (call(* com.arjuna..BaseTransaction+.rollback(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (call(* com.arjuna..Transaction+.rollback(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& target(transaction) && args(status,..);

	pointcut AbortTransactionStyle4(Transaction transaction, int status): 
		(execution(* javax..*Transaction*+.rollback(..)) 
				|| (execution(* com.arjuna..BaseTransaction+.rollback(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (execution(* com.arjuna..Transaction+.rollback(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& target(transaction) && args(status,..);

	pointcut AbortTransactionStyle5(Transaction transaction, int status, boolean parent, TransactionManager tm ): 
		(call(* javax..*Transaction*+.rollback(..)) 
				|| (call(* com.arjuna..BaseTransaction+.rollback(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (call(* com.arjuna..Transaction+.rollback(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& target(transaction) && args(status, parent, tm,..);
	
	pointcut AbortTransactionStyle6(Transaction transaction, int status, boolean parent, TransactionManager tm ): 
		(execution(* javax..*Transaction*+.rollback(..)) 
				|| (execution(* com.arjuna..BaseTransaction+.rollback(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (execution(* com.arjuna..Transaction+.rollback(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& target(transaction) && args(status, parent, tm,..);

	pointcut AbortTransactionStyle7(Transaction transaction, TransactionManager tm ): 
		(call(* javax..*Transaction*+.rollback(..)) 
				|| (call(* com.arjuna..BaseTransaction+.rollback(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (call(* com.arjuna..Transaction+.rollback(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& target(transaction) && args(tm,..);
	
	pointcut AbortTransactionStyle8(Transaction transaction, TransactionManager tm ): 
		(execution(* javax..*Transaction*+.rollback(..)) 
				|| (execution(* com.arjuna..BaseTransaction+.rollback(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (execution(* com.arjuna..Transaction+.rollback(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& target(transaction) && args(tm,..);
	
	
	before(Uid uid): CommitTransactionStyle(uid)
	{		
		Object target= thisJoinPoint.getTarget();
		//System.out.println(target.getClass());
		Object[] args= thisJoinPoint.getArgs();
		//System.out.println(args.getClass());
		Object _this = thisJoinPoint.getThis();
		//System.out.println(_this.getClass());
		try
		{
			TransactionImple transaction = TransactionImple.getTransaction(uid);
			commitJp = new CommitEventJP(transaction);
			Xid xid= transaction.getTxId();
			Map<Uid, Transaction> transactions= TransactionImple.getTransactions();
			int status= transaction.getStatus();
			int timeout = transaction.getTimeout();
			Map<XAResource, TxInfo> resources= transaction.getResources();
			List<Resource> resourceList= new ArrayList<>();
			
			for(XAResource xares : resources.keySet())
			{
				Resource res= new Resource();
				res.setXaresource(xares);
				res.setXid(resources.get(xares).xid());
				res.setState(resources.get(xares).getState());
				resourceList.add(res);
			}
			
			passContextInfo(commitJp, target, transaction, transactions, xid, resourceList, status, timeout);

		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}		
	}

	private void passContextInfo(CommitEventJP commitJp2, Object _target,
			TransactionImple transaction, Map<Uid, Transaction> transactions,
			Xid xid, List<Resource> resourceList, int status, int timeout) 
	{
		CommitEventJP commiteventJp= commitJp2;
		
		commiteventJp.setStatus(status);
		commiteventJp.setTimeout(timeout);
		commiteventJp.setResources(resourceList);
		commiteventJp.setTid(new TID(xid));
		commiteventJp.setTransactions(transactions);
		commiteventJp.setTransaction((umjdt.concepts.Transaction) transaction);
		if(_target.equals(TransactionManager.class))
		commiteventJp.setManager((TransactionManager)_target);
	}

	void around(Transaction transaction): AbortTransactionStyle1(transaction)
	{
		
	}
}
