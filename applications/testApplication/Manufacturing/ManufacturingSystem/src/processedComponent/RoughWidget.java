package processedComponent;

import java.util.List;

import com.object.Goo;
import com.object.Widget;

public class RoughWidget extends Widget {
	
//	public RoughWidget(){
//	}
	
	public RoughWidget(RawWidget rawWidget)
	{
		super(rawWidget.getGooList());
		rawWidget.setStyle("Rough");
	}
	
}
