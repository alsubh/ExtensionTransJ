package transaction;

import com.arjuna.ats.arjuna.common.Uid;
import com.arjuna.ats.arjuna.coordinator.*;

public class TwoPhaseProtocol extends TwoPhaseCoordinator
{

	public TwoPhaseProtocol()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public TwoPhaseProtocol(int at) 
	{
		super(at);
		// TODO Auto-generated constructor stub
	}

	public TwoPhaseProtocol(Uid u, int at) 
	{
		super(u, at);
		// TODO Auto-generated constructor stub
	}

	public TwoPhaseProtocol(Uid id) 
	{
		super(id);
		// TODO Auto-generated constructor stub
	}

}
