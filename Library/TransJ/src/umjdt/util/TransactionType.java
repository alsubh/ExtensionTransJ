package umjdt.util;

import java.io.PrintWriter;
import java.io.Serializable;

public class TransactionType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	public static final int TOP_LEVEL = 0;
    public static final int NESTED = 1;


    public static String stringForm (int type)
    {
	        switch (type)
	        {
	        case TOP_LEVEL:
	            return "ActionType.TOP_LEVEL";
	        case NESTED:
	            return "ActionType.NESTED";
	        default:
	            return "Unknown";
	        }
	    }
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
	    
	public static void print (PrintWriter strm, int res)
	{
	     strm.print(stringForm(res));
	}
}