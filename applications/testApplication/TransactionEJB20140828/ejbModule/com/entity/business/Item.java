package com.entity.business;

import javax.ejb.Remote;

@Remote
public interface Item {

	public int calculateTotal();
	
}
