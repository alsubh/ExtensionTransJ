package ATMPhysical;

import java.awt.*;
public class QuestionDialog extends Dialog
{
	private TextField _answer;
	private Button _okay;
	private boolean _finished;


	public QuestionDialog(String question, Component caller)
	{ 
		super(GUILayout.getContainingFrame(), true);
		setLayout(new BorderLayout());
		add("North", new Label(question));
		_answer = new TextField("");
		add("Center", _answer);
		Panel buttonPanel = new Panel();
		_okay = new Button("OK");
		buttonPanel.add(_okay);
		add("South", buttonPanel);
		pack();

		// Center this box in the outermost container holding the caller.  If
		// GUILayout found a frame for us when it did the layout, use this;
		// otherwise follow parents from the caller until we get to one that
		// has a null parent.

		Component topLevel;
		if (GUILayout.getContainingFrame() != null)
			topLevel = GUILayout.getContainingFrame();
		else
		{ 
			topLevel = caller;
			while (topLevel.getParent() != null)
				topLevel = topLevel.getParent();
		}
		Point topLevelLocation = topLevel.location();
		Dimension topLevelSize = topLevel.size();
		Dimension dialogSize = size();
		int dialogX = topLevelLocation.x + 
				(topLevelSize.width - dialogSize.width) / 2;
		int dialogY = topLevelLocation.y + 
				(topLevelSize.height - dialogSize.height) / 2;
		if (dialogX < 0) dialogX = 0;
		if (dialogY < 0) dialogY = 0;
		move(dialogX, dialogY);    
	}

	public synchronized String answer()
	{
		show();
		toFront();
		_answer.requestFocus();
		_finished = false;
		do
		{ try
		{ wait(); }
		catch (InterruptedException e)
		{ }
		}
		while (! _finished);
		hide();
		dispose();
		return _answer.getText();
	}

	public synchronized boolean action(Event e, Object arg)
	{
		if (e.target == _answer || e.target == _okay )
		{
			_finished = true;
			notify();
			return true;
		}
		else
			return false;
	}

}