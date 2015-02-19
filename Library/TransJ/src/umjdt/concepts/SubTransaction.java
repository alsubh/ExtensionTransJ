package umjdt.concepts;

public class SubTransaction extends Transaction
{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int timeout;
	
	public SubTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SubTransaction(int _timeout) {
		super(_timeout);
		this.timeout=_timeout;
		setTimeout(_timeout);
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}