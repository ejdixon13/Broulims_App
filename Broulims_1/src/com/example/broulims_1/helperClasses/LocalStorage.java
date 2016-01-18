package com.example.broulims_1.helperClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.example.broulims_1.AdActivity;
import com.example.broulims_1.CustomList;

import android.content.Context;
import android.widget.Toast;

public class LocalStorage {
	
	static String fileName;
	
	
	
    /**
     * Add Item to list in local storage
     * @param context
     * @param item
     * @return
     */
    public static boolean addToList(Context context, Item item) {
    	ShopList tempList = LocalStorage.retrieveList(context);
    	if (!tempList.productNames.contains(item.getItem()) && (item.getItem()!= null)) {
    		// make sure item is not checked when added
    		item.setChecked(false);
        	tempList.add(item);
        	Toast.makeText(context, "Added " + item.getItem() + " to your list", Toast.LENGTH_SHORT).show();
    	}
    	//Toast.makeText(context, context.getFilesDir().getPath() + "/list.ser", Toast.LENGTH_SHORT).show();
    	LocalStorage.storeList(context, tempList);
    	return true;
    }
    
    /**
     * Remove Item from list in local storage
     * @param context
     * @param item
     * @return
     */
    public static boolean removeFromList(Context context, Item item) {
    	ShopList tempList = LocalStorage.retrieveList(context);

    	
    	if ((item.getItem() != null) && tempList.productNames.contains(item.getItem())) {
        	
    		for (Item anItem : tempList) {
    			if (anItem.getItem().equals(item.getItem())) {
    				tempList.remove(anItem);
    				Toast.makeText(context, "Removed " + item.getItem() + " from your list", Toast.LENGTH_SHORT).show();
    			}		
        	}
    	}
    	else {
    		Toast.makeText(context, "Did not remove " + item.getItem(), Toast.LENGTH_SHORT).show();
    		return false;
    	}
    	LocalStorage.storeList(context, tempList);
    	return true;
    }
    
	/**
	 * Stores list on local android file system. returns true if it succeeds.
	 * @param object
	 * @return
	 */
	public static boolean storeList(Context context, Serializable object) {
	    FileOutputStream fos = null;
	    ObjectOutputStream out = null;
	    try {
	      File fileName = new File(context.getFilesDir().getPath() + "/list.ser");
	      //Toast.makeText(context, "File Name: " + fileName, Toast.LENGTH_LONG).show();
	      fos = new FileOutputStream(fileName);
	      out = new ObjectOutputStream(fos);
	      out.reset();
	      out.writeUnshared(object);
	      //out.reset();
	      out.close();
	      return true;
	    } catch (Exception ex) {
	      ex.printStackTrace();
	      return false;
	    }
	}
	
	public static boolean storeList(Serializable object) {
	    FileOutputStream fos = null;
	    ObjectOutputStream out = null;
	    try {
	      fos = new FileOutputStream("list.ser");
	      out = new ObjectOutputStream(fos);
	      out.writeUnshared(object);
	      out.reset();
	      out.close();
	      return true;
	    } catch (Exception ex) {
	      ex.printStackTrace();
	      return false;
	    }
	}
	
	public static ShopList retrieveList(Context context) {
		ShopList shopList = new ShopList();
	    FileInputStream fis = null;
	    ObjectInputStream in = null;
	    try {
	      File fileName = new File(context.getFilesDir().getPath() + "/list.ser");
	      fis = new FileInputStream(fileName);
	      //Toast.makeText(context, "File Read Name: " + fileName, Toast.LENGTH_LONG).show();
	      in = new ObjectInputStream(fis);
	      shopList = (ShopList) in.readObject();
	      //shopList = (ShopList) in.readUnshared();
	      
	      in.close();
	      return shopList;
	    } catch (Exception ex) {
	      ex.printStackTrace();
	      return null;
	    }
	}
	
	public static ShopList retrieveList() {
		ShopList shopList = new ShopList();
	    FileInputStream fis = null;
	    ObjectInputStream in = null;
	    try {
	      String fileName = "list.ser";
	      fis = new FileInputStream(fileName);
	      in = new ObjectInputStream(fis);
	      shopList = (ShopList) in.readObject();
	      in.close();
	      return shopList;
	    } catch (Exception ex) {
	      ex.printStackTrace();
	      return null;
	    }
	}
	/***
	 * Stores store location on local android file system. returns true if it succeeds.
	 * @param object
	 * @return
	 */
	public static boolean storeStore(Context context, Serializable object) {
	    FileOutputStream fos = null;
	    ObjectOutputStream out = null;
	    try {
	      File fileName = new File(context.getFilesDir().getPath() + "/store.ser");
	      //Toast.makeText(context, "File Name: " + fileName, Toast.LENGTH_LONG).show();
	      fos = new FileOutputStream(fileName);
	      out = new ObjectOutputStream(fos);
	      out.reset();
	      out.writeUnshared(object);
	      //out.reset();
	      out.close();
	      return true;
	    } catch (Exception ex) {
	      ex.printStackTrace();
	      return false;
	    }
	}
	/***
	 * retrieves store location from local android file system. returns true if it succeeds.
	 * @param object
	 * @return
	 */
	public static Store retrieveStore(Context context) {
		Store store;
	    FileInputStream fis = null;
	    ObjectInputStream in = null;
	    try {
	      File fileName = new File(context.getFilesDir().getPath() + "/store.ser");
	      fis = new FileInputStream(fileName);
	      //Toast.makeText(context, "File Read Name: " + fileName, Toast.LENGTH_LONG).show();
	      in = new ObjectInputStream(fis);
	      store = (Store) in.readObject();
	      //shopList = (ShopList) in.readUnshared();
	      
	      in.close();
	      return store;
	    } catch (Exception ex) {
	      ex.printStackTrace();
	      return null;
	    }
	}
	

}
