package com.example.broulims_1.helperClasses;

/**
 * 
 * @author ericjdixon
 * This Interface implements the Visitor Design Pattern decoupling the Shopping List operations
 *
 *
 */
public interface ShoppingListVisitor {
	public Double visit(ProduceAdItem item);
	public Double visit(QuantifiedAdItem item);
	public Double visit(PersonalItem item);
	public Double visit(WeeklyAdItem item);
}
