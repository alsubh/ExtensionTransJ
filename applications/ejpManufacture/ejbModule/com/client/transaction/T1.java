package com.client.transaction;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import com.entity.businesslogic.Goo;
import com.operator.businesslogic.Baker;
import com.operator.businesslogic.Builder;
import com.operator.businesslogic.Polisher;
import com.processedComponent.utilities.PolishedWidget;
import com.processedComponent.utilities.RawWidget;
import com.processedComponent.utilities.RoughWidget;

public class T1 {

	public List<Goo> goos = new ArrayList<Goo>();
	RawWidget rawWidget = new RawWidget();
	Builder builder = new Builder();

	RoughWidget roughWidget;
	Baker baker = new Baker();

	PolishedWidget polishedWidget;
	Polisher polisher = new Polisher();

	javax.transaction.TransactionManager manager = com.arjuna.ats.jta.TransactionManager
			.transactionManager();

	public T1() {
	}

	// Get Goo from Goo Pile
	public List<Goo> getGooFromPile(int numberOfGoos)
			throws IllegalStateException, SecurityException, SystemException {
		try {
			manager.begin();
			goos = builder.getGooList(numberOfGoos);
			// delete that goo from the pile
			manager.commit();
		} catch (SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException
				| NotSupportedException | SystemException e) {
			manager.rollback();
			e.printStackTrace();
		}
		return goos;
	}

	// Give Goo to a Builder and get back a Raw Widget
	public RawWidget getRawWidget(String name, String code, List<Goo> goo) {
		builder.setName(name);
		builder.setCode(code);
		try {
			manager.begin();

			rawWidget = (RawWidget) builder.create(goo);

			manager.commit();
		} catch (SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException
				| NotSupportedException | SystemException e) {
			// TODO Auto-generated catch block
			try {
				manager.rollback();
			} catch (IllegalStateException | SecurityException
					| SystemException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return rawWidget;
	}

	// Give Raw Widget to a Baker and get a Rough Widget
	public RoughWidget getRoughWidget(String name, String code,
			RawWidget rawWidget) throws IllegalStateException,
			SecurityException, SystemException {
		baker.setName(name);
		baker.setCode(code);
		baker.setStyle("Rough");
		try {
			manager.begin();

			roughWidget = (RoughWidget) baker.create(rawWidget);

			manager.commit();
		} catch (SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException
				| NotSupportedException | SystemException e) {
			manager.rollback();
			e.printStackTrace();
		}
		return roughWidget;
	}

	// Give Rough Widget to a Polisher and get a Polished Widget
	public PolishedWidget getPolishedWidget(String name, String code,
			RoughWidget roughWidget) {
		polisher.setName(name);
		polisher.setCode(code);
		polisher.setStyle("Polished");

		try {
			manager.begin();

			polishedWidget = (PolishedWidget) polisher.create(roughWidget);

			manager.commit();
		} catch (SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException
				| NotSupportedException | SystemException e) {
			try {
				manager.rollback();
			} catch (IllegalStateException | SecurityException
					| SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return polishedWidget;
	}

	// Put Polish Widget in a Widget Pile
	public void putInWidgetPile(PolishedWidget pWidget) {
		try {
			manager.begin();

			polisher.add(pWidget);

			manager.commit();
		} catch (SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException
				| NotSupportedException | SystemException e) {
			try {
				manager.rollback();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
