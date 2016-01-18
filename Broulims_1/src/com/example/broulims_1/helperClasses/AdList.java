package com.example.broulims_1.helperClasses;

import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;

public class AdList extends CopyOnWriteArrayList<WeeklyAdItem> implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2894337904785728159L;

	public CopyOnWriteArrayList<String> productNames = new CopyOnWriteArrayList<String>();
	
	@Override
	public boolean add(WeeklyAdItem e) {
		productNames.add(e.getItem());
		return super.add(e);	
	}
	
	@Override
	public boolean remove(Object e) {
		Item item = (Item)e;
		productNames.remove(item.getItem());
		return super.remove(e);	
	}
}
