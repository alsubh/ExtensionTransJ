package umjdt.contexts;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;

import umjdt.concepts.Transaction;

public class TransactionContext extends Context{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<TransactionContext> transactionContext = new ArrayList<TransactionContext>(); 
	
	private MultiMap<Transaction, OperationContext> operationContexts= new MultiValueMap<>();
	private List<OperationContext> operationContext = new ArrayList<OperationContext>();
	
	private MultiMap<Transaction, LockContext> lockContexts= new MultiValueMap<>();
	private List<LockContext> lockContext = new ArrayList<LockContext>();
	
	
}
