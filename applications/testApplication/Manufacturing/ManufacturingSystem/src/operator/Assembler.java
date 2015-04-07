package operator;

import java.util.ArrayList;
import java.util.List;

import com.object.Gadget;
import com.object.Widget;
import com.pile.GadgetPile;
import com.pile.WidgetPile;

public class Assembler implements Productor{

	private Widget widget = new Widget();
	private Gadget gadget = new Gadget();
	private List<Widget> widgetlist = new ArrayList<Widget>();

	private String name;
	private String code;
	private String style;
	private int numberofWidget;
	
	public Assembler(){

	}
	
	public List<Widget> getWidgets(int numberOfWidget){
		int i=0;
		
		for(Widget widget: WidgetPile.widgetPile)
		{
			if(widget.getStyle()=="Polished")
			{
				while(i<numberOfWidget)
				{
					i++;
					widgetlist.add(widget);
					remove(widget);
				}
			}
		}
		return widgetlist;
	}
	
	public Widget getWidgets(){
		int i=0;
		
		for(Widget widget: WidgetPile.widgetPile)
		{
			if(widget.getStyle()=="Polished")
			{
				return widget;
			}
		}
		return null;
	}
	
	@Override
	public Object create(Object widgets) {
		// TODO Auto-generated method stub
		
		Gadget gadget = new Gadget(name,code,style,(List<Widget>) widgets);//getWidgets());
		//add(gadget);
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public int getNumberofWidget() {
		return numberofWidget;
	}

	public void setNumberofWidget(int numberofWidget) {
		this.numberofWidget = numberofWidget;
	}
}
