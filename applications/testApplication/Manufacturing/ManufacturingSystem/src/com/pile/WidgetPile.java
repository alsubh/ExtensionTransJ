package com.pile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import processedComponent.RawWidget;

import com.configration.dbConnection;
import com.object.Goo;
import com.object.Widget;

public class WidgetPile implements IPile{

	private int count;
    Statement stmt;
	public static Connection sqlConn = 
			dbConnection.setupGooPileConnection("WidgetPile", "tedi", "tedi");
	
	//public static List<Widget> widgetPile= new ArrayList<Widget>();;
	
	public WidgetPile(){

	}

	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		Widget widget = (RawWidget)obj;
        String SQL = "INSERT INTO WidgetPile(name, code, style)" +
        		"VALUES("+widget.getName()+","+ widget.getCode()+
        		","+widget.getStyle()+");";  
        sqlStatement(SQL);  
		//widgetPile.add(widget);
	}

	@Override
	public void remove(Object obj) {
		// TODO Auto-generated method stub
		Widget widget = (Widget)obj;
		String SQL = "DELETE FROM WidgetPile WHERE (name="+widget.getName()+
				"and code="+widget.getCode()+") or style="+widget.getStyle()+
				" or ID="+widget.getID()+";";
		sqlStatement(SQL);
		//int index= widgetPile.indexOf(widget);
		//widgetPile.remove(index);
	}

	public Widget retrieveWidget() {
		Widget WidgetItem = new Widget();
		String SQL = "SELECT ID,name,code, style FROM WidgetPile";
		try {
			stmt = sqlConn.createStatement(); 
			ResultSet rs = stmt.executeQuery(SQL);
	        // Iterate through the data in the result set and display it.  
			 while (rs.next())  
             { 
				 WidgetItem.setID(rs.getInt("ID"));
				 WidgetItem.setName(rs.getString("name"));
				 WidgetItem.setCode(rs.getString("code"));
				 WidgetItem.setStyle(rs.getString("style"));
				return WidgetItem;
             }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			WidgetItem=null;
		} 
		return WidgetItem;
	}
	
	public List<Widget> retrieveAllWidgets() {
		List<Widget> widgetItems = new ArrayList<Widget>();
		Widget widgetItem = new Widget();
		String SQL = "SELECT ID,name,code, style FROM WidgetPile";
		try {
			stmt = sqlConn.createStatement(); 
			ResultSet rs = stmt.executeQuery(SQL);
	        // Iterate through the data in the result set and display it.  
			 while (rs.next())  
             { 
				widgetItem.setID(rs.getInt("ID"));
				widgetItem.setName(rs.getString("name"));
				widgetItem.setCode(rs.getString("code"));
				widgetItem.setStyle(rs.getString("type"));
				widgetItems.add(widgetItem);
             }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			widgetItems=null;
		} 
		return widgetItems;
	}
	
	public List<Widget> retrieveSpecificWidgets(String style) {
		List<Widget> widgetItems = new ArrayList<Widget>();
		Widget widgetItem = new Widget();
		String SQL = "SELECT ID,name,style FROM WidgetPile where style="+style+";";
		try {
			stmt = sqlConn.createStatement(); 
			ResultSet rs = stmt.executeQuery(SQL);
	        // Iterate through the data in the result set and display it.  
			 while (rs.next())  
             { 
				widgetItem.setID(rs.getInt("ID"));
				widgetItem.setName(rs.getString("name"));
				widgetItem.setCode(rs.getString("code"));
				widgetItem.setStyle(rs.getString("type"));
				widgetItems.add(widgetItem);
             }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			widgetItems=null;
		} 
		return widgetItems;
	}
	
	@Override
	public int count() {
		// TODO Auto-generated method stub
		String SQL = "SELECT count(name) FROM WidgetPile";
		try {
			stmt = sqlConn.createStatement(); 
			ResultSet rs = stmt.executeQuery(SQL);
	        // Iterate through the data in the result set and display it.  
			count= rs.getInt("count");
			System.out.println(" Number of Widgets in the WidgetPile="+rs.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//count = gooPile.size();
		return count;
	}

	public int count(String style) {
		List<Widget> list= new ArrayList<Widget>();
		list= retrieveSpecificWidgets(style);
		return list.size();
	}
	public void sqlStatement(String SQL) {
		try {
			stmt = sqlConn.createStatement();
			// execute insert SQL statement
			stmt.executeUpdate(SQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
