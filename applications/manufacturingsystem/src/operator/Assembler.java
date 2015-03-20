package operator;

import java.util.ArrayList;
import java.util.List;

import com.object.Gadget;
import com.object.Widget;
import com.pile.GadgetPile;
import com.pile.WidgetPile;

public class Assembler implements Productor{
	
	private String name;
	private String code;
	private int numberofWidget;
	
	private List<Widget> widgetlist = new ArrayList<Widget>();
	private GadgetPile gadgetPileObject=new GadgetPile();
	private WidgetPile widgetPileObject=new WidgetPile();
	
	public Assembler(){

	}
	
	public List<Widget> getWidgets(int numberOfWidget){
		
		widgetlist= widgetPileObject.retrieveSpecificWidgets("Polished");
		List<Widget> polishedlist = new ArrayList<Widget>();
		
		for (int i=0; i<numberOfWidget; i++ ){
			polishedlist.add(widgetlist.get(i));
		}
		
		return polishedlist;
	}
		
	@Override
	public Object create(Object listOfWidgets) {
		if(((List<Widget>)listOfWidgets).size() >0)
		{
			Gadget gadget = new Gadget(name,code,(List<Widget>) listOfWidgets);//getWidgets());
			remove(listOfWidgets);
			add(gadget);
			return gadget;	
		}
		else{
			System.out.println("No Polished Widgets");
			return null;
		}
	}

	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		gadgetPileObject.add((Gadget)obj);
		
	}

	@Override
	public void remove(Object obj) {
		// TODO Auto-generated method stub
		widgetPileObject.remove(obj);
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

	public int getNumberofWidget() {
		return numberofWidget;
	}

	public void setNumberofWidget(int numberofWidget) {
		this.numberofWidget = numberofWidget;
	}
}
