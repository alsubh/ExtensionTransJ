package utilities;

import java.io.FileInputStream;
import java.util.*;
import java.util.Properties;

public class Constants {

	static Properties props = new Properties();
	 public static long TimeToWait = 0;
	 
	 public static void loadProperties()
		{
			String path = "C:\\Users\\AnasAlsubh\\Box Sync\\Design and Implementation of TransJ\\library\\transj\\src\\utilities\\";
		       try {
		    	  //props.load(new FileInputStream(path+"constants.properties")); 
			} catch (Exception e) {
				e.printStackTrace();
			}			
				//TimeToWait = Long.parseLong(props.getProperty("TimeToWait"));	            
		}
}