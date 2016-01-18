package com.example.broulims_1;

import com.example.broulims_1.helperClasses.Item;
import com.example.broulims_1.helperClasses.LocalStorage;
import com.example.broulims_1.helperClasses.PersonalItem;
import com.example.broulims_1.helperClasses.ShopList;





import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class Shopping extends Activity {
	
	Button add;
	ShopList arrayOfItems;
	ListView mylistview;
    Shop_View listAdapter;
    ListAdapter itemAdapter;
    PersonalItem addPer;
    Activity activity;
	//The "x" and "y" position of the "Show Button" on screen.
	Point p;
	TextView btnOpenAdd;
    //Testing variable//
    ShopList list;
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping);
		activity = this;
		populateShoppingList();
		popup_Window_Add();
		popup_Window_Remove();

	}
		
	private void popup_Window_Remove() {
        final TextView btnOpenRemove = (TextView)findViewById(R.id.remove_button);
        btnOpenRemove.setOnClickListener(new View.OnClickListener(){

   @Override
   public void onClick(View arg0) {
    LayoutInflater layoutInflater 
     = (LayoutInflater)getBaseContext()
      .getSystemService(LAYOUT_INFLATER_SERVICE);  
    View popupView = layoutInflater.inflate(R.layout.shopping_remove, null);  
             final PopupWindow popupWindow = new PopupWindow(
               popupView, 
               LayoutParams.WRAP_CONTENT,  
                     LayoutParams.WRAP_CONTENT);  
             popupWindow.setOutsideTouchable(true);
             popupWindow.setTouchable(true);
             popupWindow.setFocusable(true);
             popupWindow.setBackgroundDrawable(new BitmapDrawable());
             popupWindow.showAsDropDown(btnOpenAdd);
             
             TextView btnDismiss = (TextView) popupView.findViewById(R.id.remove_button_final);      	 
            
             
             btnDismiss.setOnClickListener(new View.OnClickListener(){
            
             
     @Override
     public void onClick(View v) {

         for (Item item : arrayOfItems) {
        	 if (item.isChecked()) {
        		 arrayOfItems.remove(item);
        	 }
         }
         LocalStorage.storeList(activity, arrayOfItems);
         mylistview.setAdapter(listAdapter);
      popupWindow.dismiss();
     }});
               
             popupWindow.showAsDropDown(btnOpenRemove, 50, -30);
         
   }});
        
	}
	private void popup_Window_Add() {
        btnOpenAdd = (TextView)findViewById(R.id.add_button);
        btnOpenAdd.setOnClickListener(new View.OnClickListener(){

   @Override
   public void onClick(View arg0) {
    LayoutInflater layoutInflater 
     = (LayoutInflater)getBaseContext()
      .getSystemService(LAYOUT_INFLATER_SERVICE);  
    View popupView = layoutInflater.inflate(R.layout.shopping_add, null);  
             final PopupWindow popupWindow = new PopupWindow(
               popupView, 
               LayoutParams.WRAP_CONTENT,  
                     LayoutParams.WRAP_CONTENT);  
             popupWindow.setOutsideTouchable(true);
             popupWindow.setTouchable(true);
             popupWindow.setFocusable(true);
             popupWindow.setBackgroundDrawable(new BitmapDrawable());
        	 addPer = new PersonalItem();
             
             TextView btnDismiss = (TextView) popupView.findViewById(R.id.add_button_final);
             final EditText editText = (EditText) popupView.findViewById(R.id.add_item);
             final EditText editPrice = (EditText) popupView.findViewById(R.id.item_Price);        	 
            
             
             btnDismiss.setOnClickListener(new View.OnClickListener(){
            
             
     @Override
     public void onClick(View v) {

         

    	 addPer.setCategory("Other");
         addPer.setItem(editText.getText().toString());
    	 addPer.setPrice(editPrice.getText().toString());
    	 addPer.setQuantity(1);
    	 if (!addPer.getItem().matches("")) {
    		 arrayOfItems.add(addPer);
    		 LocalStorage.addToList(activity, addPer);
    	 }
         mylistview.setAdapter(listAdapter);
      popupWindow.dismiss();
     }});
               
             popupWindow.showAsDropDown(btnOpenAdd, 50, -30);
         
   }});
        
	}
	
	

	
	private void populateShoppingList() {
        //*************************
//		list = new ShopList();
//        Item testing = new PersonalItem();
//        testing.setCategory("Other");
//        testing.setItem("hahahahha");
//        testing.setPrice("$2.00");
//        list.add(testing);
//        LocalStorage.storeList(this, list);
        //**************************
        
        mylistview = (ListView) findViewById(R.id.list_shop);
		arrayOfItems = (ShopList) LocalStorage.retrieveList(this);
        listAdapter = new Shop_View(Shopping.this, arrayOfItems);
        
        mylistview.setAdapter(listAdapter);
       
        

	}
}
