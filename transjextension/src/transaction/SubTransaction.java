package transaction;

import utilities.OperationNumber;

public class SubTransaction extends Transaction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int timeout;
	
	private TID subTID;
	
	public SubTransaction()
	{
		super();
		subTID = new TID(super.getTId());
		//OperationNumber op = OperationNumber.Create(subTID);
		setTId(subTID);
	}
	
	public SubTransaction(int _timeout)
	{
		super(_timeout);
		this.timeout=_timeout;
		setTimeout(_timeout);
		subTID = new TID(super.getTId());
		//OperationNumber op = OperationNumber.Create(subTID);
		setTId(subTID);
	}

	public int getTimeout() 
	{
		return timeout;
	}

	public void setTimeout(int timeout) 
	{
		this.timeout = timeout;
	}

	public TID getSubTID() {
		return subTID;
	}

	public void setSubTID(TID subTID) 
	{
		this.subTID = subTID;
	}

}
