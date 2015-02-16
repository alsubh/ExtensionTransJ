package umjdt.Events;

import java.util.*;

import javax.management.OperationsException;
import umjdt.concepts.*;

public class TransactionEvent extends Event{
	
	private Transaction transaction;
	private String markBoundary;
	private Operation operation = new Operation();
	private ResourceManager resourcemanager= new ResourceManager();
	
	public TransactionEvent(){
		super();
		setType("TransactionEvent");
	}
	
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction _transaction) {
		this.transaction = _transaction;
	}
			
	public ResourceManager getResourceManager() {
		return resourcemanager;
	}

	public void setResourceManager(ResourceManager _resourceManager) {
		this.resourcemanager = _resourceManager;
	}

	public boolean involves(Operation _operation) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean occursOn(Transaction _transaction) {
		// TODO Auto-generated method stub
		return false;
	}
	public String getMarkBoundary() {
		return markBoundary;
	}

	public void setMarkBoundary(String markBoundary) {
		this.markBoundary = markBoundary;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}
}
