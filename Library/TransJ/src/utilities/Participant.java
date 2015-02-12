package utilities;

import umjdt.Events.UnexpectedOutcomeException;
import umjdt.contexts.Context;
import umjdt.contexts.ContextController;

public class Participant {

	private Context currentContext;
	
	public void setCurrentContext(Context context)
	{
		String name = "";	
		if(null != context){name = context.getName();}
		else{
				name = "No Context";
			}
		currentContext = context;
	}
	
	public Context getCurrentContext()
	{	
		return currentContext;
	}
	
	public synchronized void createContext(String name, String contextPath)
	{
		//The participant is willing to create a context named
		Context context = ContextController.createContext(name,contextPath);
		//The participant is done creating the context named
		//The participant will become a part of the context named
		context.assignParticipant(this); //The participant has become a part of the context named	
	}
	
	public void leaveContext()
	{
		String name = getCurrentContext().getName();
		//The participant is willing to leave its context named 
		getCurrentContext().dismissParticipant(this);		
		//The participant has left its context named
	}

	// The exception handling is not yet integrated into AspectOPTIMA
	// Therefore, for now ContextParticipant is responsible for handling exception signals..
	public void receiveTerminationSignal(String message)
	{
		throw new UnexpectedOutcomeException(message);
	}
}