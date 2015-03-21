package operator;

import java.util.ArrayList;
import java.util.List;

import processedComponent.RawWidget;
import processedComponent.RoughWidget;

import com.object.Goo;
import com.object.Widget;
import com.pile.GooPile;
import com.pile.WidgetPile;

public class Baker implements Productor{

	int index=0;
	private RawWidget rawWidget= new RawWidget();
	
	public RawWidget getRawWidget()
	{
		Widget wid= new RawWidget();
		//select the Widget from the WidgetPile
			// conditions: style is "Raw"  and the code start with "Raw"
		//remove the RawWidget from the Pile.
		for(Widget widget: WidgetPile.widgetPile)
		{
			if(widget.getStyle()=="Raw")
			{
				index= WidgetPile.widgetPile.indexOf(widget);
				wid= WidgetPile.widgetPile.get(index);
			}
		}
		return (RawWidget) wid; 
	}
	
	@Override
	public Object create() {
		// TODO Auto-generated method stub
		RoughWidget roughWidget= new RoughWidget(getRawWidget());
		roughWidget.setStyle("Rough");
		return roughWidget;
	}

	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		
		//update
		remove((Widget)obj);
		WidgetPile.widgetPile.add((Widget)obj);
	}

	@Override
	public void remove(Object obj) {
		// TODO Auto-generated method stub
		
		//WidgetPile.widgetPile.remove(index);
		WidgetPile.widgetPile.remove(obj);
	}	
}
