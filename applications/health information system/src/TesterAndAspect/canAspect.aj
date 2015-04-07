package TesterAndAspect;

public abstract aspect canAspect 
{
	
	String message;
	boolean value;
	
	abstract pointcut checkMethods(String  field);
	
	public void TemplateMethod()
	{
		message( message, value);
		viewModAccess(message, value );
	}
	
	public abstract boolean message(String message, boolean value);
	public abstract boolean viewModAccess(String field, boolean value);
	  
	boolean around(String field): checkMethods(field)
	 {
		 boolean result=proceed(field);
		 viewModAccess(field,result); 
		  return result;
	 }
}
