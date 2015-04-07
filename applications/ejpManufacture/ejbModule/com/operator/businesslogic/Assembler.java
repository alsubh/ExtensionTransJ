package com.operator.businesslogic;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import com.entity.businesslogic.Gadget;
import com.entity.businesslogic.Widget;
import com.operator.business.Production;
import com.pile.businesslogic.GadgetPile;
import com.pile.businesslogic.WidgetPile;

/**
 * Session Bean implementation class Assembler
 */
@Stateless
@Local(Production.class)
public class Assembler implements Production {

	private String name;
	private String code;
	private int numberofWidget;

	private List<Widget> widgetlist = new ArrayList<Widget>();
	private final GadgetPile gadgetPileObject = new GadgetPile();
	private final WidgetPile widgetPileObject = new WidgetPile();

	private javax.transaction.TransactionManager manager = com.arjuna.ats.jta.TransactionManager
			.transactionManager();

	public javax.transaction.TransactionManager getManager() {
		return manager;
	}

	public void setManager(javax.transaction.TransactionManager manager) {
		this.manager = manager;
	}

	public Assembler() {
		manager = com.arjuna.ats.jta.TransactionManager.transactionManager();
	}

	public List<Widget> getWidgets(int numberOfWidget) throws SystemException {
		List<Widget> polishedlist = null;
		try {
			manager.begin();
			widgetlist = widgetPileObject.retrieveSpecificWidgets("Polished");
			polishedlist = new ArrayList<Widget>();

			for (int i = 0; i < numberOfWidget; i++) {
				// non transactional operation
				polishedlist.add(widgetlist.get(i));
				manager.commit();
			}

		} catch (NotSupportedException | SecurityException
				| IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			manager.rollback();
			e.printStackTrace();
		}
		return polishedlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object create(Object listOfWidgets) {
		if (((List<Widget>) listOfWidgets).size() > 0) {
			Gadget gadget = new Gadget(name, code, (List<Widget>) listOfWidgets);// getWidgets());
			remove(listOfWidgets);
			add(gadget);
			return gadget;
		} else {
			System.out.println("No Polished Widgets");
			return null;
		}
	}

	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		gadgetPileObject.add(obj);

	}

	@Override
	public void remove(Object obj) {
		// TODO Auto-generated method stub
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

	public int getNumberofWidget() {
		return numberofWidget;
	}

	public void setNumberofWidget(int numberofWidget) {
		this.numberofWidget = numberofWidget;
	}
}
