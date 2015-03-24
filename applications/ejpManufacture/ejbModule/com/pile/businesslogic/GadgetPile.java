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
import javax.transaction.xa.XAResource;

import com.configration.utilities.dbConnection;
import com.entity.business.IPile;
import com.entity.businesslogic.Gadget;

/**
 * Session Bean implementation class GadgetPile
 */
@Stateless
@Remote(GadgetPile.class)
@TransactionManagement(TransactionManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Resource(name = "java:/MSSQLXADS/Gadget")
public class GadgetPile implements IPile {

	private int count;
	// public static List<Gadget> gadgetPile = new ArrayList<Gadget>();
	Statement stmt;
	public static Connection sqlConn = dbConnection.setupGadgetPileConnection(
			"GadgetPile", "tedi", "tedi");

	private XAResource resource;

	public GadgetPile() {
	}

	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		Gadget gadget = (Gadget) obj;
		String SQL = "INSERT INTO Gadget(name, code, numberOfWidget, tag)"
				+ "VALUES('" + gadget.getName() + "','" + gadget.getCode()
				+ "','" + gadget.getNumberOfComponent() + "','"
				+ gadget.getTag() + "');";
		sqlStatement(SQL);
		// gadgetPile.add(gadget);
	}

	@Override
	public void remove(Object obj) {
		// TODO Auto-generated method stub
		Gadget gadget = (Gadget) obj;
		String SQL = "DELETE FROM Gadget WHERE (name='" + gadget.getName()
				+ "' and code='" + gadget.getCode() + "' and numberOfWidget='"
				+ gadget.getNumberOfComponent() + "')or ID=" + gadget.getID()
				+ ";";
		sqlStatement(SQL);
		// int index= gadgetPile.indexOf(gadget);
		// gadgetPile.remove(index);
	}

	public void updateTag(Gadget gadget, String tag) {
		String SQL = "UPDATE Gadget SET tag='" + tag + "' WHERE ID="
				+ gadget.getID() + ";";
		sqlStatement(SQL);
	}

	public Gadget retrieveGadget() {
		Gadget gadgetItem = new Gadget();
		String SQL = "SELECT ID,name,code, numberOfWidget, tag FROM Gadget";

		try {
			stmt = sqlConn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				gadgetItem.setID(rs.getInt("ID"));
				gadgetItem.setName(rs.getString("name"));
				gadgetItem.setCode(rs.getString("code"));
				gadgetItem.setNumberOfComponent(rs.getInt("numberOfWidget"));
				gadgetItem.setTag(rs.getString("tag"));
				return gadgetItem;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			gadgetItem = null;
		}
		return gadgetItem;
	}

	public List<Gadget> retrieveAllGadget() {
		List<Gadget> gadgetItems = new ArrayList<Gadget>();
		Gadget gadgetItem = new Gadget();
		String SQL = "SELECT ID,name,code, numberOfWidget, tag FROM Gadget";
		try {
			stmt = sqlConn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				gadgetItem.setID(rs.getInt("ID"));
				gadgetItem.setName(rs.getString("name"));
				gadgetItem.setCode(rs.getString("code"));
				gadgetItem.setNumberOfComponent(rs.getInt("numberOfWidget"));
				gadgetItem.setTag(rs.getString("tag"));
				gadgetItems.add(gadgetItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			gadgetItems = null;
		}
		return gadgetItems;
	}

	public List<Gadget> retrieveSpecificGadgets(int numberOfWids, String label) {
		List<Gadget> gadgetItems = new ArrayList<Gadget>();
		Gadget gadgetItem = new Gadget();
		String SQL = "SELECT ID,name,code, numberOfWidget, tag "
				+ "FROM Gadget where numberOfWidget=" + numberOfWids + " or "
				+ " tag='" + label + "';";
		try {
			stmt = sqlConn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				gadgetItem.setID(rs.getInt("ID"));
				gadgetItem.setName(rs.getString("name"));
				gadgetItem.setCode(rs.getString("code"));
				gadgetItem.setNumberOfComponent(rs.getInt("NumberOfWidget"));
				gadgetItem.setTag(rs.getString("tag"));
				gadgetItems.add(gadgetItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			gadgetItems = null;
		}
		return gadgetItems;
	}

	public List<Gadget> retrieveSpecificGadgets(String label) {
		List<Gadget> gadgetItems = new ArrayList<Gadget>();
		Gadget gadgetItem = new Gadget();
		String SQL = "SELECT ID,name,code, numberOfWidget, tag "
				+ "FROM Gadget where tag='" + label + "';";
		try {
			stmt = sqlConn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				gadgetItem.setID(rs.getInt("ID"));
				gadgetItem.setName(rs.getString("name"));
				gadgetItem.setCode(rs.getString("code"));
				gadgetItem.setNumberOfComponent(rs.getInt("NumberOfWidget"));
				gadgetItem.setTag(rs.getString("tag"));
				gadgetItems.add(gadgetItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			gadgetItems = null;
		}
		return gadgetItems;
	}

	public List<Gadget> retrieveSpecificGadgets(int numberOfWids) {
		List<Gadget> gadgetItems = new ArrayList<Gadget>();
		Gadget gadgetItem = new Gadget();
		String SQL = "SELECT ID,name,code, numberOfWidget, tag "
				+ "FROM Gadget where numberOfWidget=" + numberOfWids + ";";
		try {
			stmt = sqlConn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				gadgetItem.setID(rs.getInt("ID"));
				gadgetItem.setName(rs.getString("name"));
				gadgetItem.setCode(rs.getString("code"));
				gadgetItem.setNumberOfComponent(rs.getInt("NumberOfWidget"));
				gadgetItem.setTag(rs.getString("tag"));
				gadgetItems.add(gadgetItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			gadgetItems = null;
		}
		return gadgetItems;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		String SQL = "SELECT count(name) FROM  Gadget";
		try {
			stmt = sqlConn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			// Iterate through the data in the result set and display it.
			count = rs.getInt("count");
			System.out.println(" Number of Gadget in the GadgetPile="
					+ rs.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// count = gooPile.size();
		return count;
	}

	public int count(int numOfWidgets, String label) {
		List<Gadget> list = new ArrayList<Gadget>();
		list = retrieveSpecificGadgets(numOfWidgets, label);
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
