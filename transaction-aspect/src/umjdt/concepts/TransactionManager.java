package umjdt.concepts;

import umjdt.util.Participant;


public class TransactionManager extends com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionManagerImple implements Participant
{
	private Transaction transaction;
	
	public TransactionManager() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String participantName() 
	{
		// TODO Auto-generated method stub
		return "TM";
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
}
