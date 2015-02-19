package umjdt.joinpoints;

import java.util.*;
import umjdt.util.*;
import umjdt.concepts.*;
import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;

import com.arjuna.ats.internal.jta.xa.TxInfo;



public class TransJP
{
	Logger logger = Logger.getLogger(TransJP.class.toString());
	
	private Transaction tranaction;
	private TxInfo txInfo;
	private TransactionType type;
	
	public TransJP()
	{
		
	}
	
	public TransJP(Transaction _transaction, TransactionType _type)
	{
		this.tranaction= _transaction;
		this.type= _type;
	}
	
	public TransJP(TransJP _transJp)
	{
		this.tranaction =_transJp.getTranaction();
		this.type= _transJp.getType();
	}
	
	public void set(TransJP _transJp)
	{
		this.tranaction =_transJp.getTranaction();
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

	public TxInfo getTxInfo() {
		return txInfo;
	}

	public void setTxInfo(TxInfo txInfo) {
		this.txInfo = txInfo;
	}
}
