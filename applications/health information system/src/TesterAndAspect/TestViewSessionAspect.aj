package TesterAndAspect;


public aspect TestViewSessionAspect extends canAspect
{

	pointcut checkMethods(String  field): 
		execution(* PersonalHealthInformation.Session.canView(..)) 
		&& args(field);
	
	@Override
	public boolean message(String message, boolean value)
	{
		System.out.println(message);
		return value;
	}
	
	@Override
	public boolean viewModAccess(String filename, boolean value)
	{
		if(value)
			return message("User can view " + filename ,value);
		else
			return message("User can't view " + filename ,value);
		
	}
}
