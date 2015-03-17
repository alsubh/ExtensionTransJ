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
import umjdt.util.Timestamp;

/**
 * @author AnasAlsubh
 *
 */
public abstract aspect InitiatorJoinpointTracker extends TransactionTracker
{
	private Logger logger = Logger.getLogger(TransactionTracker.class);
	
	protected BeginEventJP beginEventJp=null;
	
	private Transaction transaction= null;
	private Uid transactionUid= null;;
	private Xid globalID=null;
	private int status=0;
	private int timeout=-1;
	
	before() : BeginTransaction()
	{
		BeginEventJP beginEventJp = new BeginEventJP();
		beginEventJp.setBeginJP(thisJoinPoint);
		Object target = thisJoinPoint.getTarget();
		beginEventJp.setBeginTime(new Timestamp().currentTimeStamp());
		//passContextInfo(beginEventJp, thisJoinPoint.getTarget(),null, null,null,0,_timeout);
	}
	
	after() throws SystemException: BeginTransaction()
	{	
		BeginEventJP beginEventJp = new BeginEventJP();
		beginEventJp.setBeginJP(thisJoinPoint);
		beginContextInfo(thisJoinPoint.getTarget());
	}
	
	private void beginContextInfo(Object target) throws SystemException 
	{
		beginEventJp = new BeginEventJP();
		//transaction currently associated with thread.
		transaction = (Transaction) TransactionImple.getTransaction();			
		transactionUid = TransactionImple.getTransaction().get_uid();
		globalID = TransactionImple.getTransaction().getTxId();
		status =TransactionImple.getTransaction().getStatus();
		timeout = TransactionImple.getTransaction().getTimeout();
		
		passContextInfo(beginEventJp, target, transaction, transactionUid, globalID, status, timeout);		
	}
	
	private void passContextInfo(BeginEventJP eventJp, Object _target, Transaction _transaction, Uid _uid, Xid _globalxid, int _status, int _timeout) 
	{	
		BeginEventJP beginJp= eventJp;
		
		if(_target !=null)
		{
			if(_target.getClass().isInstance(TransactionManager.class))
			{
				beginJp.setManager((TransactionManager)_target);
			}
			else if(_target.getClass().isInstance(UserTransaction.class))
			{
				beginJp.setUser((UserTransaction)_target);
			}
		}
		beginJp.setTid(new TID(_globalxid, _uid));
		beginJp.setTransaction(_transaction);
		beginJp.setStatus(_status);
		beginJp.setTimeout(_timeout);
		
		BeginJoinPoint(beginJp);
	}
}
