package TesterAndAspect;
import PersonalHealthInformation.*;

public aspect Decryption extends GereralAbstractEncryption
{
	
	pointcut Test(Object s) : execution(@Encrypted * PersonalHealthInformation.*.set*(..))
	&& args(s); 
	
	void around(Object s): Test(s)  
	{
		String d_String;		
		if(!Encryption.e_String.equals(""))
			d_String = encrypt(Encryption.e_String);		
		else
			d_String=s.toString();

		proceed(s);
		System.out.println(" Decrypted String : " + d_String);	
	}
}