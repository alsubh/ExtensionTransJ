package com.transaction;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.W3CDomHandler;

import operator.Baker;
import operator.Builder;
import operator.Polisher;

import processedComponent.PolishedWidget;
import processedComponent.RawWidget;
import processedComponent.RoughWidget;

import com.object.Goo;
import com.object.Widget;
import com.pile.GooPile;

public class T1 {

	public List<Goo> goos = new ArrayList<Goo>();
	RawWidget rawWidget= new RawWidget();
	Builder builder = new Builder();
	
	RoughWidget roughWidget;
	Baker baker = new Baker();
	
	PolishedWidget polishedWidget;
	Polisher polisher = new Polisher();
	
	public T1(){
		
	}
	
	 //Get Goo from Goo Pile
	public List<Goo> getGooFromPile(int numberOfGoos)
	{
		goos= builder.getGooList(numberOfGoos);
		//delete that goo from the pile
		return goos;
	}
	
	 //Give Goo to a Builder and get back a Raw Widget
	public RawWidget getRawWidget(String name, String code, List<Goo> goo)
	{
		builder.setName(name);
		builder.setCode(code);
		rawWidget= (RawWidget) builder.create(goo);
		return rawWidget;
	}
	
	 //Give Raw Widget to a Baker and get a Rough Widget
	public RoughWidget getRoughWidget(String name, String code,RawWidget rawWidget)
	{
		roughWidget= (RoughWidget) baker.create(rawWidget);
		roughWidget.setName(name);
		roughWidget.setCode(code);
		return roughWidget;
	}
	 //Give Rough Widget to a Polisher and get a Polished Widget
	public PolishedWidget getPolishedWidget(String name, String code,RoughWidget roughWidget)
	{
		polishedWidget= (PolishedWidget) polisher.create(roughWidget);
		polishedWidget.setName(name);
		polishedWidget.setCode(code);
		return polishedWidget;
	}
	 //Put Polish Widget in a Widget Pile
	public void putInWidgetPile(PolishedWidget pWidget)
	{
		polisher.add((Widget)pWidget);
	}
}
