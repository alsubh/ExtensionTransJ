package ATMPhysical;

import java.awt.*;
import util.Money;

public class OperatorPanel extends Panel
{
	// These fields are needed by the GUI

	private Label _message;
	private Checkbox _offButton;
	private Checkbox _onButton;


	public OperatorPanel()
	{ 
		setLayout(new BorderLayout());
		setBackground(new Color(128,128,255));
		add("West", new Label("Operator Panel"));
		_message = new Label("Click ON button to turn ATM on");
		add("Center", _message);
		CheckboxGroup group = new CheckboxGroup();
		_offButton = new Checkbox("OFF", group, true);
		_onButton = new Checkbox("ON", group, false);
		Panel buttonPanel = new Panel();
		buttonPanel.add(_offButton);
		buttonPanel.add(_onButton);
		add("East", buttonPanel);
	}

	public synchronized boolean switchOn()
	{ 
		// This will blink the "Click ON button ..." message when
		// the ATM is off
		boolean isOn = _onButton.getState();
		if (! isOn)
			if (_message.isShowing())
				_message.hide();
			else
				_message.show();
		else
			_message.hide();
		return isOn;
	}

	public Money getInitialCash()
	{ 
		int numberBills = -1;
		while (numberBills < 0)
		{ QuestionDialog cashDialog = new 
		QuestionDialog("How many $20 bills are in the cash dispenser?", this);
		String answer = cashDialog.answer();
		if (answer != null)
			try
		{ numberBills = Integer.parseInt(answer); }
		catch (NumberFormatException e)
		{ }
		}
		return new Money(20 * numberBills);    
	}
}
