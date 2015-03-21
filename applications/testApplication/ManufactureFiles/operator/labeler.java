package operator;

import com.object.Gadget;
import com.pile.GadgetPile;

public class labeler {

	private String tag="";
	private static int serialNumber=100;
	
	public void tagging()
	{
		for(Gadget gadget: GadgetPile.gadgetPile)
		{
			if(gadget.getTag()!="")
			{
				gadget.setTag(generateSerial(gadget));
			}
		}
	}
	
	public String generateSerial(Gadget gad)
	{
		int numberOfCompnenet= gad.getNumberOfComponent();
		
		if(numberOfCompnenet ==1)
		{
			serialNumber= serialNumber +1;
			tag= "101"+ serialNumber;
			return tag;
		}
		else if(numberOfCompnenet ==2)
		{
			serialNumber= serialNumber +1;
			tag = "102"+ serialNumber;
			return tag;
		}
		else
		{
			serialNumber= serialNumber +1;
			tag="103" + serialNumber;
			return tag;
		}
	}
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
