package umjdt.concepts;

import javax.transaction.xa.Xid;

import com.arjuna.ats.arjuna.AtomicAction;
import com.arjuna.ats.arjuna.common.Uid;
import com.arjuna.ats.internal.jta.xa.XID;
import com.arjuna.ats.jta.xa.XidImple;

public class TransId extends XidImple{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private XidImple xid;
	private Transaction transaction;
	
	public TransId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransId(AtomicAction c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	public TransId(Uid arg0, boolean arg1, Integer arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	public TransId(Xid xid, boolean branch, Integer eisName) {
		super(xid, branch, eisName);
		// TODO Auto-generated constructor stub
	}

	public TransId(Xid xid) {
		super(xid);
		// TODO Auto-generated constructor stub
	}

	public TransId(XID x) {
		super(x);
		// TODO Auto-generated constructor stub
	}

	public TransId(XidImple _xid){
		super(_xid);
		this.xid= _xid;
	}
	
	public TransId(Transaction _transaction){
		this.transaction=_transaction;
	}
}