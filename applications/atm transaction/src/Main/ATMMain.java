package Main;

import java.awt.*;
import ATM.*;
import Bank.*;
import util.Money;

public class ATMMain implements Runnable
{
	private Frame _theFrame;
	private Bank _theBank;
	private ATM _theATM;
	private Thread _theThread;

	// Private constants

	private static final int ATM_NUMBER = 42;
	private static final String ATM_LOCATION = "GORDON COLLEGE";
	
	
	// This method is invoked when ATMMain.class is run as an application.  It
	// creates a new object of this class.  Versions with and without arguments are
	// provided; Macs pop up a dialog box if main() needs arguments, and Linux 
	// systems require main() to take arguments.  Either way, the newly created
	// object does the work

	public static void main()
	{
		new ATMMain(); 
	}

	public static void main(String argv[])
	{ 
		new ATMMain();
	}

	// ATMMain constructor.
	// Create the ATM and simulated bank, plus the GUI - furnishing a frame for its
	// container, plus a thread to run the simulation (which executes the run()
	// method of this class).  Start the thread and we're off!

	public ATMMain()
	{ 
		_theFrame = new Frame();
		_theFrame.setTitle("ATM number " + ATM_NUMBER + " at " + ATM_LOCATION);
		_theFrame.setResizable(false);

		_theBank = new Bank();
		_theATM = new ATM(ATM_NUMBER, ATM_LOCATION, _theBank, _theFrame);

		_theFrame.pack();
		_theFrame.show();

		_theThread = new Thread(this);
		_theThread.start();
	}

	// This method is run by the thread.  The program will terminate when the
	// ATM is turned off. 
	public void run()
	{
		Money initialCash = _theATM.startupOperation();
		_theATM.serviceCustomers(initialCash);

		System.exit(0); 
	}
}
