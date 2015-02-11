package umjdt.concepts;

import java.util.ArrayList;
import java.util.List;
import umjdt.Events.*;
import umjdt.util.Status;

public class TransactionManager{

	private int tmId;
	private String name;
	private Status localTransactionStatus;
	private Transaction localTransaction;
	private TransId transId;
	private TransactionBeginEvent beginEvent;
	private TransactionCommitEvent commitEvent;
	private TransactionAbortEvent abortEvent;
	private ReleaseLockEvent releaseEvent;
	private RequestLockEvent requestLockEvent;
	private List<ResourceManager> resources = new ArrayList<>();
	private IsBlockedEvent isBlockEvent;
	private TransactionThread transactionThread;
	private TwoPhaseLock twoPhaseLock;
	private TwoPhaseCommitProtocol twoPhaseCommit;
	
	public TransactionManager() {
		// TODO Auto-generated constructor stub
	}
	public TransactionManager(int _tmId){
		this.tmId = _tmId;
	}
	public TransactionManager(int _tmId, String _name){
		this.tmId = _tmId;
		this.name=_name;
	}
	public TransactionManager(int _tmId, TransId _transId){
		this.tmId = _tmId;
		this.transId = _transId;
	}
	public TransactionManager(int _tmId, Transaction _trans){
		this.tmId = _tmId;
		this.localTransaction = _trans;
	}
	
	public int getTmId() {
		return tmId;
	}
	public void setTmId(int tmId) {
		this.tmId = tmId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Status getLocalTransactionStatus() {
		return localTransactionStatus;
	}
	public void setLocalTransactionStatus(Status localTransactionStatus) {
		this.localTransactionStatus = localTransactionStatus;
	}
	public TransId getTransaction() {
		return transId;
	}
	public void setTransaction(TransId transaction) {
		this.transId = transaction;
	}
	public TransactionBeginEvent getBeginEvent() {
		return beginEvent;
	}
	public void setBeginEvent(TransactionBeginEvent beginEvent) {
		this.beginEvent = beginEvent;
	}
	public TransactionCommitEvent getCommitEvent() {
		return commitEvent;
	}
	public void setCommitEvent(TransactionCommitEvent commitEvent) {
		this.commitEvent = commitEvent;
	}
	public TransactionAbortEvent getAbortEvent() {
		return abortEvent;
	}
	public void setAbortEvent(TransactionAbortEvent abortEvent) {
		this.abortEvent = abortEvent;
	}
	public ReleaseLockEvent getReleaseEvent() {
		return releaseEvent;
	}
	public void setReleaseEvent(ReleaseLockEvent releaseEvent) {
		this.releaseEvent = releaseEvent;
	}
	public RequestLockEvent getRequestLockEvent() {
		return requestLockEvent;
	}
	public void setRequestLockEvent(RequestLockEvent requestLockEvent) {
		this.requestLockEvent = requestLockEvent;
	}
	public List<ResourceManager> getResources() {
		return resources;
	}
	public void setResources(List<ResourceManager> resources) {
		this.resources = resources;
	}
	public IsBlockedEvent getIsBlockEvent() {
		return isBlockEvent;
	}
	public void setIsBlockEvent(IsBlockedEvent isBlockEvent) {
		this.isBlockEvent = isBlockEvent;
	}
	public TransactionThread getTransactionThread() {
		return transactionThread;
	}
	public void setTransactionThread(TransactionThread transactionThread) {
		this.transactionThread = transactionThread;
	}
	public TwoPhaseLock getTwoPhaseLock() {
		return twoPhaseLock;
	}
	public void setTwoPhaseLock(TwoPhaseLock twoPhaseLock) {
		this.twoPhaseLock = twoPhaseLock;
	}
	public TwoPhaseCommitProtocol getTwoPhaseCommit() {
		return twoPhaseCommit;
	}
	public void setTwoPhaseCommit(TwoPhaseCommitProtocol twoPhaseCommit) {
		this.twoPhaseCommit = twoPhaseCommit;
	}
	public Transaction getLocalTransaction() {
		return localTransaction;
	}
	public void setLocalTransaction(Transaction localTransaction) {
		this.localTransaction = localTransaction;
	}
}
