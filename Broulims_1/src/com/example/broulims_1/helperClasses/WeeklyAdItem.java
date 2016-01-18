package com.example.broulims_1.helperClasses;

import java.io.File;

import android.graphics.Bitmap;
import android.os.Bundle;

/**
 * 
 * @author ericjdixon
 * The WeeklyAdItem will hold our Item that has a picture associated with it as well as a storeID
 *
 */

public class WeeklyAdItem extends Item implements ShoppingListVisitable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7193002109443342873L;
	// either price per unit or price per lb with produce
	//CHANGED CLASS
	protected String price;
	protected String category;
	protected String pictureBase64;
	protected String adDescript;
	protected String storeName;
	protected String itemId;
	protected String adStartDate;
	protected String adEndDate;

	public Bundle makeBundle (Integer position, boolean checked) {
		Bundle b = new Bundle();
		b.putString("productName", this.item);
		b.putString("price", this.price);
		b.putString("category", this.category);
		b.putString("pictureBase64", this.pictureBase64);
		b.putString("adDescript", this.adDescript);
		b.putString("itemId", this.itemId);
		b.putString("adStartDate", this.adStartDate);
		b.putString("adEndDate", this.adEndDate);
		b.putBoolean("checked", checked);
		b.putInt("position", position);
		return b;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAdDescript() {
		return adDescript;
	}
	public void setAdDescript(String adDescript) {
		this.adDescript = adDescript;
	}
	public String getAdStartDate() {
		return adStartDate;
	}
	public void setAdStartDate(String adStartDate) {
		this.adStartDate = adStartDate;
	}
	public String getAdEndDate() {
		return adEndDate;
	}
	public void setAdEndDate(String adEndDate) {
		this.adEndDate = adEndDate;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	public Bitmap getAdPicture() {
		return FirebaseData.loadImageFromBase64(pictureBase64);
	}
	public void setPictureBase64(String pictureBase64) {
		this.pictureBase64 = pictureBase64;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	@Override
	public Double accept(ShoppingListVisitor visitor) {
		return visitor.visit(this);
	}
}
