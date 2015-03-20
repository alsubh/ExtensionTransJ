package Transaction;


import ATM.*;
import Bank.*;
import Session.*;
import util.Money;
import util.Status;


class DepositTransaction extends Transaction
{

	private int   _toAccount;
	private Money _amount;

	public DepositTransaction(Session session, ATM atm, Bank bank)
	{ 
		super(session, atm, bank);
	}

	public int getTransactionSpecificsFromCustomer()
	{
		_toAccount = _bank.chooseAccountType("deposit to", _atm);
		_amount = _atm.getAmountEntry();
		return Status.SUCCESS;
	}

	public int sendToBank()
	{
		try
		{
			Thread.sleep(3 * 1000); 
		}
		catch (InterruptedException e)
		{

		}
		return _bank.initiateDeposit(_session.cardNumber(), _session.PIN(), _atm.number(), _serialNumber, _toAccount, _amount, _newBalance, _availableBalance);
	}

	public int finishApprovedTransaction()
	{
		boolean envelopeAccepted = _atm.acceptEnvelope();
		_bank.finishDeposit(_atm.number(), _serialNumber, envelopeAccepted);
		if (envelopeAccepted)
		{
			_atm.issueReceipt(_session.cardNumber(),_serialNumber,"DEPOSIT TO " + _bank.accountName(_toAccount),_amount,_newBalance,_availableBalance);
			return Status.SUCCESS;             
		}
		else
			return Status.ENVELOPE_DEPOSIT_TIMED_OUT;
	}


}
