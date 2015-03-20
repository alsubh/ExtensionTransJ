package Bank;

import ATM.*;
import util.Money;
import util.Status;


public class Bank
{
	public Bank()
	{ }

	// The different types of account that can be linked to a card
	private static final int CHECKING = 0, SAVINGS = 1,MONEY_MARKET = 2;

	public int initiateWithdrawl(int cardNumber,int PIN,int ATMnumber,int serialNumber,int from,Money amount, Money newBalance, Money availableBalance)
	{ 
		if (cardNumber < 1 || cardNumber > NUMBER_ACCOUNTS)
			return Status.UNKNOWN_CARD;

		if (PIN != _PIN [ cardNumber ] )
			return Status.INVALID_PIN;

		_currentTransactionCard = cardNumber;
		_currentTransactionAccount = _accountNumber [ cardNumber ] [ from ];
		if (_currentTransactionAccount == 0)
			return Status.NO_SUCH_ACCOUNT;

		if (from == SAVINGS)
			return Status.CANT_WITHDRAW_FROM_ACCOUNT;

		if (_availableBalance [ _currentTransactionAccount ].less(amount))
			return Status.INSUFFICIENT_AVAILABLE_BALANCE;

		if (MAXIMUM_WITHDRAWL_AMOUNT_PER_DAY.less(
				Money.add(_withdrawlsToday [ cardNumber ], amount)))
			return Status.DAILY_WITHDRAWL_LIMIT_EXCEEDED;

		_currentTransactionAmount = amount;
		newBalance.set(Money.subtract(
				_currentBalance [ _currentTransactionAccount ], amount));
		availableBalance.set(Money.subtract(
				_availableBalance [ _currentTransactionAccount], amount));

		return Status.SUCCESS;
	}
	
	public void finishWithdrawl(int ATMnumber,int serialNumber,boolean succeeded)
	{ 
		if (succeeded)
		{ _withdrawlsToday [ _currentTransactionCard ].add(
				_currentTransactionAmount);
		_currentBalance [ _currentTransactionAccount ].subtract(
				_currentTransactionAmount);
		_availableBalance [ _currentTransactionAccount ].subtract(
				_currentTransactionAmount);
		}
	}
	
	public int initiateDeposit(int cardNumber,int PIN,int ATMnumber,int serialNumber,int to,Money amount,Money newBalance, Money availableBalance)
	{ 
		if (cardNumber < 1 || cardNumber > NUMBER_ACCOUNTS)
			return Status.UNKNOWN_CARD;

		if (PIN != _PIN[cardNumber])
			return Status.INVALID_PIN;

		_currentTransactionCard = cardNumber;
		_currentTransactionAccount = _accountNumber [ cardNumber ] [ to ];
		if (_currentTransactionAccount == 0)
			return Status.NO_SUCH_ACCOUNT;

		_currentTransactionAmount = amount;
		newBalance.set(Money.add(_currentBalance[_currentTransactionAccount], amount));
		availableBalance.set(_availableBalance[_currentTransactionAccount]);
		return Status.SUCCESS;
	}
	
	public void finishDeposit(int ATMnumber, int serialNumber,  boolean succeeded)
	{
		if (succeeded)
			_currentBalance [ _currentTransactionAccount ].add(_currentTransactionAmount);
	}
	
	public int doTransfer(int cardNumber,
			int PIN,
			int ATMnumber,
			int serialNumber,
			int from,
			int to,
			Money amount,
			Money newBalance,
			Money availableBalance)
	{ 
		if (cardNumber < 1 || cardNumber > NUMBER_ACCOUNTS)
			return Status.UNKNOWN_CARD;

		if (PIN != _PIN [ cardNumber ])
			return Status.INVALID_PIN;

		int fromAccount = _accountNumber [ cardNumber ] [ from ],
				toAccount = _accountNumber [ cardNumber ] [ to ];
		if (fromAccount == 0 || toAccount == 0)
			return Status.NO_SUCH_ACCOUNT;

		if (from == SAVINGS)
			return Status.CANT_WITHDRAW_FROM_ACCOUNT;

		if (_availableBalance [ fromAccount ].less(amount))
			return Status.INSUFFICIENT_AVAILABLE_BALANCE;

		_currentBalance [ fromAccount ].subtract(amount);
		_currentBalance [ toAccount ].add(amount);
		_availableBalance [ fromAccount ].subtract(amount);
		_availableBalance [ toAccount ].add(amount);

		newBalance.set(_currentBalance [ toAccount ]);
		availableBalance.set(_availableBalance [ toAccount ]);

		return Status.SUCCESS;
	}
	
	public int doInquiry(int cardNumber,
			int PIN,
			int ATMnumber,
			int serialNumber,
			int from,
			Money newBalance,
			Money availableBalance)
	{
		if (cardNumber < 1 || cardNumber > NUMBER_ACCOUNTS)
			return Status.UNKNOWN_CARD;

		if (PIN != _PIN [ cardNumber ] )
			return Status.INVALID_PIN;

		int account = _accountNumber [ cardNumber ] [ from ];
		if (account == 0)
			return Status.NO_SUCH_ACCOUNT;

		newBalance.set(_currentBalance [ account ]);
		availableBalance.set(_availableBalance [ account ]);

		return Status.SUCCESS;
	}
	
	public int chooseAccountType(String purpose, ATM atm)
	{
		String menu[] = { "Checking", "Savings", "Money Market" };
		String prologue = "Please choose an account to ";
		int choice = atm.getMenuChoice(prologue + purpose, 3, menu);
		return choice - 1;
	}
	
	public String accountName(int type)
    {
		return accountNames[type];
    }
	
	public String rejectionExplanation(int code)
    { 
		return explanation[code - Status.UNKNOWN_CARD];
    }
	
	// These constants are used by the accountName and rejectionDescription methods
    private static final String accountNames[] = { "CHKNG ", "SVNGS ", "MNYMKT" };
    
    private static final String explanation[] = 
      { 
        "Your card number was not recognized",
        "Invalid PIN",
        "No account of the type you requested is linked to your card",
        "You cannot withdraw money from this type of account at an ATM",
        "The available balance in your account is not sufficient",
        "You have exceeded the daily limit on cash withdrawls with your card" 
      };
    
    private static final int NUMBER_CARDS = 2;
    private static final int NUMBER_ACCOUNTS = 3;
    
    private static int _PIN [] =
        { 0, // dummy for nonexistent card 0
          42, 1234 
        };

      // Total withdrawls so far today using each card

      private static Money _withdrawlsToday  [] =
        { new Money(0),  //dummy for nonexistent card 0
          new Money(0),
          new Money(0)
        };

      private static final Money MAXIMUM_WITHDRAWL_AMOUNT_PER_DAY = new Money(300);

      // Accounts (CHECKING, SAVINGS, MONEY_MARKET) linked to each card - 0 = none

      private static final int _accountNumber [] [] =
        { { 0, 0, 0 }, // dummies for nonexistent card 0
          { 1, 2, 0 },
          { 1, 0, 3 }
        };

      // Current and available balances of each account

      private static Money _currentBalance [] =
        { new Money(0), // dummy for nonexistent account 0
          new Money(100), 
          new Money(1000),
          new Money(5000) 
        }; 

      private static Money _availableBalance [] =
        { new Money(0), // dummy for nonexistent account 0
          new Money(100), 
          new Money(1000),
          new Money(5000) 
        }; 

      // A real system would use the transaction commit/rollback mechanisms of an 
      // underlying DBMS - we just save the most recent transaction initiated in
      // a variable and complete if when we get the finish call.
      
      private static int _currentTransactionCard;
      private static int _currentTransactionAccount;
      private static Money _currentTransactionAmount;
    
}
