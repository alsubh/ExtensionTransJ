package umjdt.util;

import java.io.Serializable;

public class TransactionType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;

	public TransactionType()
	{
		
	}
	
	public TransactionType(String _type)
	{
		this.type = _type;
	}
	public String getType() {
		return type;
	}

	public void setType(String _type) {
		this.type = _type;
	}
}
