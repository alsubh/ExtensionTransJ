/**
 * 
 */
package TransactionJoinpointTracker;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.apache.log4j.Logger;

import com.arjuna.ats.arjuna.common.Uid;
import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionImple;
import com.arjuna.ats.internal.jta.xa.TxInfo;

import umjdt.concepts.Resource;
import umjdt.concepts.SubTransaction;
import umjdt.concepts.Xid;
import umjdt.concepts.Transaction;
import umjdt.joinpoints.AbortEventJP;
import umjdt.joinpoints.CommitEventJP;
import umjdt.joinpoints.EndEventJP;
import umjdt.util.Status;

/**
 * @author AnasAlsubh
 *
 */
public abstract aspect TerminatorJoinpointTracker extends TransactionTracker
{
	private Logger logger = Logger.getLogger(TransactionTracker.class);
	
	protected EndEventJP endEventJp=null;
	protected CommitEventJP commitJp=null;
	protected AbortEventJP abortJp=null;
	
	before(): CommitTransaction()
	{		
		try
		{
			Object target= thisJoinPoint.getTarget();
			//System.out.println(target.getClass());
			Object[] args= thisJoinPoint.getArgs();
			//System.out.println(args.getClass());
			Object _this = thisJoinPoint.getThis();
			//System.out.println(_this.getClass());
		
			//Transaction transaction = (Transaction) TransactionImple.getTransaction(tid.getUid());
			commitJp = new CommitEventJP();
			commitJp.setCommitJP(thisJoinPoint);
			contexinfo(commitJp, target);

		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}		
	}
	
	after() throws SystemException: CommitTransaction()
	{
		Object target= thisJoinPoint.getTarget();
		//System.out.println(target.getClass());
		Object[] args= thisJoinPoint.getArgs();
		//System.out.println(args.getClass());
		Object _this = thisJoinPoint.getThis();
		//System.out.println(_this.getClass());

		CommitEventJP commitjp = new CommitEventJP();
		commitjp.setCommitJP(thisJoinPoint);
		commitjp.setStatus(Status.STATUS_COMMITTED);
		
		contexinfo(commitjp, target);
	}
	
	before(): AbortTransaction()
	{
		try
		{
			Object target= thisJoinPoint.getTarget();
			//System.out.println(target.getClass());
			Object[] args= thisJoinPoint.getArgs();
			//System.out.println(args.getClass());
			Object _this = thisJoinPoint.getThis();
			//System.out.println(_this.getClass());

			abortJp = new AbortEventJP();
			abortJp.setAbortJP(thisJoinPoint);
			contexinfo(abortJp,target);

		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
		
	after(): AbortTransaction()
	{
		Object target= thisJoinPoint.getTarget();
		//System.out.println(target.getClass());
		Object[] args= thisJoinPoint.getArgs();
		//System.out.println(args.getClass());
		Object _this = thisJoinPoint.getThis();
		//System.out.println(_this.getClass());
		try 
		{
			//Transaction transaction = (Transaction) TransactionImple.getTransaction(_tid.getUid());
			AbortEventJP abortjp = new AbortEventJP();
			abortjp.setAbortJP(thisJoinPoint);
			abortjp.setStatus(Status.STATUS_ROLLEDBACK);
			contexinfo(abortjp, target);
		} 
		catch (SystemException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void contexinfo(EndEventJP _endJP, Object _target) throws SystemException 
	{
		if(_target !=null)
		{
			if(_target instanceof TransactionManager)
			{
				_endJP.setManager((TransactionManager)_target);
				//transaction currently associated with thread.
				transaction = (Transaction)_endJP.getManager().getTransaction();
			}
			else if(_target instanceof UserTransaction)
			{
				_endJP.setUser((UserTransaction)_target);
				transaction = (Transaction) TransactionImple.getTransaction();
			}
		} 
		_endJP.setTid(transaction.getTid());
		Xid xid= transaction.getTid().getXid();
		//System.out.println(_"TxId= "+ transaction.getTxId());
		int status= transaction.getStatus();
		int timeout = transaction.getTimeout();
		
		List<Resource> resourceList= new ArrayList<>();		
		if(transaction.getResources().size() > 0)
		{
			for(XAResource xares : transaction.getResources().keySet())
			{
				Resource res;
				try 
				{
					res = new Resource(xares);
					res.setXid(transaction.getResources().get(xares).xid());
					res.setState(transaction.getResources().get(xares).getState());// TMFAIL for abort and TMSUCESS for commit
					resourceList.add(res);
				}
				catch (XAException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		List<SubTransaction> subtransactions= new ArrayList<>();
		if(TransactionImple.getTransactions().size() > 0)
		{
			for(Uid _uid : TransactionImple.getTransactions().keySet())
			{
				SubTransaction sub= new SubTransaction(new Xid(xid, _uid));
				Xid tid= sub.getTid();
				sub = (SubTransaction)TransactionImple.getTransactions().get(_uid);
				sub.setStatus(sub.getStatus());
				sub.setTimeout(sub.getTimeout());	
				subtransactions.add(sub);
			}
		}
		
		if(_endJP instanceof CommitEventJP)
			passContextInfo(commitJp, _target, transaction, subtransactions, xid, resourceList, status, timeout);
		else
			passContextInfo(abortJp, _target, transaction, subtransactions, xid, resourceList, status, timeout);
	}

	private void passContextInfo(CommitEventJP commitJP, Object _target,
			Transaction transaction, List<SubTransaction> transactions,
			Xid xid, List<Resource> resourceList, int status, int timeout) 
	{
		CommitEventJP commiteventJp= commitJP;
		
		commiteventJp.setStatus(Status.STATUS_COMMITTING);
		commiteventJp.setTimeout(timeout);
		commiteventJp.setResources(resourceList);
		commiteventJp.setTid(new Xid(xid));
		commiteventJp.setTransactions(transactions);
		commiteventJp.setTransaction(transaction);
		if((_target !=null) && (_target.getClass().equals(TransactionManager.class)))
			commiteventJp.setManager((TransactionManager)_target);
		
		//Commit(commiteventJp);
		CommitJoinPoint(commiteventJp);
	}
	
	private void passContextInfo(AbortEventJP abortJP, Object _target,
			TransactionImple transaction, List<SubTransaction> transactions,
			Xid xid, List<Resource> resourceList, int status, int timeout) 
	{
		AbortEventJP aborteventJp= abortJP;
		
		aborteventJp.setStatus(Status.STATUS_ROLLING_BACK);
		aborteventJp.setTimeout(timeout);
		aborteventJp.setResources(resourceList);
		aborteventJp.setTid(new Xid(xid));
		aborteventJp.setTransactions(transactions);
		aborteventJp.setTransaction((umjdt.concepts.Transaction) transaction);
		if((_target !=null) && (_target.getClass().equals(TransactionManager.class)))
			aborteventJp.setManager((TransactionManager)_target);
		
		//Abort(aborteventJp);
		AbortJoinPoint(aborteventJp);
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
