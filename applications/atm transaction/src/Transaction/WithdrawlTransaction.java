package Transaction;

import ATM.*;
import Bank.*;
import Session.*;
import util.Money;
import util.Status;

class WithdrawlTransaction extends Transaction
{
	// Instance variables of WithdrawlTransaction

	private int   _fromAccount;
	private Money _amount;

	public WithdrawlTransaction(Session session, ATM atm, Bank bank)
	{ 
		super(session, atm, bank);
	}

	public int getTransactionSpecificsFromCustomer()
	{
		_fromAccount = _bank.chooseAccountType("withdraw from", _atm);
		String menu[] = { "$ 20", "$ 40", "$ 60", "$ 80", "$ 100", "$ 200", "$ 300" };
		switch (_atm.getMenuChoice("Please choose an amount:", 7, menu))
		{
		case 1: _amount = new Money(20); break;
		case 2: _amount = new Money(40); break;
		case 3: _amount = new Money(60); break;
		case 4: _amount = new Money(80); break;
		case 5: _amount = new Money(100); break;
		case 6: _amount = new Money(200); break;
		case 7: _amount = new Money(300); break;
		}
		if (_atm.checkIfCashAvailable(_amount))
			return Status.SUCCESS;
		else
			return Status.TOO_LITTLE_CASH;
	}

	public int sendToBank()
	{
		return _bank.initiateWithdrawl(_session.cardNumber(),_session.PIN(),_atm.number(),_serialNumber,_fromAccount,_amount,_newBalance,_availableBalance);
	}

	public int finishApprovedTransaction()
	{
		_atm.dispenseCash(_amount);
		_bank.finishWithdrawl(_atm.number(), _serialNumber, true);
		_atm.issueReceipt(_session.cardNumber(),
				_serialNumber, 
				"WITHDRAWL FROM " + _bank.accountName(_fromAccount),
				_amount,
				_newBalance,
				_availableBalance);
		return Status.SUCCESS;
	}

}
