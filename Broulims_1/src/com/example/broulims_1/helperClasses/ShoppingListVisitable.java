package com.example.broulims_1.helperClasses;

public interface ShoppingListVisitable {
	// method for our operations
	public Double accept(ShoppingListVisitor visitor);
}
