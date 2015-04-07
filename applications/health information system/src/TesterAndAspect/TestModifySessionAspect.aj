package TesterAndAspect;

public aspect TestModifySessionAspect extends canAspect 
{
		

	pointcut checkMethods(String  field): 
		execution(* PersonalHealthInformation.Session.canModify(..)) 
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
			return message("User can modify " + filename ,value);
		else
			return message("User can't modify " + filename ,value);
		
	}
}
