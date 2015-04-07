package com.object;

import java.util.ArrayList;
import java.util.List;

public class Widget implements Iitem{

	private int ID;
	private String name;
	private String style;
	private String code;
	private int numberOfComponent;
	private List<Goo> gooList =new ArrayList<Goo>();
	
	public Widget(){
		
	}
	
	public Widget(List<Goo> gooLst)
	{
		gooLst= gooList;
	}
	
	public Widget(String name, String style, String code, List<Goo> goolst)
	{	
		this.name= name;
		this.style=style;
		this.code=code;
		this.gooList= goolst;
	}
	
	public Widget(String name, String style, String code)
	{	
		this.name= name;
		this.style=style;
		this.code=code;
	}
	
	@Override
	public int calculateTotal() {
		// TODO Auto-generated method stub
		int sum=0;
		for(Goo goo: gooList)
		{
			sum= goo.getPrice();
		}
		return sum;
	}
	
	public int numberOfComponent()
	{
		numberOfComponent = gooList.size();
		return numberOfComponent;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getNumberOfComponent() {
		return numberOfComponent;
	}

	public void setNumberOfComponent(int numberOfComponent) {
		this.numberOfComponent = numberOfComponent;
	}
	public List<Goo> getGooList() {
		return gooList;
	}

	public void setGooList(List<Goo> gooList) {
		this.gooList = gooList;
	}
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}
