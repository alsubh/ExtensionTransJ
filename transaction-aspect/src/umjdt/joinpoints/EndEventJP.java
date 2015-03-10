package umjdt.joinpoints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

import javax.transaction.TransactionManager;

import com.arjuna.ats.arjuna.common.Uid;

import umjdt.concepts.Resource;
import umjdt.concepts.TID;
import umjdt.concepts.Transaction;
import umjdt.util.Constants;
import umjdt.util.Timestamp;

public class EndEventJP extends TransJP
{
		private javax.transaction.Transaction xatransaction;
		private TransactionManager manager;
		private Timestamp endTime; 
		private int timeout;
		private int status;
		private TID tid;
		private List<Transaction> transactionlist;
		private Map<Uid, javax.transaction.Transaction> transactions= new HashMap<Uid, javax.transaction.Transaction>();
		private List<Resource> resources= new ArrayList<Resource>();
		
		
		public EndEventJP()
		{
			setTimeout((int) Constants.TimeToWait);
		}
		
		public EndEventJP(Transaction transaction) 
		{
			super();
		}

		public TransactionManager getTm() {
			return manager;
		}

		public void setManager(TransactionManager tm) {
			this.manager = tm;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public TID getTid() {
			return tid;
		}

		public void setTid(TID tid) {
			this.tid = tid;
		}
		public int getTimeout() {
			return timeout;
		}

		public void setTimeout(int timeout) {
			this.timeout = timeout;
		}

		public javax.transaction.Transaction getXatransaction() {
			return xatransaction;
		}

		public void setXatransaction(javax.transaction.Transaction xatransaction) {
			this.xatransaction = xatransaction;
		}

		public Timestamp getEndTime() {
			return endTime;
		}

		public void setEndTime(Timestamp endTime) {
			this.endTime = endTime;
		}

		public List<Transaction> getTransactionlist() {
			return transactionlist;
		}

		public void setTransactionlist(List<Transaction> transactionlist) {
			this.transactionlist = transactionlist;
		}

		public Map<Uid, javax.transaction.Transaction> getTransactions() {
			return transactions;
		}

		public void setTransactions(Map<Uid, javax.transaction.Transaction> transactions) {
			this.transactions = transactions;
		}

		public List<Resource> getResources() {
			return resources;
		}

		public void setResources(List<Resource> resources) {
			this.resources = resources;
		}

		class BeginTask extends TimerTask 
		{
		    public void run() {
		    	logger.log(Level.INFO, BeginTask.class.toString());
		      System.out.println("End Transaction!");
		      //System.exit(0); //Stops everything 
		    }
		 }	
}