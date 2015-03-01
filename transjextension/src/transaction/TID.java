package transaction;

import javax.transaction.xa.Xid;

import com.arjuna.ats.jta.xa.XidImple;
//import com.arjuna.ats.internal.jta.xa.XID;


public class TID extends XidImple 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Xid _Xid;
	//private XID _XID;
	
	public TID()
	{
		super();
		this._Xid= new XidImple();
		setXid(_Xid);
	}
	
	public TID(Xid _xid)
	{
		super(_xid);
		this._Xid= new XidImple(_xid);
		setXid(_xid);
	}
	
	public TID(Transaction _transaction)
	{
		this(_transaction.getTId());
		this._Xid= new XidImple(_transaction.getTId());
		setXid(_Xid);
	}
	public void setXid(Xid xid) 
	{
		this._Xid = xid;
	}
	
	/**
	 * 
	public TID(XID _ID)
	{
		super(_ID);
		setxID(_ID);
	}

	public Xid getXid()
	{
		return _Xid;
	}
	public XID getxID() 
	{
		return _XID;
	}

	public void setxID(XID xID) 
	{
		this._XID = xID;
	}
	*/
}
