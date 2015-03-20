package TesterAndAspect;

import PersonalHealthInformation.*;

public aspect DesignConstrantAspect 
{
	
	declare warning :
		execution(@PII * PersonalHealthInformation.*.get*(..)):
			" Caution : Accessing PII ";
		
		
		declare warning :
			execution(@Protected * PersonalHealthInformation.*.get*(..)) || execution(@Protected * PersonalHealthInformation.*.set*(..)):
				" Caution : Protected Field ";
			
			
			declare warning :
				( get(!private * PersonalHealthInformation.*.*)
				|| set(!private * PersonalHealthInformation.*.*))
				:" Caution : Field access modifier should be private ";
				
				
//				get(protected * PersonalHealthInformation.*.*)
//				|| set(protected * PersonalHealthInformation.*.*)
//				|| get(public * PersonalHealthInformation.*.*)
//				|| set(public * PersonalHealthInformation.*.*)
				
}
