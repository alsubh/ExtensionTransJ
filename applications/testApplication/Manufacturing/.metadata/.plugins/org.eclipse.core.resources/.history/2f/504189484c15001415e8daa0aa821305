package operator;

import com.object.Gadget;
import com.pile.GadgetPile;

public class Labeler {

	private String tag="";
	private static int serialNumber=100;
	
	public Labeler(){
		
	}
	
	public void tagging()
	{
		for(Gadget gadget: GadgetPile.gadgetPile)
		{
			if(gadget.getTag()=="")
			{
				gadget.setTag(generateSerial(gadget)+getTag());
			}
		}
	}
	
	public int generateSerial(Gadget gad)
	{
		serialNumber= serialNumber+1;
		return serialNumber;
	}
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
