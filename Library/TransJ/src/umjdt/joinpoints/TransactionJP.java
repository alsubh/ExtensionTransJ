package umjdt.joinpoints;

import java.util.List;

import umjdt.Events.TransactionAbortEvent;
import umjdt.Events.TransactionBeginEvent;
import umjdt.Events.TransactionCommitEvent;
import umjdt.concepts.Transaction;
import org.aspectj.lang.JoinPoint;
public class TransactionJP extends TransJP
{
		
	public TransactionJP() 
	{
		super();
	}

	public TransactionJP(TransJP _transJp) 
	{
		super(_transJp);
	}

	private JoinPoint transactionJp;
	private Thread transactionJPThread;
	private List<OperationJP> operationJp;
	private TransactionBeginEvent beginEvent;
	private TransactionAbortEvent abortEvent;
	private TransactionCommitEvent commitEvent;
	
	public JoinPoint getTransactionJp()
	{
		return transactionJp;
	}

	public void setTransactionJp(JoinPoint transactionJp) 
	{
		this.transactionJp = transactionJp;
	}

	public Thread getTransactionJPThread() {
		return transactionJPThread;
	}

	public void setTransactionJPThread(Thread transactionJPThread) {
		this.transactionJPThread = transactionJPThread;
	}

	public List<OperationJP> getOperationJp() {
		return operationJp;
	}

	public void setOperationJp(List<OperationJP> operationJp) {
		this.operationJp = operationJp;
	}

	public TransactionBeginEvent getBeginEvent() {
		return beginEvent;
	}

	public void setBeginEvent(TransactionBeginEvent beginEvent) {
		this.beginEvent = beginEvent;
	}

	public TransactionAbortEvent getAbortEvent() {
		return abortEvent;
	}

	public void setAbortEvent(TransactionAbortEvent abortEvent) {
		this.abortEvent = abortEvent;
	}

	public TransactionCommitEvent getCommitEvent() {
		return commitEvent;
	}

	public void setCommitEvent(TransactionCommitEvent commitEvent) {
		this.commitEvent = commitEvent;
	}
}
