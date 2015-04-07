package util;

public class Status
{
	public static final int SUCCESS = 0;

	// Cash dispenser does not have enough cash for a withdrawl request
	public static final int TOO_LITTLE_CASH = 1;

	// Customer did not deposit envelope within time out period
	public static final int ENVELOPE_DEPOSIT_TIMED_OUT = 2;

	// Various reasons why bank might reject a transaction
	public static final int UNKNOWN_CARD = 3;
	
	// Card number not recognized
	public static final int INVALID_PIN = 4;
	
	// PIN not correct for card
	public static final int NO_SUCH_ACCOUNT = 5; 
	
	// Card holder does not have this type account
	public static final int CANT_WITHDRAW_FROM_ACCOUNT = 6;

	// Account doesn't allow ATM withdrawl
	public static final int INSUFFICIENT_AVAILABLE_BALANCE = 7; // Self-explanatory
	public static final int DAILY_WITHDRAWL_LIMIT_EXCEEDED = 8;  // Ditto
}
