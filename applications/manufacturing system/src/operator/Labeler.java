package operator;

import java.util.ArrayList;
import java.util.List;

import com.object.Gadget;
import com.pile.GadgetPile;

public class Labeler {

	private String tag="";
	private static String serialNumber="149000";
	private GadgetPile gadgetpileObject = new GadgetPile();
	List<Gadget> listOfGadget = new ArrayList<Gadget>();
	
	public Labeler(){
		
	}
	
	public void tagging(){
		listOfGadget= gadgetpileObject.retrieveSpecificGadgets(" ");
		for (Gadget gad: listOfGadget)
		{
			// Update Tag
			gadgetpileObject.updateTag(gad, generateSerial());
		}
	}
	
	public void tagging(Gadget gad){
			// Update Tag
			gadgetpileObject.updateTag(gad, getTag());
	}
	
	public String generateSerial()//(Gadget gad)
	{
		//serialNumber= serialNumber+1;
		return serialNumber;
	}
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
