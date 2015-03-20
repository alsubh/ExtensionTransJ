package ATMPhysical;

import java.awt.*;
public class GUILayout
{

	public static final int DISPLAYABLE_LINES =     9;
	public static final int PRINTABLE_LINES =       8;
	public static final int PRINTABLE_CHARS =      30;

	// The following are used only for doing the layout

	private static final int DISPLAY_ROW =          0;
	private static final int DISPLAY_COL =          0;
	private static final int DISPLAY_WIDTH =        3;
	private static final int DISPLAY_HEIGHT =       1;
	private static final int DISPLAY_FILL =         GridBagConstraints.BOTH;

	private static final int PRINTER_ROW =          0;
	private static final int PRINTER_COL =          DISPLAY_COL + DISPLAY_WIDTH;
	private static final int PRINTER_WIDTH =        1;
	private static final int PRINTER_HEIGHT =       1;
	private static final int PRINTER_FILL =         GridBagConstraints.NONE;

	private static final int ENVELOPE_ROW =         DISPLAY_ROW + DISPLAY_HEIGHT;
	private static final int ENVELOPE_COL =         0;
	private static final int ENVELOPE_WIDTH =       1;
	private static final int ENVELOPE_HEIGHT =      1;
	private static final int ENVELOPE_FILL =        GridBagConstraints.NONE;

	private static final int DISPENSER_ROW =        ENVELOPE_ROW;
	private static final int DISPENSER_COL =        ENVELOPE_COL + ENVELOPE_WIDTH;
	private static final int DISPENSER_WIDTH =      1;
	private static final int DISPENSER_HEIGHT =     1;
	private static final int DISPENSER_FILL =       GridBagConstraints.NONE;

	private static final int READER_ROW =           ENVELOPE_ROW;
	private static final int READER_COL =           DISPENSER_COL + DISPENSER_WIDTH;
	private static final int READER_WIDTH =         1;
	private static final int READER_HEIGHT =        1;
	private static final int READER_FILL =          GridBagConstraints.NONE;

	private static final int KEYBOARD_ROW =         ENVELOPE_ROW;
	private static final int KEYBOARD_COL =         READER_COL + READER_WIDTH;
	private static final int KEYBOARD_WIDTH =       1;
	private static final int KEYBOARD_HEIGHT =      1;
	private static final int KEYBOARD_FILL =        GridBagConstraints.NONE;

	private static final int OPERATOR_ROW =         ENVELOPE_ROW + ENVELOPE_HEIGHT;
	private static final int OPERATOR_COL =         0;
	private static final int OPERATOR_WIDTH =       GridBagConstraints.REMAINDER;
	private static final int OPERATOR_HEIGHT =      1;
	private static final int OPERATOR_FILL =        GridBagConstraints.BOTH;

	private static final int TOTAL_ROWS = 3;
	private static final int TOTAL_COLS = 3;

	public static void doLayout(Container container,
			Component cardReader,
			Component display,
			Component keyboard,
			Component cashDispenser,
			Component envelopeAcceptor, 
			Component receiptPrinter,
			Component operatorPanel)
	{    
		container.hide();

		GridBagLayout layout = new GridBagLayout();
		container.setLayout(layout);

		// Put cardReader in a Panel with GridLayout to ensure it gets space
		// even when invisible

		Panel cardReaderPanel = new Panel();
		cardReaderPanel.setLayout(new GridLayout(1,1));
		cardReaderPanel.add(cardReader);
		container.add(cardReaderPanel);
		layout.setConstraints(cardReaderPanel, 
				makeConstraints(READER_ROW, READER_COL,
						READER_WIDTH, READER_HEIGHT, 
						READER_FILL));
		cardReader.hide();

		container.add(display);
		layout.setConstraints(display, 
				makeConstraints(DISPLAY_ROW, DISPLAY_COL,
						DISPLAY_WIDTH, DISPLAY_HEIGHT,
						DISPLAY_FILL));

		container.add(keyboard);
		layout.setConstraints(keyboard,
				makeConstraints(KEYBOARD_ROW, KEYBOARD_COL,
						KEYBOARD_WIDTH, KEYBOARD_HEIGHT,
						KEYBOARD_FILL));

		container.add(cashDispenser);
		layout.setConstraints(cashDispenser,
				makeConstraints(DISPENSER_ROW, DISPENSER_COL,
						DISPENSER_WIDTH, DISPENSER_HEIGHT,
						DISPENSER_FILL));


		// Put envelopeAcceptor in a Panel with GridLayout to ensure it gets space
		// even when invisible

		Panel envelopeAcceptorPanel = new Panel();
		envelopeAcceptorPanel.setLayout(new GridLayout(1,1));
		envelopeAcceptorPanel.add(envelopeAcceptor);
		container.add(envelopeAcceptorPanel);
		layout.setConstraints(envelopeAcceptorPanel,
				makeConstraints(ENVELOPE_ROW, ENVELOPE_COL, 
						ENVELOPE_WIDTH, ENVELOPE_HEIGHT,
						ENVELOPE_FILL));
		envelopeAcceptor.hide();

		container.add(receiptPrinter);
		layout.setConstraints(receiptPrinter,
				makeConstraints(PRINTER_ROW, PRINTER_COL,
						PRINTER_WIDTH, PRINTER_HEIGHT,
						PRINTER_FILL));

		container.add(operatorPanel);
		layout.setConstraints(operatorPanel,
				makeConstraints(OPERATOR_ROW, OPERATOR_COL,
						OPERATOR_WIDTH, OPERATOR_HEIGHT,
						OPERATOR_FILL));                                

		// When we create a dialog box later, we may need to pass a frame
		// to its constructor. (Standards-conforming implementations do not
		// need this, but defective implementations (e.g. Unix) do.)  Our
		// container is either a Frame, or we should be able to get to one
		// from it.  We can also use the location of this frame to position
		// the dialog appropriately.

		Container c = container;
		while (c != null && ! (c instanceof Frame))
			c = c.getParent();
		if (c instanceof Frame)
			_containingFrame = (Frame) c;
		else
			_containingFrame = null;
	}

	// This method creates a GridBagConstraints object with specified constraints,
	// and others defaulted.

	private static GridBagConstraints makeConstraints(
			int row, int col, int width, int height, int fill)
	{ 
		GridBagConstraints g = new GridBagConstraints();
		g.gridy = row;
		g.gridx = col;
		g.gridheight = height;
		g.gridwidth = width;
		g.fill = fill;
		g.insets = new Insets(2,2,2,2);
		return g;
	}

	// On some (defective) implementations of Java, we need to pass a Frame to
	// the constructor for Dialog - the documentation says null is allowed here,
	// but Unix implementations throw a null pointer exception if one tries.
	// Fortunately, the container we are given when we do the layout is either
	// a Frame, or we should be able to get to one by following parents.  The
	// doLayout method tries to fill this in; it if can't, it sets it null.

	private static Frame _containingFrame;

	public static Frame getContainingFrame()
	{ return _containingFrame; }

}
