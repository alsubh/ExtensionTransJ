package umjdt.util;

import java.io.PrintWriter;

public class Status implements javax.transaction.Status {

	public static final int RUNNING = 0;
	public static final int PREPARING = 1;
	public static final int ABORTING = 2;
	public static final int ABORT_ONLY = 3;
	public static final int ABORTED = 4;
	public static final int PREPARED = 5;
	public static final int COMMITTING = 6;
	public static final int COMMITTED = 7;
	public static final int CREATED = 8;
	public static final int INVALID = 9;
	public static final int CLEANUP = 10;
	public static final int H_ROLLBACK = 11;
	public static final int H_COMMIT = 12;
	public static final int H_MIXED = 13;
	public static final int H_HAZARD = 14;
	public static final int DISABLED = 15;
	public static final int NO_ACTION = 16;
	public static final int LOCKED = 17;
	public static final int RELEASED = 18;

	/**
	 * @return <code>String</code> representation of the status.
	 */

	public static String stringForm(int res) {
		switch (res) {
		case RUNNING:
			return "Status.RUNNING";
		case PREPARING:
			return "Status.PREPARING";
		case ABORTING:
			return "Status.ABORTING";
		case ABORT_ONLY:
			return "Status.ABORT_ONLY";
		case ABORTED:
			return "Status.ABORTED";
		case PREPARED:
			return "Status.PREPARED";
		case COMMITTING:
			return "Status.COMMITTING";
		case COMMITTED:
			return "Status.COMMITTED";
		case CREATED:
			return "Status.CREATED";
		case INVALID:
			return "Status.INVALID";
		case CLEANUP:
			return "Status.CLEANUP";
		case H_ROLLBACK:
			return "Status.H_ROLLBACK";
		case H_COMMIT:
			return "Status.H_COMMIT";
		case H_MIXED:
			return "Status.H_MIXED";
		case H_HAZARD:
			return "Status.H_HAZARD";
		case DISABLED:
			return "Status.DISABLED";
		case NO_ACTION:
			return "Status.NO_ACTION";
		case BLOCKED:
			return "Status.BLOCKED";
		case RELEASED:
			return "Status.RELEASED";
		default:
			return "Unknown";
		}
	}

	/**
	 * Print the status on the specified <code>PrintWriter</code>.
	 */

	public static void print(PrintWriter strm, int res) {
		strm.print(Status.stringForm(res));
	}
}
