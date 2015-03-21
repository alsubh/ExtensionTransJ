package com.pile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.configration.*;
import com.object.Goo;

public class GooPile implements IPile{

	private int count;
    Statement stmt;
	public static Connection sqlConn = 
			dbConnection.setupGooPileConnection("GooPile", "tedi", "tedi");
	
	//public static List<Goo> gooPile = new ArrayList<Goo>();
	
	public GooPile(){	
	}

	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		Goo goo = (Goo)obj;
        String SQL = "INSERT INTO GooPile(name, code, type, price)" +
        		"VALUES("+goo.getName()+","+ goo.getCode()+
        		","+goo.getType()+","+goo.getPrice()+");";  
        sqlStatement(SQL);  
		//gooPile.add(goo);
	}

	@Override
	public void remove(Object obj) {
		// TODO Auto-generated method stub
		Goo goo = (Goo)obj;
		String SQL = "DELETE FROM GooPile WHERE (name="+goo.getName()+
				" and code="+goo.getCode()+" and type="+goo.getType()+
				") or ID="+goo.getID()+";";
		sqlStatement(SQL);
		//int index= gooPile.indexOf(goo);
		//gooPile.remove(index);
	}

	public Goo retrieveGoo() {
		Goo gooItem = new Goo();
		String SQL = "SELECT ID,name,code, type, price FROM GooPile";
		
		try {
			stmt = sqlConn.createStatement(); 
			ResultSet rs = stmt.executeQuery(SQL);
	        // Iterate through the data in the result set and display it.  
			 while (rs.next())  
             { 
				gooItem.setID(rs.getInt("ID"));
				gooItem.setName(rs.getString("name"));
				gooItem.setCode(rs.getString("code"));
				gooItem.setType(rs.getString("type"));
				gooItem.setPrice(rs.getInt("price"));
				return gooItem;
             }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			gooItem=null;
		} 
		return gooItem;
	}
	
	public List<Goo> retrieveAllGoo() {
		List<Goo> gooItems = new ArrayList<Goo>();
		Goo gooItem = new Goo();
		String SQL = "SELECT ID,name,code, type, price FROM GooPile";
		try {
			stmt = sqlConn.createStatement(); 
			ResultSet rs = stmt.executeQuery(SQL);
	        // Iterate through the data in the result set and display it.  
			 while (rs.next())  
             { 
				gooItem.setID(rs.getInt("ID"));
				gooItem.setName(rs.getString("name"));
				gooItem.setCode(rs.getString("code"));
				gooItem.setType(rs.getString("type"));
				gooItem.setPrice(rs.getInt("price"));
				gooItems.add(gooItem);
             }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			gooItems=null;
		} 
		return gooItems;
	}
	
	@Override
	public int count() {
		// TODO Auto-generated method stub
		String SQL = "SELECT count(name) FROM GooPile";
		try {
			stmt = sqlConn.createStatement(); 
			ResultSet rs = stmt.executeQuery(SQL);
	        // Iterate through the data in the result set and display it.  
			count= rs.getInt("count");
			System.out.println(" Number of Goos in the GooPile="+rs.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//count = gooPile.size();
		return count;
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
