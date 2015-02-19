package umjdt.concepts;

import javax.transaction.xa.Xid;

import com.arjuna.ats.arjuna.common.Uid;
import com.arjuna.ats.internal.jta.xa.XID;
import com.arjuna.ats.jta.xa.XidImple;

public class TransId extends XidImple 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Xid xid;
	private XID xID;
	
	public TransId()
	{
		super();
		this.xid= new XidImple();
		setXid(xid);
	}
	
	public TransId(Xid _xid)
	{
		super(_xid);
		this.xid= new XidImple(_xid);
		setXid(xid);
	}
	
	public TransId(Transaction t)
	{
		this(t.getId());
		this.xid= new XidImple(t.getId());
		setXid(xid);
	}
	
	public TransId(XID xID)
	{
		super(xID);
		setxID(xID);
	}

	public Xid getXid() {
		return xid;
	}

	public void setXid(Xid xid) {
		this.xid = xid;
	}

	public XID getxID() {
		return xID;
	}

	public void setxID(XID xID) {
		this.xID = xID;
	}
	
	
}