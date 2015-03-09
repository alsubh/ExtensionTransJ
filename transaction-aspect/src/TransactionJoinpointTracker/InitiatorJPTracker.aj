/**
 * 
 */
package TransactionJoinpointTracker;

import org.apache.log4j.Logger;

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
}
