package TesterAndAspect;


public aspect TestSessionAspect
{

	pointcut checkViewMethods(String  field): execution(* PersonalHealthInformation.Session.canView(..)) && args(field);
	pointcut checkModifyMethods(String  field): execution(* PersonalHealthInformation.Session.canModify(..)) && args(field);
	
	boolean around(String field):checkViewMethods(field)
	{
		boolean result = proceed(field);
		if(result){
			System.out.println("User can view " + field);
			return true;
		}else
		{
			System.out.println("User can't view " + field );
			return false;
		}
	}

	boolean around(String field):checkModifyMethods(field)
	{
		boolean result = proceed(field);
		if(result){
			System.out.println("User can modify " + field);
			return true;
		}else
		{
			System.out.println("User can't modify " + field );
			return false;
		}
	}
}
