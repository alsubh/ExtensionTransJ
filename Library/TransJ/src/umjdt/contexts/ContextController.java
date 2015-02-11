package umjdt.contexts;

import java.util.Vector;

public class ContextController {

	private static Vector<Context> activeContexts = new Vector<Context>();
	private static Vector<Context> inactiveContexts = new Vector<Context>();
	
	public static synchronized void addNewContext(Context newContext)
	{
		activeContexts.add(newContext);
	}
		
	public static synchronized Context createContext(String contextName, String contextPath)
	{
		Context resultingContext = null;
		
		System.out.println("\t\tCONTEXT CONTROLLER CLASS WILL NOW CREATE THE CONTEXT OBJECT NAMED: " + contextName);
		
		resultingContext = (Context) createNewContextObject(contextName, contextPath);
		
		System.out.println("\t\tCONTEXT CONTROLLER CLASS HAS CREATED THE CONTEXT OBJECT NAMED: " + contextName);
		
		resultingContext.setName(contextName);
		
		addNewContext(resultingContext);
		
		return resultingContext;
	}
	
	public static synchronized Context createOrRetrieveContext(String contextName, String contextPath)// refer to the transaction Model (Flat or Nested)
	{
		Context resultingContext = null;
		
		if(isContextInTheActiveCollection(contextName))
		{
			resultingContext = getActiveContextByName(contextName);
		}
		else
		{
			resultingContext = createContext(contextName, contextPath);
		}
		
		return resultingContext;
	}
	
	public static synchronized Context getContextByName(String searchedName)
	{
		Context activeContext = getActiveContextByName(searchedName);
		
		if(null != activeContext)
		{
			return activeContext;
		}
		else
		{
			return getInactiveContextByName(searchedName);
		}
	}
	
	public static synchronized Context getActiveContextByName(String searchedName)
	{
		for(Context context : activeContexts)
		{
			if(context.getName().equals(searchedName))
			{
				return context;
			}
		}
		
		return null;
	}
	
	public static synchronized Context getInactiveContextByName(String searchedName)
	{
		for(Context context : inactiveContexts)
		{
			if(context.getName().equals(searchedName))
			{
				return context;
			}
		}
		
		return null;
	}
	
	public static synchronized boolean isContextInTheActiveCollection(String searchedName)
	{
		for(Context context : activeContexts)
		{
			if(context.getName().equals(searchedName))
			{
				return true;
			}
		}
		
		return false;
	}
	
	private static synchronized Object createNewContextObject(String name, String path)
	{
		Object createdObject = null;
		
		try 
		{
		    Class classDefinition = Class.forName(path);
		    
		    createdObject = classDefinition.newInstance();
		} 
		catch (InstantiationException e) 
		{
		    System.out.println(e);
		} 
		catch (IllegalAccessException e) 
		{
		    System.out.println(e);
		}
		catch (ClassNotFoundException e) 
		{
		    System.out.println(e);
		}
		
		return createdObject;		   
	}
	
	public static synchronized void inactivateContext(Context oldContext)
	{
		activeContexts.remove(oldContext);
		
		inactiveContexts.add(oldContext);
	}
}
