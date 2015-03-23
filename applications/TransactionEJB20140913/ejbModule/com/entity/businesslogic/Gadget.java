package com.entity.businesslogic;

import java.util.ArrayList;
import java.util.List;

import com.entity.business.Item;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Gadget
 */
@Stateless
public class Gadget implements Item {

	private int ID;
	private String name;
	private String code;
	private int numberOfComponent;
	private String tag="";
	private List<Widget>widgetList =new ArrayList<Widget>();
	
	public Gadget(){
		
	}
	
	public Gadget(List<Widget> widgets)
	{
		widgetList= widgets;
	}
	
	public Gadget(String name, String code, List<Widget> widgetlst)
	{	
		this.name= name;
		this.code=code;
		this.widgetList= widgetlst;
		this.numberOfComponent= widgetlst.size();
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
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}
