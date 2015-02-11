package umjdt.concepts;

import umjdt.util.Status;

public class TransactionStatus {
	
	private Status status;
	private Transaction transaction;
	private TransactionManager localTransactionManager;
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public TransactionManager getLocalTransactionManager() {
		return localTransactionManager;
	}
	public void setLocalTransactionManager(
			TransactionManager localTransactionManager) {
		this.localTransactionManager = localTransactionManager;
	}
	
}
