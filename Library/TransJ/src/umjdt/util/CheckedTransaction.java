package umjdt.util;

import java.util.Hashtable;
import java.util.logging.Logger;

import umjdt.concepts.TransId;

public class CheckedTransaction
{
    /**
     * Called during transaction termination if more than one thread is
     * associated with the transaction. The supplied information should be
     * sufficient for application specific implementations to do useful work
     * (such as synchronizing on the threads).
     * 
     * The default implementation simply prints a warning.
     */
	
	Logger log = Logger.getLogger(CheckedTransaction.class.getName());
	String msg ="";
    public void check (boolean isCommit, TransId transId, Hashtable list) // list of the subtransactions
    {
        if (isCommit)
        {
        	System.out.println(transId + Integer.toString(list.size()));
        	msg = "Transaction Id : "+ transId + "Number of SubTransactions :" +Integer.toString(list.size());
        	log.info(msg);
        }
        else
        {
        	System.out.println(transId + Integer.toString(list.size()));
        	msg = "Transaction Id : "+ transId + "Number of SubTransactions :" +Integer.toString(list.size());
            	log.info(msg);
        }
    }

}
