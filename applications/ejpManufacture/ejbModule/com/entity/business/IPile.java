package com.entity.business;

import javax.ejb.Remote;

import com.arjuna.ats.txoj.common.*;

@Remote
public interface IPile {
	// Database Transactions 
	public void add(Object obj);
	public void remove(Object obj);
	public int count();
}
