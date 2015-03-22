package com.client.transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.*;

import com.entity.businesslogic.Gadget;
import com.entity.businesslogic.Widget;
import com.operator.businesslogic.Assembler;
import com.operator.businesslogic.Labeler;


public class T2 {

	private Widget widget=new Widget();
	private Gadget gadet= new Gadget();
	
	private Assembler assembler = new Assembler();
	
	private List<Widget> widgets= new ArrayList<Widget>(); 
	
	private Labeler labeler = new Labeler();	
	private ReadWriteLock lock = new ReentrantReadWriteLock();
		
	public T2(){
		
	}
	
	//Get Widget (W1) from Widget Pile 1
	//Get Widget (W2) from Widget Pile 2
	@SuppressWarnings("finally")
	public List<Widget> getWidget(int numberOfComponent){
		
		// lock the widget pile for getting widget
		//lock.readLock().lock();
		try{
			widgets = assembler.getWidgets(numberOfComponent);
		} finally {
			//lock.readLock().unlock();
			return widgets;
		}
	}
	//Give W1 and W2 to Assembler and get a Gadget, G
	public Gadget getGadget(String name, String code, List<Widget> wids)//, int numberofWidgets)
	{
		widgets=wids;
		
		assembler.setName(name);
		assembler.setCode(code);
		assembler.setNumberofWidget(widgets.size());//numberofWidgets);
		
		gadet= (Gadget) assembler.create(widgets);
		//Put Gadget G in a Gadget Pile
		return gadet;
	}

	//Have Labeler put a tag on G
	public void putTag(Gadget gad,String tag){
		labeler.setTag(tag);
		labeler.tagging(gad);
	}

}
