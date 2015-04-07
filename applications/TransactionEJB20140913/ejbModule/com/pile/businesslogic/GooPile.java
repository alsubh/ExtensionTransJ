package com.pile.businesslogic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.client.utilities.ClientUtility;
import com.configration.utilities.dbConnection;
import com.configration.utilities.dbType;
import com.entity.business.IPile;
import com.entity.businesslogic.Goo;

/**
 * Session Bean implementation class GooPile
 */
@Stateless
@Remote
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class GooPile implements com.entity.business.IPile {

	private int count;
    Statement stmt;
	public static Connection sqlConn=
			dbConnection.setupGooPileConnection("GooPile", "tedi", "tedi");
	
	//public static List<Goo> gooPile = new ArrayList<Goo>();
	
	public GooPile(){	
		System.out.println(sqlConn);
	}

	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		Goo goo = (Goo)obj;
        String SQL = "INSERT INTO Goo(name, code, type, price)" +
        		"VALUES('"+goo.getName()+"','"+ goo.getCode()+
        		"','"+goo.getType()+"',"+goo.getPrice()+");";
        String dType = dbType.SQLSERVER.toString();
        sqlConn = ClientUtility.setupGooPileXAConnection(dType,"GooPile", "tedi", "tedi", SQL);
        //sqlStatement(SQL);  
	}

	@Override
	public void remove(Object obj) {
		// TODO Auto-generated method stub
		Goo goo = (Goo)obj;
		String SQL = "DELETE FROM Goo WHERE (name='"+goo.getName()+
				"' and code='"+goo.getCode()+"' and type='"+goo.getType()+
				"') or ID="+goo.getID()+";";
		sqlStatement(SQL);
		//int index= gooPile.indexOf(goo);
		//gooPile.remove(index);
	}

	public Goo retrieveGoo() {
		Goo gooItem = new Goo();
		String SQL = "SELECT ID,name,code, type, price FROM Goo";
		
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
		String SQL = "SELECT ID,name,code, type, price FROM Goo";
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
		List<Goo> gooItems = new ArrayList<Goo>();
		gooItems= retrieveAllGoo();
		return gooItems.size();
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
