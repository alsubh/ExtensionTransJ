package com.operator.business;

import javax.ejb.Remote;

@Remote
public interface Production {

	public Object create(Object obj);
	
	public void add(Object obj);
	
	public void remove(Object obj);
}
