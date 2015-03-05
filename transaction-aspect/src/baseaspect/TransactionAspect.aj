package baseaspect;

import org.apache.log4j.Logger;

import umjdt.joinpoints.AbortJP;
import umjdt.joinpoints.BeginEventJP;
import umjdt.joinpoints.CommitJP;
import umjdt.joinpoints.EndEventJP;
import TransactionJoinpointTracker.TransactionTracker;


public abstract aspect TransactionAspect 
{
	private Logger logger = Logger.getLogger(TransactionAspect.class);
	
	public pointcut TransactionBegin(BeginEventJP _beginJp) : 
		within(TransationTracker) && 
		execution(void TransactionTracker.BeginJoinPoint(..)) && args(_beginJp);
	public pointcut TransactionEnd(EndEventJP _endEventJp): execution(* EndTransactionAspect.begin(..) && args(_endEventJp));
	public pointcut TransactionCommit(CommitJP _commitJp): execution(* CommitTransactionAspect.begin(..) && args(_commitJp));
	public pointcut TransactionAbort(AbortJP _abortJp): execution(* AbortTransactionAspect.begin(..) && args(_abortJp));
}
