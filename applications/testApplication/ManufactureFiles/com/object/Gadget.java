package com.object;

import java.util.ArrayList;
import java.util.List;

public class Gadget implements Iitem{

	private String name;
	private String style;
	private String code;
	private int numberOfComponent;
	private String tag="";
	private List<Widget> widgetList;	
	
	
	public Gadget(){
		
	}
	
	public Gadget(List<Widget> widgets)
	{
		widgetList =new ArrayList<Widget>();
		widgetList= widgets;
	}
	
	public Gadget(String name, String style, String code, List<Widget> widgetlst)
	{
		widgetList = new ArrayList<Widget>();
		
		this.name= name;
		this.style=style;
		this.code=code;
		this.widgetList= widgetlst;
		this.tag="";
	}
	
	@Override
	public int calculateTotal() {
		// TODO Auto-generated method stub
		int sum=0;
		for(Widget widget: widgetList)
		{
			sum= widget.calculateTotal();
		}
		return sum;
	}
	
	public int numberOfComponent()
	{
		numberOfComponent = widgetList.size();
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
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<Widget> getWidgetList() {
		return widgetList;
	}

	public void setWidgetList(List<Widget> widgetList) {
		this.widgetList = widgetList;
	}
}
