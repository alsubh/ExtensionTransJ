package com.pile;

import java.util.ArrayList;
import java.util.List;

import com.object.Widget;

public class WidgetPile implements IPile{

	private int count;
	public static List<Widget> widgetPile;
	
	public WidgetPile(){
		widgetPile = new ArrayList<Widget>();
	}

	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		Widget widget = (Widget)obj;
		widgetPile.add(widget);
	}

	@Override
	public void remove(Object obj) {
		// TODO Auto-generated method stub
		Widget widget = (Widget)obj;
		int index= widgetPile.indexOf(widget);
		widgetPile.remove(index);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		count = widgetPile.size();
		return count;
	}
	
}
