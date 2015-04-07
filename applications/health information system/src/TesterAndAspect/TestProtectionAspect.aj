package TesterAndAspect;

import PersonalHealthInformation.*;


public aspect TestProtectionAspect 
{

	
	pointcut checkViewProtection() : execution(@Protected * PersonalHealthInformation.*.get*(..));	
	pointcut checkModifyProtection(String str) : execution(@Protected * PersonalHealthInformation.*.set*(..))
	&& args(str);
	
	
	Object around(): checkViewProtection() 
	{		
		
		System.out.println("*****************************check View Protection*****************************************");
				
		Session userSession=Session.getSession();
		String fieldName = thisJoinPointStaticPart.getSignature().getName().substring(3);
		System.out.println(fieldName.toLowerCase() + " is field name");
		if (userSession.canView(fieldName))
		{
			System.out.println(fieldName.toLowerCase() + " is not proctected");
			Object result= proceed();
			if(result == null)
			{
				System.out.println("result is null");
				return new String();
			}
			return result;
		}
		else
		{
			System.out.println(fieldName.toLowerCase() + " is proctected");
			return new String();
		}	
	}
	
	
	Object around(String str): checkModifyProtection(str) 
	{		
		System.out.println("*****************************check Modify Protection*****************************************");
		
		Session userSession=Session.getSession();
		String fieldName = thisJoinPointStaticPart.getSignature().getName().substring(3);
		System.out.println(fieldName.toLowerCase() + " is field name");
		if (userSession.canModify(fieldName))
		{
			System.out.println(fieldName.toLowerCase() + " is not proctected");
			Object result= proceed(str);
			if(result == null)
			{
				System.out.println("result is null");
				return new String();
			}
			return result;
		}
		else
		{
			System.out.println(fieldName.toLowerCase() + " is proctected");
			return new String();
		}
	}	
}