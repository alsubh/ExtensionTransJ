package umjdt.concepts;

import umjdt.util.IdNumber;


public class SubTransaction extends Transaction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int timeout;
	private IdNumber subTid;
	
	public SubTransaction()
	{
		super();
		TID id = new TID(super.getTId());
		subTid = IdNumber.Create(id);
		setSubTid(subTid);
	}
	
	public SubTransaction(int _timeout)
	{
		super(_timeout);
		this.timeout=_timeout;
		setTimeout(_timeout);
		TID id = new TID(super.getTId());
		subTid = IdNumber.Create(id);
		setSubTid(subTid);
	}

	public int getTimeout() 
	{
		return timeout;
	}

	public void setTimeout(int timeout) 
	{
		this.timeout = timeout;
	}
	
	public IdNumber getSubTid() {
		return subTid;
	}

	public void setSubTid(IdNumber subTid) {
		this.subTid = subTid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
