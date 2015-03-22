package com.entity.business;

import javax.ejb.Remote;

@Remote
public interface IPile {

	// Database Transactions 
	public void add(Object obj);
	public void remove(Object obj);
	public int count();
}
