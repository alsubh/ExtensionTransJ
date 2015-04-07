package Main;

import java.applet.Applet;
import java.awt.*;
import ATM.*;
import Bank.*;
import util.Money;

public class ATMApplet extends Applet implements Runnable
{
	private Bank _theBank;
	private ATM _theATM;
	private Thread _theThread;

	// Private constants

	private static final int ATM_NUMBER = 42;
	private static final String ATM_LOCATION = "GORDON COLLEGE";   


	// Applet initialization.
	// Create the ATM and simulated bank, plus the GUI - using this as its
	// container, plus a thread to run the simulation (which executes the run()
	// method of this class.)

	public void init()
	{ 
		_theBank = new Bank();
		_theATM = new ATM(ATM_NUMBER, ATM_LOCATION, _theBank, this);

		// If we are running in a frame we can get to, then set its title bar
		// to our title

		Component c = this;
		while (c.getParent() != null) c = c.getParent();
		if (c instanceof Frame)
		{ 
			((Frame) c).setTitle("ATM number " + ATM_NUMBER + " at " + ATM_LOCATION);
			((Frame) c).setResizable(false);
		}

		_theThread = new Thread(this);
		_theThread.start();
	}

	// stop() and start() are called as the applet is scrolled on and off the
	// screen.  Simply suspend and resume the thread.

	public void start()
	{ 
		_theThread.resume();
	}

	public void stop()
	{
		_theThread.suspend();
	}


	// This method is run by the thread.  Since there is no provision for an
	// applet to terminate itself, we let the operator turn the machine on and
	// off as often as desired.

	public void run()
	{
		while (true)
		{
			Money initialCash = _theATM.startupOperation();
			_theATM.serviceCustomers(initialCash);
		}
	}   
}
