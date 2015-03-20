package ATMPhysical;

import java.awt.*;
import java.util.Date;
import util.Money;

public class ReceiptPrinter extends TextArea
{
	public ReceiptPrinter()
	{        
		super(GUILayout.PRINTABLE_LINES, GUILayout.PRINTABLE_CHARS);
		setBackground(Color.white);
		setForeground(Color.black);
		setFont(new Font("Courier", Font.PLAIN, 12));
		setEditable(false);
	}

	public void printReceipt(int theATMnumber, String theATMlocation, int cardNumber,int serialNumber,String description, Money amount,	Money balance,Money availableBalance)
	{ 
		setText("");

		// Set up the receipt

		String lines[] = new String[7];
		int i = 0;
		lines[i ++] = new Date().toString() + "\n";
		lines[i ++] = theATMnumber + " " + theATMlocation + "\n";
		lines[i ++] = "CARD " + cardNumber + " TRANS " + serialNumber + "\n";
		lines[i ++] = description + "\n";
		if (amount.equals(new Money(0)))
			lines[i ++] = "\n";
		else
			lines[i ++] = "AMOUNT:    " + amount.dollars() + "." +
					((amount.cents() >= 10) ? "" + amount.cents() 
							: "0" + amount.cents()) + "\n";
		lines[i ++] = "CURR BAL:  " + balance.dollars() + "." +
				((balance.cents() >= 10) ? "" + balance.cents() 
						: "0" + balance.cents()) + "\n";
		lines[i ++] = "AVAILABLE: " + availableBalance.dollars() + "." +
				((availableBalance.cents() >= 10) ? "" + availableBalance.cents() 
						: "0" + availableBalance.cents()) + "\n";

		// Animate it

		for (i = 0; i < 7; i ++)
		{ 
			appendText(lines[i]);
			try
			{ 
				Thread.sleep(1 * 1000); }
			catch (InterruptedException e)
			{ }
		}    
	}
}
