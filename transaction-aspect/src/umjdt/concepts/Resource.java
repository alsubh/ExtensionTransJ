package umjdt.concepts;

import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;


public class Resource 
{
	private XAResource xaresource;
	private int state;
	private Xid xid;
	
	
	public XAResource getXaresource() {
		return xaresource;
	}
	public void setXaresource(XAResource xaresource) {
		this.xaresource = xaresource;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Xid getXid() {
		return xid;
	}
	public void setXid(Xid xid) {
		this.xid = xid;
	}
}