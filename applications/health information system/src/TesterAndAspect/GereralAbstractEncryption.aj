package TesterAndAspect;

public abstract aspect GereralAbstractEncryption
{
			
	public String encrypt(String original)
	{		
		return new StringBuffer(original).reverse().toString();			
	}
	
	
	public String getE_String() {
		return e_String;
	}


	public void setE_String(String e_String) {
		this.e_String = e_String;
	}


	private String e_String="";
	

}
