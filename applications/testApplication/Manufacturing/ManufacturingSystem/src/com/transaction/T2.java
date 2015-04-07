package com.transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.*;

import operator.Assembler;
import operator.Labeler;

import com.object.Gadget;
import com.object.Widget;
import com.pile.GadgetPile;

public class T2 {

	private Widget widget=new Widget();
	private List<Widget> widgets= new ArrayList<Widget>(); 
	private Assembler assembler = new Assembler();
	private Gadget gadet= new Gadget();
	private Labeler labeler = new Labeler();	
	private ReadWriteLock lock = new ReentrantReadWriteLock();
		
	public T2(){
		
	}
	
	//Get Widget (W1) from Widget Pile 1
	//Get Widget (W2) from Widget Pile 2
	@SuppressWarnings("finally")
	public Widget getWidget(){
		lock.readLock().lock();
		try{
			widget = assembler.getWidgets();
		} finally {
			lock.readLock().unlock();
			return widget;
		}
	}
	//Give W1 and W2 to Assembler and get a Gadget, G
	public Gadget getGadget(String name, String code, String style,Widget w1, Widget w2, int numberofWidgets)
	{
		widgets.add(w1);
		widgets.add(w2);
		
		assembler.setName(name);
		assembler.setCode(code);
		assembler.setStyle(style);
		assembler.setNumberofWidget(numberofWidgets);
		
		gadet= (Gadget) assembler.create(widgets);
		return gadet;
		
	}
	//Put Gadget G in a Gadget Pile
	public void putGadgetInPile(Gadget gad)
	{
		GadgetPile.gadgetPile.add(gad);
	}
	//Have Labeler put a tag on G
	public void putTag(String tag){
		labeler.setTag(tag);
		labeler.tagging();
	}

}
