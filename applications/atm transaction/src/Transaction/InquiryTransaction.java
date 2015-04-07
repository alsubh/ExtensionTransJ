package Transaction;

import ATM.*;
import Bank.*;
import Session.*;
import util.Money;
import util.Status;

class InquiryTransaction extends Transaction
{
	private int   _fromAccount;
	
	public InquiryTransaction(Session session, ATM atm, Bank bank)
    {
		super(session, atm, bank); 
	}
	
	public int getTransactionSpecificsFromCustomer()
    {
      _fromAccount = _bank.chooseAccountType("balance for", _atm);
      return Status.SUCCESS;
    }
	
	public int sendToBank()
    {
      return _bank.doInquiry(_session.cardNumber(), _session.PIN(),_atm.number(), _serialNumber, _fromAccount, _newBalance, _availableBalance);
    }
	
	public int finishApprovedTransaction()
    {
      _atm.issueReceipt(_session.cardNumber(), _serialNumber, "INQUIRY FROM " + _bank.accountName(_fromAccount), new Money(0), _newBalance, _availableBalance);
       return Status.SUCCESS;
    }
}
