package com.example.broulims_1.helperClasses;

public class QuantifiedAdItem extends WeeklyAdItem implements ShoppingListVisitable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6618998249604659508L;
	private Integer quantity;

	public QuantifiedAdItem (String item, String price, Integer quantity) {
		this.item = item;
		this.price = price;
		this.quantity = quantity;
	}
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public Double accept(ShoppingListVisitor visitor) {
		return visitor.visit(this);
	}
}
