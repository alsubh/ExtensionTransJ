package ATM;

import java.awt.*;

import ATMPhysical.CardReader;
import Session.Session;
import ATMPhysical.*;
import util.Status;
import util.Money;
import Bank.*;

public class ATM 
{
	// Private instance variables
	private int              _number;
	private String           _location;
	private Bank             _bank;

	// Values for _state instance variable
	private static final int RUNNING = 0;
	private static final int STOPPED = 1;

	private int              _state;

	private CardReader       _cardReader;
	private Display          _display;
	private Keyboard         _keyboard;
	private CashDispenser    _cashDispenser;
	private EnvelopeAcceptor _envelopeAcceptor;
	private ReceiptPrinter   _receiptPrinter;
	private OperatorPanel    _operatorPanel;


	public ATM(int number, String location, Bank bank, Container container)
	{
		super();

		_number = number;
		_location = location;
		_bank = bank;

		_cardReader = new CardReader();
		_display = new Display();
		_keyboard = new Keyboard();
		_cashDispenser = new CashDispenser();
		_envelopeAcceptor = new EnvelopeAcceptor();
		_receiptPrinter = new ReceiptPrinter();
		_operatorPanel = new OperatorPanel();

		GUILayout.doLayout(container,
				_cardReader, _display, _keyboard, _cashDispenser,
				_envelopeAcceptor, _receiptPrinter, _operatorPanel);
	}

	public synchronized Money startupOperation()
	{ 
		// Wait for switch to be turned on.  Message will blink on and off
		// to tell user what to do
		while (! _operatorPanel.switchOn()) 
			try
		{ 
				Thread.sleep(1000); 
		}
		catch (InterruptedException e)
		{ 

		}
		_state = RUNNING;
		return _operatorPanel.getInitialCash(); 
	}

	public void serviceCustomers(Money initialCash)
	{
		_cashDispenser.setCash(initialCash);

		while (_state == RUNNING)
		{ 
			int readerStatus = CardReader.NO_CARD;  // Initialization needed only
			// to keep the compiler happy!
			_display.requestCard();
			do
			{ 
				if (! _operatorPanel.switchOn())
					_state = STOPPED;
				else
					readerStatus = _cardReader.checkForCardInserted();
			}
			while (_state == RUNNING && readerStatus == CardReader.NO_CARD);
			_display.clearDisplay();

			if (_state == RUNNING)
				switch (readerStatus)
				{
				case CardReader.CARD_HAS_BEEN_READ:      
				{
					Session session = new Session(_cardReader.cardNumber(),this,_bank);
					session.doSessionUseCase();
					break;
				}
				case CardReader.UNREADABLE_CARD:

					_display.reportCardUnreadable();
					_cardReader.ejectCard();
					_display.clearDisplay();
				}
		}
	}

	public int getPIN()
	{
		_display.requestPIN();
		int PIN = _keyboard.readPIN(_display);
		_display.clearDisplay();
		return PIN;
	}

	public int getMenuChoice(String whatToChoose, int numItems, String items[])
	{
		_display.displayMenu(whatToChoose, numItems, items);
		int choice =  _keyboard.readMenuChoice(numItems);
		_display.clearDisplay();
		return choice;
	} 

	public Money getAmountEntry()
	{
		_display.requestAmountEntry();
		Money amount = _keyboard.readAmountEntry(_display);
		_display.clearDisplay();
		return amount;
	}

	public boolean checkIfCashAvailable(Money amount)
	{
		return ! _cashDispenser.currentCash().less(amount);
	}

	public void dispenseCash(Money amount)
	{
		_cashDispenser.dispenseCash(amount);
	}

	public boolean acceptEnvelope()
	{
		return _envelopeAcceptor.acceptEnvelope(); 
	}

	public void issueReceipt(int cardNumber, int serialNumber, String description, Money amount,Money balance, Money availableBalance)
	{ 
		_receiptPrinter.printReceipt(_number, _location, cardNumber, serialNumber, description, amount,balance, availableBalance);;
	}

	public int reEnterPIN()
	{ _display.requestReEnterPIN();
	int PIN = _keyboard.readPIN(_display);
	_display.clearDisplay();
	return PIN;
	}

	public boolean reportTransactionFailure(String explanation)
	{ 
		_display.reportTransactionFailure(explanation);
		int response = _keyboard.readMenuChoice(2);
		_display.clearDisplay();
		return response == 1;
	}

	public void ejectCard()
	{
		_cardReader.ejectCard();
	}

	public void retainCard()
	{
		_display.reportCardRetained();
		_cardReader.retainCard(); 
		_display.clearDisplay();
	}

	public int number()
	{ 
		return _number; 
	}
}
