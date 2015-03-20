package ATMPhysical;

import java.awt.*;

public class EnvelopeAcceptor extends Button
{
	public EnvelopeAcceptor()
	{ 
		super("Click to insert Envelope");
		_originalBounds = null;  // Must get this after GUI is all laid out
	}

	public synchronized boolean acceptEnvelope()
	{ 
		if (_originalBounds == null)
			_originalBounds = bounds();
		else
			reshape(_originalBounds.x, _originalBounds.y, _originalBounds.width, _originalBounds.height);

		show();
		repaint();
		requestFocus();

		// Wait for user to simulate inserting envelope by clicking button.
		// If we wait 20 seconds and no envelope is entered, we time out

		try
		{
			wait(20 * 1000); }
		catch(InterruptedException e)
		{ }

		if (! _inserted)
		{ 
			hide();
			return _inserted;
		}

		// Animate envelope going into the machine

		Rectangle currentBounds =
				new Rectangle(_originalBounds.x, _originalBounds.y,_originalBounds.width, _originalBounds.height);

		while (currentBounds.width > 0 && currentBounds.height > 0)
		{ 
			reshape(currentBounds.x, currentBounds.y,currentBounds.width, currentBounds.height);
			repaint();
			try 
			{ 
				Thread.sleep(100); } 
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

		return _inserted;
	}

	// Method and private data needed for GUI

	public synchronized boolean action(Event e, Object arg)
	{
		if (e.target == this)
		{ 
			_inserted = true;
			notify();
			return true;
		}
		else
			return false;
	}    

	private Rectangle _originalBounds;
	private boolean _inserted;
}
