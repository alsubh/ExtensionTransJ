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
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import com.configration.utilities.dbConnection;
import com.entity.business.IPile;
import com.entity.businesslogic.Widget;
import com.processedComponent.utilities.PolishedWidget;
import com.processedComponent.utilities.RawWidget;
import com.processedComponent.utilities.RoughWidget;

/**
 * Session Bean implementation class WidgetPile
 */
@Stateless
@Remote(WidgetPile.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Resource(name = "java:/MSSQLXADS/Widget")
public class WidgetPile implements IPile {

	private int count;
	Statement stmt;

	public static Connection sqlConn = dbConnection.setupWidgetPileConnection(
			"WidgetPile", "tedi", "tedi");

	// public static List<Widget> widgetPile= new ArrayList<Widget>();

	public static XAResource xaResource = null;
	public Xid xid = null;
	public int flag = XAResource.TMNOFLAGS;

	public WidgetPile() {

	}

	@Override
	public void add(Object obj) {
		Widget widget = null;
		try {
			if (obj.getClass() == RawWidget.class) {
				widget = (RawWidget) obj;
			} else if (obj.getClass() == RoughWidget.class) {
				widget = (RoughWidget) obj;
			} else if (obj.getClass() == PolishedWidget.class) {
				widget = (PolishedWidget) obj;
			}
			String SQL = "INSERT INTO Widget(name, code, style)" + "VALUES('"
					+ widget.getName() + "','" + widget.getCode() + "','"
					+ widget.getStyle() + "');";
			sqlStatement(SQL);
			// widgetPile.add(widget);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(Object obj) {
		// TODO Auto-generated method stub
		Widget widget = null;
		try {

			if (obj.getClass() == RawWidget.class) {
				widget = (RawWidget) obj;
				sqlDeleteStatement(widget);
			} else if (obj.getClass() == RoughWidget.class) {
				widget = (RoughWidget) obj;
				sqlDeleteStatement(widget);
			} else if (obj.getClass() == PolishedWidget.class) {
				widget = (PolishedWidget) obj;
				sqlDeleteStatement(widget);
			} else if (obj.getClass() == ArrayList.class) {
				List<Widget> listOfWidgets = (List<Widget>) obj;
				int length = listOfWidgets.size();
				for (int i = 0; i < length - 1; i++) {
					sqlDeleteStatement(listOfWidgets.get(i));
					listOfWidgets.remove(listOfWidgets.get(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Widget retrieveFirstWidget() {
		Widget WidgetItem = new Widget();
		String SQL = "SELECT ID,name,code, style FROM Widget";
		try {
			stmt = sqlConn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				WidgetItem.setID(rs.getInt("ID"));
				WidgetItem.setName(rs.getString("name"));
				WidgetItem.setCode(rs.getString("code"));
				WidgetItem.setStyle(rs.getString("style"));
				return WidgetItem;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			WidgetItem = null;
		}
		return WidgetItem;
	}

	public List<Widget> retrieveAllWidgets() {
		List<Widget> widgetItems = new ArrayList<Widget>();
		Widget widgetItem = new Widget();
		String SQL = "SELECT ID,name,code, style FROM Widget";
		try {
			stmt = sqlConn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				widgetItem.setID(rs.getInt("ID"));
				widgetItem.setName(rs.getString("name"));
				widgetItem.setCode(rs.getString("code"));
				widgetItem.setStyle(rs.getString("type"));
				widgetItems.add(widgetItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			widgetItems = null;
		}
		return widgetItems;
	}

	public List<Widget> retrieveSpecificWidgets(String style) {
		List<Widget> widgetItems = new ArrayList<Widget>();
		Widget widgetItem = new Widget();

		String SQL = "SELECT ID,name,code, style FROM Widget where style='"
				+ style + "';";
		try {
			stmt = sqlConn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				widgetItem.setID(rs.getInt("ID"));
				widgetItem.setName(rs.getString("name"));
				widgetItem.setCode(rs.getString("code"));
				widgetItem.setStyle(rs.getString("style"));
				widgetItems.add(widgetItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			widgetItems = null;
		}
		return widgetItems;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		String SQL = "SELECT count(name) FROM Widget";
		try {
			stmt = sqlConn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			// Iterate through the data in the result set and display it.
			count = rs.getInt("count");
			System.out.println(" Number of Widgets in the WidgetPile="
					+ rs.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// count = gooPile.size();
		return count;
	}

	public int count(String style) {
		List<Widget> list = new ArrayList<Widget>();
		list = retrieveSpecificWidgets(style);
		return list.size();
	}

	public void sqlStatement(String SQL) throws XAException {
		try {

			stmt = sqlConn.createStatement();
			// execute insert SQL statement
			xaResource.start(xid, flag);
			stmt.executeUpdate(SQL);
			xaResource.end(xid, flag);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			xaResource.rollback(xid);
		}
	}

	private void sqlDeleteStatement(Widget widget) throws XAException {
		String SQL = "DELETE FROM Widget WHERE (name='" + widget.getName()
				+ "' and code='" + widget.getCode() + "' and style='"
				+ widget.getStyle() + "') or ID=" + widget.getID() + ";";
		sqlStatement(SQL);
	}
}
