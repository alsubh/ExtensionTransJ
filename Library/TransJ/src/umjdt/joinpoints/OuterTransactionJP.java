package umjdt.joinpoints;

import umjdt.concepts.Transaction;
import umjdt.util.TransactionType;
import org.aspectj.lang.JoinPoint; 

public class OuterTransactionJP extends TransJP
{
	private Transaction transaction;
	private JoinPoint outerTransactionJp;
	
	public OuterTransactionJP(Transaction _transaction)
	{
		setTranaction(_transaction);
	}
	
	public String typeOfTransaction(Transaction _transaction)
	{
		return TransactionType.stringForm( _transaction.getTransactionType(_transaction));
	}
	
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public JoinPoint getOuterTransactionJp() {
		return outerTransactionJp;
	}

	public void setOuterTransactionJp(JoinPoint outerTransactionJp) {
		this.outerTransactionJp = outerTransactionJp;
	}
}
