package com.example.broulims_1.helperClasses;

public class PersonalItem extends Item implements ShoppingListVisitable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6634262380715728934L;
	private String customCategory;
	private Double customPrice;
	
	public Double getCustomPrice() {
		return customPrice;
	}
	public void setCustomPrice(Double customPrice) {
		this.customPrice = customPrice;
	}
	public String getCustomCategory() {
		return customCategory;
	}
	public void setCustomCategory(String customCategory) {
		this.customCategory = customCategory;
	}
	@Override
	public Double accept(ShoppingListVisitor visitor) {
		return visitor.visit(this);
	}
	
}
