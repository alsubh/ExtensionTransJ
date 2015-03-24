package com.operator.businesslogic;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.entity.businesslogic.Gadget;
import com.pile.businesslogic.GadgetPile;

/**
 * Session Bean implementation class Labeler
 */
@Stateless
public class Labeler {

	private String tag = "";
	private static String serialNumber = "149000";
	private final GadgetPile gadgetpileObject = new GadgetPile();
	List<Gadget> listOfGadget = new ArrayList<Gadget>();

	private javax.transaction.TransactionManager manager = com.arjuna.ats.jta.TransactionManager
			.transactionManager();

	public Labeler() {

	}

	public void tagging() {
		listOfGadget = gadgetpileObject.retrieveSpecificGadgets(" ");
		for (Gadget gad : listOfGadget) {
			// Update Tag
			gadgetpileObject.updateTag(gad, generateSerial());
		}
	}

	public void tagging(Gadget gad) {
		// Update Tag
		gadgetpileObject.updateTag(gad, getTag());
	}

	public String generateSerial()// (Gadget gad)
	{
		// serialNumber= serialNumber+1;
		return serialNumber;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public javax.transaction.TransactionManager getManager() {
		return manager;
	}

	public void setManager(javax.transaction.TransactionManager manager) {
		this.manager = manager;
	}
}
