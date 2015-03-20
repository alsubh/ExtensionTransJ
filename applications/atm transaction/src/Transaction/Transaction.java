package Transaction;

import ATM.*;
import Bank.*;
import Session.*;
import util.Money;
import util.Status;

public abstract class Transaction
{

	public abstract int getTransactionSpecificsFromCustomer();
	public abstract int sendToBank();
	public abstract int finishApprovedTransaction();

	// Instance variables common to all kinds of transaction
	protected Session _session;
	protected ATM     _atm;
	protected Bank    _bank;

	// Every transaction is assigned a unique serial number that is used
	// to identify it in communications with the bank and on the receipt.
	protected int     _serialNumber;
	// Every transaction gets both the updated balance and available
	// balance for printing on the receipt.
	protected Money   _newBalance, _availableBalance;
	// This class member is used to assign serial numbers sequentially
	private static int _lastSerialNumberAssigned = 0;


	public Transaction(Session session, ATM atm, Bank bank)
	{
		_session = session;
		_atm = atm;
		_bank = bank;
		_serialNumber = ++ _lastSerialNumberAssigned;
		_newBalance = new Money();
		_availableBalance = new Money();
	}

	public static Transaction chooseTransaction(Session session, ATM atm, Bank bank)
	{ 
		String transactionMenu[] =
			{
				"Cash Withdrawl",
				"Deposit",
				"Transfer Funds Between Accounts",
				"Balance Inquiry"
			};
		switch (atm.getMenuChoice("Please choose a transaction type:", 4, transactionMenu))
		{
		case 1: return new WithdrawlTransaction(session, atm, bank);
		case 2: return new DepositTransaction(session, atm, bank);
		case 3: return new TransferTransaction(session, atm, bank);
		case 4: return new InquiryTransaction(session, atm, bank);
		default: return null; // To keep the compiler happy
		}
	}

	public int doTransactionUseCase()
	{
		int code;
		code = getTransactionSpecificsFromCustomer();
		if (code != Status.SUCCESS)
			return code;
		code = sendToBank();
		if (code == Status.INVALID_PIN)
		{
			code = _session.doInvalidPINExtension();
			if (code == Status.INVALID_PIN)
				return code;
		}
		if (code == Status.SUCCESS)
			code = finishApprovedTransaction();
		return code;
	}

}
