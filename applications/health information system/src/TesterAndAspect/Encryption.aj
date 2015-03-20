package TesterAndAspect;

import PersonalHealthInformation.*;


public aspect Encryption extends GereralAbstractEncryption 
{
	
	public static String e_String="";
	

	pointcut Test() : execution(@Encrypted * PersonalHealthInformation.*.get*(..)) ;		
		
	Object around(): Test() 
	{
		
		Object result = proceed();
		e_String=encrypt(result.toString());		
		System.out.println(" Encrypted String : " + e_String);
		return result;		
	
	}
	
	
			
}
