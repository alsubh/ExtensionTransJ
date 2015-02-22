package umjdt.util;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.Objects;
import org.omg.CORBA.portable.ApplicationException;

import umjdt.concepts.TransId;

public class OperationNumber implements Comparable, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static short nextSeqNumber = 1;  // Start with operation #1
    public static OperationNumber Empty;
    //public static short LocalTransactionId; // Local transaction Id -- set once when the operation joins the transaction application
    //public short transactionId;
    public static TransId LocalTransactionId; // Local transaction Id -- set once when the operation joins the transaction application
    public TransId transactionId;
    public short SeqOperationNumber;
    
    public static OperationNumber Create() {
    	OperationNumber result = new OperationNumber();
        result.transactionId = LocalTransactionId;
        result.SeqOperationNumber = GetNextSeqNumber();
        return result;
    }
    
    public static OperationNumber Create(TransId _transactionId)
    {
    	OperationNumber result = new OperationNumber();
        result.transactionId = _transactionId;
        result.SeqOperationNumber = GetNextSeqNumber();
        return result;
    }

    public static OperationNumber Create(TransId _transactionId, short _operationSeqNur) {
    	OperationNumber result = new OperationNumber();
        result.transactionId = _transactionId;
        result.SeqOperationNumber = _operationSeqNur;
        return result;
    }

    private OperationNumber() {
    }

    @Override
    public String toString() {
        return transactionId + "." + SeqOperationNumber;
    }

    public boolean Equals(Object obj) {
        boolean tag = false;
        int result = Compare(this, (OperationNumber) obj);

        if (result > 0) {
            tag = false;
        } else if (result < 0) {
            tag = false;
        } else if (result == 0) {
            tag = true;
        }
        return tag;
    }

    public static int Compare(OperationNumber a, OperationNumber b) {
        int result = 0;

        if (!(a == b)) {
            if (((Object) a == null) && ((Object) b != null)) {
                result = -1;
            } else if (((Object) a != null) && ((Object) b == null)) {
                result = 1;
            } else {
                if (!a.transactionId.equals(b.transactionId)) {// Operation for different transactions. 
                    result = -1;
                } else if (a.transactionId.equals(b.transactionId)) {// operations for the transaction.
                    result = 1;
                } else if (a.SeqOperationNumber < b.SeqOperationNumber) { // the operation a is happened before the operation b
                    result = 2;
                } else if (a.SeqOperationNumber > b.SeqOperationNumber) { //the operation a is happened after the operation b
                    result = 3;
                }
            }
        }
        return result;
    }
    
    public static int CompareOperationsOn(OperationNumber a, OperationNumber b) {
        int result = 0;

        if (!(a == b)) {
            if (((Object) a == null) && ((Object) b != null)) {
                result = -1;
            } else if (((Object) a != null) && ((Object) b == null)) {
                result = 1;
            } else
            	{
            		if (a.transactionId.equals(b.transactionId)) {// operations on the same transaction.
            		}
	                    if (a.SeqOperationNumber < b.SeqOperationNumber) 
	                    { // the operation a is happened before the operation b
	                    	result = 2;
	                    } 
	                    else if (a.SeqOperationNumber > b.SeqOperationNumber) 
	                    { //the operation a is happened after the operation b
	                    	result = 3;
	                    }
            	}
        }
        return result;
    }

    public static boolean operatorEqual(OperationNumber a, OperationNumber b) {
        return (Compare(a, b) == 0);
    }

    public static boolean operatorNotEqual(OperationNumber a, OperationNumber b) {
        return (Compare(a, b) != 0);
    }

    public static boolean operatorLessThan(OperationNumber a, OperationNumber b) {
        return (Compare(a, b) < 0);
    }

    public static boolean operatorGreaterThan(OperationNumber a, OperationNumber b) {
        return (Compare(a, b) > 0);
    }

    public static boolean operatorLessThankOrEqual(OperationNumber a, OperationNumber b) {
        return (Compare(a, b) <= 0);
    }

    public static boolean operatorGreaterThanOrEqual(OperationNumber a, OperationNumber b) {
        return (Compare(a, b) >= 0);
    }

    public int CompareTo(Object obj) {
        return Compare(this, (OperationNumber) obj);
    }

    public static OperationNumber getEmpty() {
        return new OperationNumber();
    }

    public static void setEmpty(OperationNumber empty) {
        Empty = empty;
    }

    public short getSeqNumber() {
        return SeqOperationNumber;
    }

    public void setSeqNumber(short seqNumber) {
        SeqOperationNumber = seqNumber;
    }

    private static short GetNextSeqNumber() {
        if (nextSeqNumber == Short.MAX_VALUE) {
            nextSeqNumber = 1;
        }
        return nextSeqNumber++;
    }

    @Override
    public int compareTo(Object obj) {
        return Compare(this, (OperationNumber) obj);
    }
}