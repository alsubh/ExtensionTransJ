package umjdt.util;

import java.util.Hashtable;
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

    public void check (boolean isCommit, TransId transId, Hashtable list)
    {
        if (isCommit)
        	System.out.println(transId + Integer.toString(list.size()));
        else
        	System.out.println(transId + Integer.toString(list.size()));
    }

}
