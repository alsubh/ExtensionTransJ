package processedComponent;

import java.util.List;

import com.object.Goo;
import com.object.Widget;

public class RawWidget extends Widget {
	
	public RawWidget(){
		
	}
	
	public RawWidget(List<Goo> goolist)
	{
		super(goolist);
	}
	
	public RawWidget(String name, String code, List<Goo> gooList)
	{
		super(name, "Raw", "Raw"+code, gooList);	
	}
	
}
