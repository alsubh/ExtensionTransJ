package ATMPhysical;

import java.awt.*;
import java.util.StringTokenizer;


public class Display extends Panel
{

	private Label  _line[];
	private int    _currentLine;

	public Display()
	{ 
		setLayout(new GridLayout(GUILayout.DISPLAYABLE_LINES, 1));
		setBackground(new Color(0, 96, 0));  // Dark green
		setForeground(Color.white);
		_line = new Label[GUILayout.DISPLAYABLE_LINES];
		for (int i = 0; i < GUILayout.DISPLAYABLE_LINES; i ++)
		{ _line[i] = new Label("");
		add(_line[i]);
		}
		_currentLine = 0;
	}

	public void requestCard()
	{ write("Please insert your card to begin");
	}


	public void requestPIN()
	{ 
		write("Please enter your Personal Identification Number (PIN)\n" + "Press ENTER when finished or CLEAR to start over");
	}

	public void displayMenu(String whatToChoose, int numItems, String items[])
	{
		write(whatToChoose);
		for (int i = 0; i < numItems; i ++)
			write((i + 1) + ") " + items[i]); 
	}

	public void requestAmountEntry()
	{
		write( "Please enter amount.\n" +   "Press ENTER when finished or CLEAR to start over");
	}


	public void requestDepositEnvelope()
	{ write(
			"Please deposit an envelope in the slot");
	}

	public void reportCardUnreadable()
	{ 
		write(
			"Sorry, your card was inserted incorrectly or\n" + 
					"is unreadable.\n" +
			"Please try inserting your card again");
	}

	public void reportTransactionFailure(String explanation)
	{ 
		write(explanation);
		write("\n" + "Do you want to perform another transaction\n" + "(1 = Yes, 2 = No)?");
	}

	public void requestReEnterPIN()
	{ 
		write("Your PIN was entered incorrectly.\n" + "Please re-enter it");
	}

	public void reportCardRetained()
	{ 
		write("Your PIN was entered incorrectly.\n" +   "Your card has been retained - please contact the bank");
		try
		{ 
			Thread.sleep(10 * 1000); 
		}
		catch (InterruptedException e)
		{ 

		}
	}

	public void echoInput(String echo)
	{ 
		_line[GUILayout.DISPLAYABLE_LINES - 1].setText("          " + echo);
	}

	public void clearDisplay()
	{
		for (int i = 0; i < GUILayout.DISPLAYABLE_LINES; i ++)
			_line[i].setText("");
		_currentLine = 0;
	}

	// Private method and instance variables needed for GUI

	private void write(String s)
	// Write one or more lines; may contain multiple lines delimited by \n 
	{ 
		StringTokenizer t = new StringTokenizer(s, "\n", false);
		while (t.hasMoreTokens())
		{ 
			try
			{ 
				_line[_currentLine ++].setText(t.nextToken());
			}
			catch (Exception e)
			{

			}
		}
	}    
}
