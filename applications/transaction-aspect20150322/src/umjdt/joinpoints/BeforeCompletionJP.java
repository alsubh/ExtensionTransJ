/**
 * 
 */
package umjdt.joinpoints;

import org.aspectj.lang.JoinPoint;

import umjdt.concepts.TID;
import umjdt.concepts.Transaction;
import umjdt.util.Timestamp;

/**
 * @author AnasAlsubh
 * 
 */
public class BeforeCompletionJP {
	private TID tid;
	private Transaction transaction;
	private JoinPoint beforeCompletionJp;
	private Timestamp timestamp;

	public BeforeCompletionJP() {
		setTimestamp(new Timestamp().currentTimeStamp());
	}

	public BeforeCompletionJP(TID _tid) {
		this.setTid(_tid);
		setTimestamp(new Timestamp().currentTimeStamp());
	}

	public BeforeCompletionJP(Transaction _transaction) {
		this.setTransaction(_transaction);
		setTimestamp(new Timestamp().currentTimeStamp());
	}

	public TID getTid() {
		return tid;
	}

	public void setTid(TID tid) {
		this.tid = tid;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public JoinPoint getAfterCompletionJp() {
		return beforeCompletionJp;
	}

	public void setAfterCompletionJp(JoinPoint afterCompletionJp) {
		this.beforeCompletionJp = afterCompletionJp;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
