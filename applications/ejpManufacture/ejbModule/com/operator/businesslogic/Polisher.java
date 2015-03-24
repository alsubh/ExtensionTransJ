package com.operator.businesslogic;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.entity.businesslogic.Widget;
import com.operator.business.Production;
import com.pile.businesslogic.WidgetPile;
import com.processedComponent.utilities.PolishedWidget;
import com.processedComponent.utilities.RoughWidget;

/**
 * Session Bean implementation class Polisher
 */
@Stateless
@Local(Production.class)
public class Polisher implements Production {

	private String name;
	private String code;
	private String style;

	private final WidgetPile widgetPileObject = new WidgetPile();

	private javax.transaction.TransactionManager manager = com.arjuna.ats.jta.TransactionManager
			.transactionManager();

	public RoughWidget getRoughWidget() {
		Widget wid = new Widget();
		// select the Widget from the WidgetPile
		wid = (Widget) widgetPileObject.retrieveSpecificWidgets("Rough");
		return (RoughWidget) wid;
	}

	@Override
	public Object create(Object roughWid) {
		// TODO Auto-generated method stub
		PolishedWidget polishedWidget = new PolishedWidget(name, code,
				(RoughWidget) roughWid);// getRoughWidget());
		polishedWidget.setStyle("Polished");
		// update
		remove(roughWid);
		add(polishedWidget);
		return polishedWidget;
	}

	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		widgetPileObject.add(obj);
	}

	@Override
	public void remove(Object obj) {
		// TODO Auto-generated method stub

		// WidgetPile.widgetPile.remove(index);
		widgetPileObject.remove(obj);
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

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public javax.transaction.TransactionManager getManager() {
		return manager;
	}

	public void setManager(javax.transaction.TransactionManager manager) {
		this.manager = manager;
	}
}
