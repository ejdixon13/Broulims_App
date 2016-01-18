package com.example.Test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import android.content.Context;

import com.example.broulims_1.MainActivity;
import com.example.broulims_1.helperClasses.Item;
import com.example.broulims_1.helperClasses.LocalStorage;
import com.example.broulims_1.helperClasses.PersonalItem;
import com.example.broulims_1.helperClasses.ProduceAdItem;
import com.example.broulims_1.helperClasses.QuantifiedAdItem;
import com.example.broulims_1.helperClasses.ShopList;
import com.example.broulims_1.helperClasses.ShoppingListVisitable;

public class ShopListTest {

	  @Test
	  public void addItem() {
	    ShopList shopList = new ShopList();
	    ProduceAdItem apple = new ProduceAdItem("Apple","1.99", 3.1);
	    PersonalItem personalItem = new PersonalItem();
	    QuantifiedAdItem itemWithQuantity = new QuantifiedAdItem("Stuff","1.50", 10);
	    shopList.add(apple);
	    shopList.add(personalItem);
	    shopList.add(itemWithQuantity);
	    
	    Assert.assertSame(apple, shopList.remove(shopList.indexOf(apple)));
	    Assert.assertSame(personalItem, shopList.remove(shopList.indexOf(personalItem)));
	    Assert.assertSame(itemWithQuantity, shopList.remove(shopList.indexOf(itemWithQuantity)));
	  }

	  @Test
	  public void getTotal() {
		  ShopList shopList = new ShopList();
		  ProduceAdItem apple = new ProduceAdItem("Apple","1.99", 3.1);
		  PersonalItem personalItem = new PersonalItem();
		  QuantifiedAdItem itemWithQuantity = new QuantifiedAdItem("Stuff","1.50", 100);
		  shopList.add(apple);
		  shopList.add(personalItem);
		  shopList.add(itemWithQuantity);
		  
		  System.out.println("total: " + shopList.getTotal());
		  Assert.assertEquals("$156.17", shopList.getTotal());

	  }
	  
	  @Test
	  public void storeRetrieveItem() {
		    ShopList shopList = new ShopList();
		    ProduceAdItem apple = new ProduceAdItem("Apple", "1.99", 3.1);
		    PersonalItem personalItem = new PersonalItem();
		    personalItem.setItem("Nunchucks");
		    QuantifiedAdItem itemWithQuantity = new QuantifiedAdItem("Stuff", "1.50", 10);
		    shopList.add(apple);
		    shopList.add(personalItem);
		    shopList.add(itemWithQuantity);
		    for (ShoppingListVisitable item : shopList) {
		    	System.out.println("itemBeforeSerial: " + item.toString());
		    }
		    
		    LocalStorage.storeList(shopList);
		    
		    ShopList newShopList = LocalStorage.retrieveList();
		    for (ShoppingListVisitable item : newShopList) {
		    	System.out.println("itemAfterSerial: " + item.toString());
		    }
		    Assert.assertEquals(shopList, newShopList);
	  }

}
