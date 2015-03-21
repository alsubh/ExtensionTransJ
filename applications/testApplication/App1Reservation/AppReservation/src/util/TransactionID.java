package util;

import javax.*;
import javax.transaction.xa.*;

import com.arjuna.ats.internal.jta.xa.XID;

public class TransactionID implements Xid 
{ 
	protected int formatId; 
	protected byte globalTransactionId[]; 
	protected byte branchQualifierbqual[]; 
	
	public TransactionID() 
	{
	} 
	
	public TransactionID(int formatId, byte gtrid[], byte bqual[]) 
	{ 
		this.formatId = formatId; 
		this.globalTransactionId = gtrid; 
		this.branchQualifierbqual = bqual; 
	} 
	
	public int getFormatId() 
	{ 
		return formatId; 
	} 
	
	public byte[] getBranchQualifier() 
	{ 
		return branchQualifierbqual; 
	} 
	
	public byte[] getGlobalTransactionId() 
	{ 
		return globalTransactionId; 
	}
}