package umjdt.concepts;

import com.arjuna.ats.arjuna.common.Uid;
import com.arjuna.ats.arjuna.coordinator.BasicAction;
import com.arjuna.ats.arjuna.coordinator.TwoPhaseCoordinator;

public class TwoPhaseCommitProtocol extends TwoPhaseCoordinator{

	public TwoPhaseCommitProtocol() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TwoPhaseCommitProtocol(int at) {
		super(at);
		// TODO Auto-generated constructor stub
	}

	public TwoPhaseCommitProtocol(Uid u, int at) {
		super(u, at);
		// TODO Auto-generated constructor stub
	}

	public TwoPhaseCommitProtocol(Uid id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
}
