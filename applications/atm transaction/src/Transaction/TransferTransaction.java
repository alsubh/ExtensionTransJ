package Transaction;

import ATM.*;
import Bank.*;
import Session.*;
import util.Money;
import util.Status;

class TransferTransaction extends Transaction
{
	private int   _fromAccount;
	private int   _toAccount;
	private Money _amount;

	public TransferTransaction(Session session, ATM atm, Bank bank)
	{ super(session, atm, bank);
	}

	public int getTransactionSpecificsFromCustomer()
	{
		_fromAccount = _bank.chooseAccountType("transfer from", _atm);
		_toAccount = _bank.chooseAccountType("transfer to", _atm);
		_amount = _atm.getAmountEntry();
		return Status.SUCCESS;
	}

	public int sendToBank()
	{
		return _bank.doTransfer(_session.cardNumber(),
				_session.PIN(),
				_atm.number(),
				_serialNumber,
				_fromAccount,
				_toAccount,
				_amount,
				_newBalance,
				_availableBalance);
	}

	public int finishApprovedTransaction()
	{
		_atm.issueReceipt(_session.cardNumber(),
				_serialNumber,
				"TRANSFER " + _bank.accountName(_fromAccount) +
				" TO " + _bank.accountName(_toAccount),
				_amount,
				_newBalance,
				_availableBalance);
		return Status.SUCCESS;
	}


}
