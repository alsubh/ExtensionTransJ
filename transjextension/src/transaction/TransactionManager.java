package transaction;

import utilities.Participant;

public class TransactionManager extends com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionManagerImple implements Participant
{
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
}
