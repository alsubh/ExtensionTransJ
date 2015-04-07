package com.pile;

import java.util.ArrayList;
import java.util.List;

import com.object.Goo;

public class GooPile implements IPile{

	private int count;
	public static List<Goo> gooPile;
	
	public GooPile(){
		gooPile = new ArrayList<Goo>();
	}

	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		Goo goo = (Goo)obj;
		gooPile.add(goo);
	}

	@Override
	public void remove(Object obj) {
		// TODO Auto-generated method stub
		Goo goo = (Goo)obj;
		int index= gooPile.indexOf(goo);
		gooPile.remove(index);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		count = gooPile.size();
		return count;
	}

}
