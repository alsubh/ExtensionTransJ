/**
 * 
 */
package TransactionJoinpointTracker;

import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

import org.apache.log4j.Logger;

import umjdt.joinpoints.AbortEventJP;
import umjdt.joinpoints.BeginEventJP;
import umjdt.joinpoints.CommitEventJP;
import umjdt.joinpoints.EndEventJP;

/**
 * @author AnasAlsubh
 *
 */
public aspect TerminatorJPTracker 
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
	
}
