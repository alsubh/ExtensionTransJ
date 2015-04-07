package ATMPhysical;

import java.awt.*;
import util.Money;

public class CashDispenser extends Panel
{
	// Instance variable
	private Money _currentCash;
	private Label _label;  

	public CashDispenser()
	{
		setLayout(new GridLayout(1,1));
		_label = new Label("$XXX", Label.CENTER);
		_label.setFont(new Font("Helvetica", Font.PLAIN, 24));
		_label.setForeground(new Color(0, 64, 0));
		add(_label);
		_label.hide();
		_currentCash = new Money(0);
	}

	public void setCash(Money initialCash)
	{ 
		_currentCash = initialCash; 
	}

	public void dispenseCash(Money amount)
	{ 
		_label.setText("$" + amount.dollars());
		for (int size = 3; size <= 24; size += 1)
		{ _label.setFont(new Font("Helvetica", Font.PLAIN, size));
		_label.show();
		try
		{ 
			Thread.sleep(250); }
		catch (InterruptedException e)
		{ }
		_label.hide();
		}
		_currentCash.subtract(amount);
	}

	public Money currentCash()
	{
		return _currentCash;
	}
}
