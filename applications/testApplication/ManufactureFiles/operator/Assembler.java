package operator;

import java.util.ArrayList;
import java.util.List;

import com.object.Gadget;
import com.object.Widget;
import com.pile.GadgetPile;
import com.pile.WidgetPile;

public class Assembler implements Productor{

	private Widget widget = new Widget();
	private int numberofWidget=0;
	Gadget gadget = null;
	private List<Widget> widgetlist = new ArrayList<Widget>();
	
	public Assembler(int numberOfWidget){
		gadget = new Gadget();

		gadget.setNumberOfComponent(numberOfWidget);
		this.numberofWidget= numberOfWidget;
	}
	
	public List<Widget> getWidgets(){
		
		int i=0;
		
		for(Widget widget: WidgetPile.widgetPile)
		{
			if(widget.getStyle()=="Polished")
			{
				while(i<numberofWidget)
				{
					i++;
					widgetlist.add(widget);
					remove(widget);
				}
			}
		}
		return widgetlist;
	}
	
	@Override
	public Object create() {
		// TODO Auto-generated method stub
		Gadget gadget = new Gadget(getWidgets());
		add(gadget);
		return gadget;
	}

	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		GadgetPile.gadgetPile.add((Gadget)obj);
		
	}

	@Override
	public void remove(Object obj) {
		// TODO Auto-generated method stub
		WidgetPile.widgetPile.remove(obj);
	}
}
