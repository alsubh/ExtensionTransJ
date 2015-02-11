package umjdt.concepts;

public class SubTransaction extends Transaction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Transaction transaction;
	private TransId transid;
	private static int seqNumber=0; 
		
	public SubTransaction(TransId _id) {
		super(_id);
		this.transid =_id;
	}

	public SubTransaction(int _subId,  TransId _id) {
		super(_id);
		this.transid =_id;
		_subId = seqNumber+1; 
		this.setSeqNumber(_subId);
		// TODO Auto-generated constructor stub
	}
	
	public SubTransaction(Transaction transaction, TransId transid, int seqNumber) {
		super(transid);
		this.transaction = transaction;
		this.transid = transid;
		this.seqNumber = seqNumber;
	}

	public SubTransaction (Transaction _transaction) {
		super(_transaction.getId());
		this.transaction= _transaction;
	}
	
	public TransId getTransid() {
		return transid;
	}

	public void setTransid(TransId transid) {
		this.transid = transid;
	}

	public int getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}
