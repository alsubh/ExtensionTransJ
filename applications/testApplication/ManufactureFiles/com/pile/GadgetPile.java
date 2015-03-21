package com.pile;

import java.util.ArrayList;
import java.util.List;

import com.object.Gadget;

public class GadgetPile implements IPile{

	private int count;
	public static List<Gadget> gadgetPile;
	
	public GadgetPile(){
		gadgetPile = new ArrayList<Gadget>();
	}

	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		Gadget gadget = (Gadget)obj;
		gadgetPile.add(gadget);
	}

	@Override
	public void remove(Object obj) {
		// TODO Auto-generated method stub
		Gadget gadget = (Gadget)obj;
		int index= gadgetPile.indexOf(gadget);
		gadgetPile.remove(index);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		count = gadgetPile.size();
		return count;
	}
	
}
