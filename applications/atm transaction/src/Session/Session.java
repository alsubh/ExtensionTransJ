package Session;

import ATM.*;
import Transaction.Transaction;
import Transaction.Transaction.*;
import util.Status;
import util.Money;
import Bank.Bank;


public class Session 
{
	// Possible values for _state instance variable

    private static final int RUNNING = 0;
    private static final int FINISHED = 1;
    private static final int ABORTED = 2;
    
    // Instance variables
    
    private int         _cardNumber;
    private ATM         _atm;
    private Bank        _bank;
    private int         _state;
    private int         _PIN;
    private Transaction _currentTransaction;
    
    
	public Session(int cardNumber, ATM atm, Bank bank)
    { _cardNumber = cardNumber;
      _atm = atm;
      _bank = bank;
      _state = RUNNING;
      _PIN = 0;
      _currentTransaction = null;
    }
	
	public void doSessionUseCase()
    {
      _PIN = _atm.getPIN();
      
      do
        {
          String anotherMenu[] = { "Yes", "No" };
          _currentTransaction = Transaction.chooseTransaction(this, _atm, _bank);
          int status = _currentTransaction.doTransactionUseCase();
          switch (status)
            {
              case Status.SUCCESS:
  
                if (1 != _atm.getMenuChoice
                  ("Do you want to perform another transaction?",2,anotherMenu))
                    _state = FINISHED;
                break;
              
              case Status.INVALID_PIN:
               
                _state = ABORTED;
                break;
               
             default:
               
               boolean doAnother = doFailedTransactionExtension(status);
               if (! doAnother)
                 _state = FINISHED;
            }
         }
      while (_state == RUNNING);
  
      if (_state != ABORTED) 
          _atm.ejectCard();
    }
	
	public int doInvalidPINExtension()
    { 
      int code;
      for (int i = 0; i < 3; i ++)
        { _PIN = _atm.reEnterPIN();
          code = _currentTransaction.sendToBank();     
          if (code != Status.INVALID_PIN)
              return code;
        }
      _atm.retainCard();
      _state = ABORTED;
      return Status.INVALID_PIN;
    }
	
	public boolean doFailedTransactionExtension(int reason)
    { switch(reason)
        { 
          case Status.TOO_LITTLE_CASH:
  
              return _atm.reportTransactionFailure(
           "Sorry, there is not enough cash available to satisfy your request");
  
          case Status.ENVELOPE_DEPOSIT_TIMED_OUT:
  
              return _atm.reportTransactionFailure(
           "Envelope not deposited - transaction cancelled");
           
          default:
  
              return _atm.reportTransactionFailure(
                            _bank.rejectionExplanation(reason));
  
        } 
    }
  
	public int cardNumber()
    {
		return _cardNumber; 
    }
	
	 public int PIN()
     {
		 return _PIN; 
     }
	 
	 
}
