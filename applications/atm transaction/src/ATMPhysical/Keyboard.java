package ATMPhysical;

import java.awt.*;
import util.Money;

public class Keyboard extends Panel
{
	// Each individual key is represented by a button

	private Button _numberKey[];  // _numberKey[i] represents digit i
	private Button _enterKey;
	private Button _clearKey;

	// Record the button clicked

	private int _buttonClicked;
	private static final int NONE = -1;
	private static final int ENTER = 10;
	private static final int CLEAR = 11; 


	public Keyboard()
	{ 
		super();
		setLayout(new GridLayout(4,3));
		_numberKey = new Button[10];
		for (int i = 1; i < 10; i ++)
		{
			_numberKey[i] = new Button("" + i);
			add(_numberKey[i]);
		}
		_enterKey = new Button("Enter");
		_enterKey.setForeground(Color.black);
		_enterKey.setBackground(new Color(128, 128, 255));
		add(_enterKey);
		_numberKey[0] = new Button("0");
		add(_numberKey[0]);
		_clearKey = new Button("Clear");
		_clearKey.setForeground(Color.black);
		_clearKey.setBackground(new Color(255, 128, 128));
		add(_clearKey);
	}

	public int readPIN(Display echoOn)
	{
		StringBuffer result = new StringBuffer("");
		StringBuffer echo = new StringBuffer("");
		int keyClicked;
		do
		{
			keyClicked = inKey();
			switch (keyClicked)
			{ case ENTER:
				// If a legitimate integer has been entered, return it;
				// otherwise fall through to clear case and make user
				// start over
				try
				{
					return Integer.parseInt(result.toString()); 
				}
				catch (NumberFormatException e)
				{ }
			case CLEAR:
				result.setLength(0);
				echo.setLength(0);
				break;
			default:
				result.append(keyClicked);
				echo.append('*');
			}
			echoOn.echoInput(echo.toString());
		}
		while (true);
	}

	public int readMenuChoice(int numItems)
	{
		int key;
		do
		{ key = inKey();
		}
		while (key < 1 || key > numItems);
		return key;
	}

	public Money readAmountEntry(Display echoOn)
	{
		StringBuffer cents = new StringBuffer("  "),
				dollars = new StringBuffer("");
		echoOn.echoInput(".  ");
		int keyClicked;
		do
		{
			keyClicked = inKey();
			switch (keyClicked)
			{ case ENTER:
				// If a legitimate amout has been entered, return it;
				// otherwise fall through to clear case and make user
				// start over
				try
				{
					if (dollars.length() == 0) dollars.append('0');
					if (cents.charAt(0) == ' ') cents.setCharAt(0, '0');
					return new Money(Integer.parseInt(dollars.toString()),
							Integer.parseInt(cents.toString()));
				}
				catch (NumberFormatException e)
				{ }
			case CLEAR:
				cents.setLength(0);
				cents.append("  ");
				dollars.setLength(0);
				break;
			default:
				if (cents.charAt(0) != ' ')
					dollars.append(cents.charAt(0));
				cents.setCharAt(0, cents.charAt(1));
				cents.setCharAt(1, Character.forDigit(keyClicked, 10));
			}
			echoOn.echoInput(dollars.toString() + "." + cents.toString());
		}
		while (true);
	}


	// Methods and private data needed for GUI

	private synchronized int inKey()
	{ 
		_buttonClicked = NONE;
		requestFocus();
		do
		{ try
		{ wait(); }
		catch (InterruptedException e)
		{ }
		}
		while (_buttonClicked == NONE);
		return _buttonClicked;
	}

	public synchronized boolean action(Event e, Object arg)
	{ 
		for (int i = 0; i < 10; i ++)
			if (e.target == _numberKey[i])
				_buttonClicked = i;
		if (e.target == _enterKey)
			_buttonClicked = ENTER;
		if (e.target == _clearKey)
			_buttonClicked = CLEAR;
		if (_buttonClicked != NONE)
		{ notify();
		return true;
		}
		else
			return false;
	}
}