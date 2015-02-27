package transaction;

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
		setTId(subTID);
	}
	
	public SubTransaction(int _timeout)
	{
		super(_timeout);
		this.timeout=_timeout;
		setTimeout(_timeout);
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
