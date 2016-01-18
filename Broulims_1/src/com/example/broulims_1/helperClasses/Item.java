package com.example.broulims_1.helperClasses;

import java.io.Serializable;

public abstract class Item implements Serializable, ShoppingListVisitable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3876103807647806238L;
	protected String category; // MADE CHANGE
	protected String type;
	protected String item;
	protected String price;
	protected Integer quantity = 1;
	protected boolean checked;
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	
	@Override
	public String toString() {
		return item;
	}
	
}
