package com.operator.businesslogic;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.operator.business.Production;
import com.pile.businesslogic.*;
import com.entity.businesslogic.*;
import com.processedComponent.utilities.*;

import javax.ejb.Stateless;


/**
 * Session Bean implementation class Builder
 */
@Stateless
public class Builder implements Production {

	private String name; 
	private String type;
	private String code;
	
	private List<Goo> goolist = new ArrayList<Goo>();
	private RawWidget rawWidget;
	private int numberOfGoosInPile;

	private GooPile gooPileObject= new GooPile();
	private WidgetPile widgetPileObject=new WidgetPile();
	
	private Statement stmt;
	
	public Builder(){
	}
	
	public List<Goo> getGooList(int numberOfGoos)
	{
		// Get number of current available Goos  
		numberOfGoosInPile= gooPileObject.count();
		
		int i =0;
		Goo goo =new Goo();
		while(i< numberOfGoos)
		{
			//if(GooPile.gooPile.size() !=0)
			if(numberOfGoosInPile !=0)
			{
				// get Goo from the Pile
				goo= retrieveGoo();
				goolist.add(goo);
				// eliminate the Goo from the Pile
				remove(goo);
			}
			i++;
		}
		return goolist;
	}
	
	private Goo retrieveGoo() {
		Goo gooItem = new Goo();
		gooItem=gooPileObject.retrieveGoo();
		return gooItem;
	}

	@Override
	public Object create(Object goolist) {
		rawWidget = new RawWidget(name, code, (List<Goo>)goolist);
		add(rawWidget);
		return rawWidget;
	}
	@Override
	public void add(Object obj) {
		// add new raw widget to the widget pile table in DB
		widgetPileObject.add((RawWidget)obj);
		//WidgetPile.widgetPile.add(rawWidget);
	}
	@Override
	public void remove(Object obj) {

		// remove the goo from the GooPile table in DB 
		gooPileObject.remove(obj);
	}
	
	private void sqlStatement(Object obj,String SQL) {
		try {
			if((obj.getClass()).toString()=="WidgetPile")
			{
				stmt = ((WidgetPile)obj).sqlConn.createStatement();
			}
			else if((obj.getClass()).toString()=="GooPile")
			{
				stmt = ((GooPile)obj).sqlConn.createStatement();
			}
			// execute insert SQL statement
			stmt.executeUpdate(SQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Goo> getGoolist() {
		return goolist;
	}

	public void setGoolist(List<Goo> goolist) {
		this.goolist = goolist;
	}

	public RawWidget getRawWidget() {
		return rawWidget;
	}

	public void setRawWidget(RawWidget rawWidget) {
		this.rawWidget = rawWidget;
	}
}

