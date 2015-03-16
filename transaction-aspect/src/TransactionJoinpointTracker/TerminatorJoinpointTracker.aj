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
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.apache.log4j.Logger;

import com.arjuna.ats.arjuna.common.Uid;
import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionImple;
import com.arjuna.ats.internal.jta.xa.TxInfo;

import umjdt.concepts.Resource;
import umjdt.concepts.SubTransaction;
import umjdt.concepts.TID;
import umjdt.concepts.Transaction;
import umjdt.joinpoints.AbortEventJP;
import umjdt.joinpoints.CommitEventJP;
import umjdt.joinpoints.EndEventJP;

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
	
	before(Transaction _transaction): CommitTransaction(_transaction)
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
			commitJp = new CommitEventJP(_transaction);
			commitJp.setCommitJP(thisJoinPoint);
			contexinfo(commitJp, target, _transaction);

		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}		
	}
	
	after(Transaction _transaction) throws SystemException: CommitTransaction(_transaction)
	{
		Object target= thisJoinPoint.getTarget();
		//System.out.println(target.getClass());
		Object[] args= thisJoinPoint.getArgs();
		//System.out.println(args.getClass());
		Object _this = thisJoinPoint.getThis();
		//System.out.println(_this.getClass());

		//Transaction transaction = (Transaction) TransactionImple.getTransaction(_tid.getUid());
		CommitEventJP commitjp = new CommitEventJP(_transaction);
		commitjp.setCommitJP(thisJoinPoint);
		commitjp.setStatus(Status.STATUS_COMMITTED);
		commitjp.setTid(_transaction.getTid());
		
		contexinfo(commitjp, target, _transaction);
	}
	
	before(Transaction _transaction): AbortTransaction(_transaction)
	{
		try
		{
			Object target= thisJoinPoint.getTarget();
			//System.out.println(target.getClass());
			Object[] args= thisJoinPoint.getArgs();
			//System.out.println(args.getClass());
			Object _this = thisJoinPoint.getThis();
			//System.out.println(_this.getClass());
	
			//Transaction transaction = (Transaction) TransactionImple.getTransaction(_tid.getUid());
			abortJp = new AbortEventJP(_transaction);
			abortJp.setAbortJP(thisJoinPoint);
			contexinfo(abortJp,target, _transaction);

		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
		
	after(Transaction _transaction): AbortTransaction(_transaction)
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
			AbortEventJP abortjp = new AbortEventJP(_transaction);
			abortjp.setAbortJP(thisJoinPoint);
			abortjp.setStatus(Status.STATUS_ROLLEDBACK);
			abortjp.setTid(_transaction.getTid());
			contexinfo(abortjp, target, _transaction);
		} 
		catch (SystemException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			Resource res;
			try 
			{
				res = new Resource(xares);
				res.setXid(resources.get(xares).xid());
				res.setState(resources.get(xares).getState());
				resourceList.add(res);
			}
			catch (XAException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		
		if(_endJP.getClass().isInstance(CommitEventJP.class))
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
		aborteventJp.setTid(new TID(xid));
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
