package baseaspects;

import umjdt.contexts.Context;
import umjdt.contexts.ContextController;
import utilities.*;
import java.io.Serializable;
import umjdt.util.LockType;
import umjdt.util.Semaphore;


public aspect ContextParticipantAspect 
{	
	declare parents: Participant implements Serializable;

	
	
}
