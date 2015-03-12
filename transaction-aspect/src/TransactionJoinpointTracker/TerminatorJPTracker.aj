/**
 * 
 */
package TransactionJoinpointTracker;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.apache.log4j.Logger;
import org.omg.CORBA.IMP_LIMIT;

import com.arjuna.ats.arjuna.common.Uid;
import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionImple;
import com.arjuna.ats.internal.jta.xa.TxInfo;

import umjdt.concepts.Resource;
import umjdt.concepts.SubTransaction;
import umjdt.concepts.TID;
import umjdt.concepts.Transaction;
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

	pointcut CommitTransactionStyle(TID tid): 
		(call(* javax..*Transaction*+.commit(..)) 
				|| (call(* com.arjuna..BaseTransaction+.commit(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (call(* com.arjuna..Transaction+.commit(..)))) && args(tid,..);
	
	pointcut CommitTransactionStyle1(Transaction transaction): 
		(call(* javax..*Transaction*+.commit(..)) 
				|| (call(* com.arjuna..BaseTransaction+.commit(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (call(* com.arjuna..Transaction+.commit(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& target(transaction);

	/*
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
		

	pointcut AbortTransactionStyle(Uid uid): 
		(call(* javax..*Transaction*+.rollback(..)) 
				|| (call(* com.arjuna..BaseTransaction+.rollback(..))) //ats.internal.jta.transaction.arjunacore.BaseTransaction.
				|| (call(* com.arjuna..Transaction+.rollback(..))))//arjuna.ats.jta.transaction.Transaction+ 
				&& args(uid,..);
	
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
	
	*/
	
	before(TID tid): CommitTransactionStyle(tid)
	{		
		try
		{
			Object target= thisJoinPoint.getTarget();
			//System.out.println(target.getClass());
			Object[] args= thisJoinPoint.getArgs();
			//System.out.println(args.getClass());
			Object _this = thisJoinPoint.getThis();
			//System.out.println(_this.getClass());
		
			Transaction transaction = (Transaction) TransactionImple.getTransaction(tid.getUid());
			commitJp = new CommitEventJP(transaction);
			contexinfo(commitJp, target, transaction);

		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}		
	}
	
	before(Uid uid): AbortTransactionStyle(uid)
	{
		try
		{
			Object target= thisJoinPoint.getTarget();
			//System.out.println(target.getClass());
			Object[] args= thisJoinPoint.getArgs();
			//System.out.println(args.getClass());
			Object _this = thisJoinPoint.getThis();
			//System.out.println(_this.getClass());
	
			TransactionImple transaction = TransactionImple.getTransaction(uid);
			abortJp = new AbortEventJP((umjdt.concepts.Transaction) transaction);
			contexinfo(abortJp,target, transaction);

		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	after(Uid uid): CommitTransactionStyle(uid)
	{
		Object target= thisJoinPoint.getTarget();
		//System.out.println(target.getClass());
		Object[] args= thisJoinPoint.getArgs();
		//System.out.println(args.getClass());
		Object _this = thisJoinPoint.getThis();
		//System.out.println(_this.getClass());

		TransactionImple transaction = TransactionImple.getTransaction(uid);
		CommitEventJP commitjp = new CommitEventJP(transaction);
		commitjp.setStatus(Status.STATUS_COMMITTED);
		commitjp.setTransactionId(new TID (transaction.getTxId(), transaction.get_uid()));
	}
	
	after(Uid uid): AbortTransactionStyle(uid)
	{
		Object target= thisJoinPoint.getTarget();
		//System.out.println(target.getClass());
		Object[] args= thisJoinPoint.getArgs();
		//System.out.println(args.getClass());
		Object _this = thisJoinPoint.getThis();
		//System.out.println(_this.getClass());

		TransactionImple transaction = TransactionImple.getTransaction(uid);
		AbortEventJP abortjp = new AbortEventJP(transaction);
		abortjp.setStatus(Status.STATUS_ROLLEDBACK);
		abortjp.setTransactionId(new TID (transaction.getTxId(), transaction.get_uid()));
	}
	
	private void contexinfo(EndEventJP _endJP,Object _target, Transaction _transaction) throws SystemException 
	{
		Xid xid= _transaction.getTid().getXid();
		//System.out.println(_"TxId= "+ transaction.getTxId());
		int status= _transaction.getStatus();
		int timeout = _transaction.getTimeout();
		Map<XAResource, TxInfo> resources= _transaction.getResources();
		List<Resource> resourceList= new ArrayList<>();
		
		for(XAResource xares : resources.keySet())
		{
			Resource res= new Resource();
			res.setXaresource(xares);
			res.setXid(resources.get(xares).xid());
			res.setState(resources.get(xares).getState());
			resourceList.add(res);
		}
		List<SubTransaction> subtransactions= new ArrayList<>();
	
		for(Uid _uid : TransactionImple.getTransactions().keySet())
		{
			SubTransaction sub= new SubTransaction(new TID(xid, _uid));
			TID tid= sub.getTid();
			sub = (SubTransaction)TransactionImple.getTransactions().get(_uid);
			sub.setStatus(sub.getStatus());
			sub.setTimeout(sub.getTimeout());	
			subtransactions.add(sub);
		}
		
		if(_endJP.getClass().equals(CommitEventJP.class))
			passContextInfo(commitJp, _target, _transaction, subtransactions, xid, resourceList, status, timeout);
		else
			passContextInfo(abortJp, _target, _transaction, subtransactions, xid, resourceList, status, timeout);
	}

	private void passContextInfo(CommitEventJP commitJP, Object _target,
			Transaction transaction, List<SubTransaction> transactions,
			Xid xid, List<Resource> resourceList, int status, int timeout) 
	{
		CommitEventJP commiteventJp= commitJP;
		
		commiteventJp.setStatus(Status.STATUS_COMMITTING);
		commiteventJp.setTimeout(timeout);
		commiteventJp.setResources(resourceList);
		commiteventJp.setTid(new TID(xid));
		commiteventJp.setTransactions(transactions);
		commiteventJp.setTransaction((umjdt.concepts.Transaction) transaction);
		if((_target !=null) && (_target.getClass().equals(TransactionManager.class)))
			commiteventJp.setManager((TransactionManager)_target);
		
		Commit(commiteventJp);
	}
	
	private void passContextInfo(AbortEventJP abortJP, Object _target,
			TransactionImple transaction, List<SubTransaction> transactions,
			Xid xid, List<Resource> resourceList, int status, int timeout) 
	{
		AbortEventJP aborteventJp= abortJP;
		
		aborteventJp.setStatus(Status.STATUS_ROLLING_BACK);
		aborteventJp.setTimeout(timeout);
		aborteventJp.setResources(resourceList);
		aborteventJp.setTid(new TID(xid));
		aborteventJp.setTransactions(transactions);
		aborteventJp.setTransaction((umjdt.concepts.Transaction) transaction);
		if((_target !=null) && (_target.getClass().equals(TransactionManager.class)))
			aborteventJp.setManager((TransactionManager)_target);
		
		Abort(aborteventJp);
	}
	
	public void End(EndEventJP _endEventjp)
	{	
	}
	public void Commit(CommitEventJP _commitEventjp)
	{	
	}
	
	public void Abort(AbortEventJP _abortEventjp)
	{	
	}
}
