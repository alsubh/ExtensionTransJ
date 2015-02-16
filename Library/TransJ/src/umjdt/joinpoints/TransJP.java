package umjdt.joinpoints;

import java.util.*;
import umjdt.util.*;
import umjdt.concepts.*;
import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;



public class TransJP
{
	Logger logger = Logger.getLogger(TransJP.class.toString());
	
	private Transaction tranaction;
	private Operation operation;
	private Resource resource;
	private TransactionType type;
	
	public TransJP()
	{
	}
	
	public TransJP(Transaction _transaction, TransactionType _type)
	{
		this.setTranaction(_transaction);
		this.setType(_type);
	}
	
	public void set(TransJP _transJp)
	{
		this.tranaction =_transJp.getTranaction();
		this.operation= _transJp.getOperation();
		this.resource =_transJp.getResource();
		this.type= _transJp.getType();
	}

	public Transaction getTranaction() {
		return tranaction;
	}

	public void setTranaction(Transaction tranaction) {
		this.tranaction = tranaction;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
