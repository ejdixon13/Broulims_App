package com.example.broulims_1.helperClasses;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShopList extends CopyOnWriteArrayList<Item> implements Serializable {
	
	
	/**
	 *  need this serialVersionUID in order to extend ArrayList
	 */
	private static final long serialVersionUID = -7136288384614579974L;
	public CopyOnWriteArrayList<String> productNames = new CopyOnWriteArrayList<String>();
	
	@Override
	public boolean add(Item e) {
		productNames.add(e.getItem());
		return super.add(e);	
	}
	
	@Override
	public boolean remove(Object e) {
		Item item = (Item)e;
		productNames.remove(item.getItem());
		return super.remove(e);	
	}

	// utilize the ShoppingListVisitorPrice to get a price for each of our items
	public String getTotal() {
		Double sum = 0.0;
		ShoppingListVisitor priceVisitor = new ShoppingListVisitorPrice();
		for (Item item: this) {
			sum += item.accept(priceVisitor);
		} 

		return new DecimalFormat("$#.##").format(sum);
	}

}
