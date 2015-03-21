package ResourceManager;

import javax.*;
import javax.transaction.xa.*;

public class XAResourceImp implements XAResource
{

	private final XAResource xaResource;
	private final String name;
	
	public XAResourceImp(XAResource xaResource, String name)
	{
		this.xaResource=xaResource;
		this.name=name;
	}
	
	@Override
	public void commit(Xid xid, boolean onePhase) throws XAException {
		// TODO Auto-generated method stub
		xaResource.commit(xid, onePhase);
	}

	@Override
	public void end(Xid xid, int flags) throws XAException {
		// TODO Auto-generated method stub
		xaResource.end(xid, flags);
		
	}

	@Override
	public void forget(Xid xid) throws XAException {
		// TODO Auto-generated method stub
		xaResource.forget(xid);
	}

	@Override
	public int getTransactionTimeout() throws XAException {
		// TODO Auto-generated method stub
		return xaResource.getTransactionTimeout();
	}

	@Override
	public boolean isSameRM(XAResource xaResourceObj) throws XAException {
		// TODO Auto-generated method stub
		if(xaResourceObj instanceof XAResourceImp)
		{
			return xaResource.isSameRM(((XAResourceImp)xaResourceObj).xaResource);
		}
		return false;
	}

	@Override
	public int prepare(Xid xid) throws XAException {
		// TODO Auto-generated method stub
		return xaResource.prepare(xid);
	}

	@Override
	public Xid[] recover(int flag) throws XAException {
		// TODO Auto-generated method stub
		return xaResource.recover(flag);
		
	}

	@Override
	public void rollback(Xid xid) throws XAException {
		// TODO Auto-generated method stub
		xaResource.rollback(xid);
	}

	@Override
	public boolean setTransactionTimeout(int seconds) throws XAException {
		// TODO Auto-generated method stub
		return xaResource.setTransactionTimeout(seconds);
	}

	@Override
	public void start(Xid xid, int flags) throws XAException {
		// TODO Auto-generated method stub
		xaResource.start(xid, flags);
		
	}

	public XAResource getXaResource() {
		return xaResource;
	}

	public String getName() {
		return name;
	}

}
