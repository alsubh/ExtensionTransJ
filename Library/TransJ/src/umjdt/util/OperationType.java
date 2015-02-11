package umjdt.util;

import java.io.Serializable;
import java.lang.reflect.Method;

public class OperationType implements Serializable {

	private static final long serialVersionUID = 1L;
	private String accessType = "";
	
	public OperationType()
	{
		
	}
	
	public OperationType(String _type)
	{
		this.accessType = _type;
	}
	
	public String getAccessType(String methodName)
	{
		boolean annotationNotFound = false;
		
		for (Method method : this.getClass().getMethods()) 
		{
			if((methodName.trim()).equalsIgnoreCase(method.getName()))
			{        	 
				if(method.isAnnotationPresent(ReadAccess.class))
					accessType = AccessType.READ;

				else if(method.isAnnotationPresent(WriteAccess.class))
		        	accessType = AccessType.WRITE;

				else if(method.isAnnotationPresent(UpdateAccess.class))
		        	accessType = AccessType.UPDATE;
		            
				else
					annotationNotFound = true;
				
				break;
			}	 
		}
	   
		// If there is no annotation, assume the worst case
		if(annotationNotFound)
			accessType = AccessType.WRITE;
		
		return accessType;
	}

	public void setAccessType(String _type) {
		this.accessType = _type;
	}
}

