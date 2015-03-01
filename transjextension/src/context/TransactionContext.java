package context;

import java.util.List;
import java.util.UUID;

import joinpoint.TransJP;
import joinpoint.transaction.AbortEventJP;
import joinpoint.transaction.BeginEventJP;
import joinpoint.transaction.CommitEventJP;
import joinpoint.transaction.InnerCompletionJP;
import joinpoint.transaction.OuterCompletionJP;
import joinpoint.transaction.TransactionJP;
import transaction.Resource;
import transaction.TID;
import transaction.Transaction;
import utilities.Participant;
import utilities.TransactionType;


public class TransactionContext extends Context
{
	private UUID cid;
	private TransactionJP transactionJp;
	private List<Resource> resources;
	// Transaction Manager, Resource Manager, or coordinator
	private Participant participant;
	
	// transaction events
	private BeginEventJP beginEventJp;
	private AbortEventJP abortEventJp;
	private CommitEventJP commitEventJp;
	private InnerCompletionJP innerCompletionJp;
	private OuterCompletionJP outerComplectionJp;
	
	
	public TransactionContext() 
	{
		super();
		this.setCid(UUID.randomUUID());
	}

	public TransactionContext(TID _tid, Transaction _transaction,
			TransJP _transJP, TransactionType _type) 
	{
		super(_tid, _transaction, _transJP, _type);
		this.setCid(UUID.randomUUID());
	}

	public TransactionContext(TransJP _transJp)
	{
		super(_transJp);
		this.setCid(UUID.randomUUID());
	}
	
	public TransactionContext(UUID cid, TransactionJP transactionJp,
			List<Resource> resources, Participant participant,
			BeginEventJP beginEventJp, AbortEventJP abortEventJp,
			CommitEventJP commitEventJp, InnerCompletionJP innerCompletionJp,
			OuterCompletionJP outerComplectionJp)
	{
		super();
		this.setCid(UUID.randomUUID());
		this.setTransactionJp(transactionJp);
		this.setResources(resources);
		this.setParticipant(participant);
		this.setBeginEventJp(beginEventJp);
		this.setAbortEventJp(abortEventJp);
		this.setCommitEventJp(commitEventJp);
		this.setInnerCompletionJp(innerCompletionJp);
		this.setOuterComplectionJp(outerComplectionJp);
	}



	public UUID getCid() {
		return cid;
	}



	public void setCid(UUID cid) {
		this.cid = cid;
	}



	public TransactionJP getTransactionJp() {
		return transactionJp;
	}



	public void setTransactionJp(TransactionJP transactionJp) {
		this.transactionJp = transactionJp;
	}



	public List<Resource> getResources() {
		return resources;
	}



	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}



	public Participant getParticipant() {
		return participant;
	}



	public void setParticipant(Participant participant) {
		this.participant = participant;
	}



	public BeginEventJP getBeginEventJp() {
		return beginEventJp;
	}



	public void setBeginEventJp(BeginEventJP beginEventJp) {
		this.beginEventJp = beginEventJp;
	}



	public AbortEventJP getAbortEventJp() {
		return abortEventJp;
	}



	public void setAbortEventJp(AbortEventJP abortEventJp) {
		this.abortEventJp = abortEventJp;
	}



	public CommitEventJP getCommitEventJp() {
		return commitEventJp;
	}



	public void setCommitEventJp(CommitEventJP commitEventJp) {
		this.commitEventJp = commitEventJp;
	}



	public InnerCompletionJP getInnerCompletionJp() {
		return innerCompletionJp;
	}



	public void setInnerCompletionJp(InnerCompletionJP innerCompletionJp) {
		this.innerCompletionJp = innerCompletionJp;
	}



	public OuterCompletionJP getOuterComplectionJp() {
		return outerComplectionJp;
	}



	public void setOuterComplectionJp(OuterCompletionJP outerComplectionJp) {
		this.outerComplectionJp = outerComplectionJp;
	}
	
}
