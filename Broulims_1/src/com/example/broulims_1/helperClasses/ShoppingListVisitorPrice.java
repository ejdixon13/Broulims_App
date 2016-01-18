package com.example.broulims_1.helperClasses;

public class ShoppingListVisitorPrice implements ShoppingListVisitor {

	@Override
	public Double visit(ProduceAdItem item) {
		if (item.getPrice() != null && item.getWeight() != null) {
			//return (item.getPrice() * item.getWeight());
			return 0.0;
		}
		else
			return 0.0;
	}

	@Override
	public Double visit(QuantifiedAdItem item) {
		if (item.getPrice() != null && item.getQuantity() != null) {
			//return (item.getPrice() * item.getQuantity());
			return 0.0;
		}
		else
			return 0.0;
	}

	@Override
	public Double visit(PersonalItem item) {
		if (item.getCustomPrice() != null) {
			return item.getCustomPrice();
		}
		else
			return 0.0;
	}
	
	@Override
	public Double visit(WeeklyAdItem item) {
		return 0.0;
	}


}
