package com.example.broulims_1.helperClasses;

public class ProduceAdItem extends WeeklyAdItem implements ShoppingListVisitable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 227881080143862539L;
	private Double weight;
	
	public ProduceAdItem(String item, String price, Double weight) {
		this.item = item;
		this.price = price;
		this.weight = weight;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Override
	public Double accept(ShoppingListVisitor visitor) {
		return visitor.visit(this);
	}
}
