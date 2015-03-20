package ATMPhysical;

import java.awt.*;

public class CardReader extends Button
{
	// These are the values that can be returned by checkForCardInserted()

	public static final int NO_CARD = 0;
	public static final int UNREADABLE_CARD = 1;
	public static final int CARD_HAS_BEEN_READ = 2;

	// Instance variables
	private int _status;
	private int _cardNumberRead; 


	public CardReader()
	{
		super("Click to insert Card");
		_status = NO_CARD;
		_originalBounds = null;  // Must get this after GUI is all laid out
	}

	@SuppressWarnings("deprecation")
	public void ejectCard()
	{ // Animate card coming out of machine

		setLabel("Ejecting card");
		Rectangle currentBounds = 
				new Rectangle(_originalBounds.x + _originalBounds.width / 2,
						_originalBounds.y + _originalBounds.height / 2,
						_originalBounds.width / _originalBounds.height, 1);
		show();

		while (currentBounds.height <= _originalBounds.height &&
				currentBounds.width <= _originalBounds.width)
		{ reshape(currentBounds.x, currentBounds.y,
				currentBounds.width, currentBounds.height);
		repaint();
		try 
		{ Thread.sleep(100); } 
		catch (InterruptedException e) 
		{ }
		currentBounds.height += 1;
		currentBounds.width = 
				(_originalBounds.width * currentBounds.height) / _originalBounds.height;
		currentBounds.x =
				_originalBounds.x + (_originalBounds.width - currentBounds.width) / 2;
		currentBounds.y =
				_originalBounds.y + (_originalBounds.height - currentBounds.height) / 2;
		}

		hide();

		_status = NO_CARD; 
	}

	public void retainCard()
	{ 
		_status = NO_CARD;
		try
		{ 
			Thread.sleep(10 * 1000); 
		}
		catch (InterruptedException e)
		{ 

		} 
	}

	public synchronized int checkForCardInserted()
	{ 
		// Wait until user clicks the "Click To Insert Card" button, or operator
		// Turns the machine off.  (The timeout in the wait ensures that we will
		// return periodically so that ATM can recheck the switch status.)

		if (_originalBounds == null)
			_originalBounds = bounds();
		else
			reshape(_originalBounds.x, _originalBounds.y,
					_originalBounds.width, _originalBounds.height);

		setLabel("Click to insert card");
		show();
		repaint();
		requestFocus();

		try
		{ wait(1000); }
		catch (InterruptedException e)
		{ }
		if (_status == NO_CARD)   // This happens if we timeout
		{ hide();
		return NO_CARD;
		}

		// Animate card going into the machine

		Rectangle currentBounds =
				new Rectangle(_originalBounds.x, _originalBounds.y,
						_originalBounds.width, _originalBounds.height);

		while (currentBounds.width > 0 && currentBounds.height > 0)
		{ reshape(currentBounds.x, currentBounds.y,
				currentBounds.width, currentBounds.height);
		repaint();
		try 
		{ Thread.sleep(100); } 
		catch (InterruptedException e) 
		{ }
		currentBounds.height -= 1;
		currentBounds.width = 
				(_originalBounds.width * currentBounds.height) / _originalBounds.height;
		currentBounds.x =
				_originalBounds.x + (_originalBounds.width - currentBounds.width) / 2;
		currentBounds.y =
				_originalBounds.y + (_originalBounds.height - currentBounds.height) / 2;
		}

		hide();  

		// Since we don't have a magnetic stripe reader (or a literal card!),
		// pop up a dialog and ask user to enter the card number.  

		QuestionDialog cardNumberDialog = 
				new QuestionDialog("Enter card number:", this);

		String answer = cardNumberDialog.answer();

		// Extract the number typed.  Typing a non-number simulates an unreadable
		// card.

		if (answer == null)
			_status = UNREADABLE_CARD;
		else
		{ try
		{ _cardNumberRead = Integer.parseInt(answer); 
		_status = CARD_HAS_BEEN_READ;
		}
		catch (NumberFormatException e)
		{ _status = UNREADABLE_CARD;
		}
		}
		return _status;
	}

	public int cardNumber()
	{ 
		return _cardNumberRead;
	}

	/* The following method and field are needed for the GUI */

	public synchronized boolean action(Event e, Object arg)
	{ if (e.target == this)
	{ _status = CARD_HAS_BEEN_READ;
	notify();
	return true;
	}
	else
		return false;
	}

	private Rectangle _originalBounds;
}

