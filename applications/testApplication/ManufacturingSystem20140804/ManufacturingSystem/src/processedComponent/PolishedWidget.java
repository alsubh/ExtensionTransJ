package processedComponent;

import java.util.List;

import com.object.Goo;
import com.object.Widget;

public class PolishedWidget extends Widget {
	
	public PolishedWidget(){
		
	}
	
	public PolishedWidget(RoughWidget roughWidget)
	{
		super(roughWidget.getGooList());
	}
	
	public PolishedWidget(String name, String code, RoughWidget roughWidget)
	{
		super(name, "Polished", "polished"+code);	
	}
}
