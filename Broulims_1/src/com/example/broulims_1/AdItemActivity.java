package com.example.broulims_1;

import com.example.broulims_1.helperClasses.ButtonAnimation;
import com.example.broulims_1.helperClasses.FirebaseData;
import com.example.broulims_1.helperClasses.Item;
import com.example.broulims_1.helperClasses.LocalStorage;
import com.example.broulims_1.helperClasses.ShopList;
import com.example.broulims_1.helperClasses.WeeklyAdItem;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AdItemActivity extends Activity {
	CheckBox addSign;
	ImageView imageView;
	TextView productTitle;
	TextView productPrice;
	TextView productDetails;
	TextView productQuantity;
	TextView productValid;
	ImageView addQuant;
	ImageView subQuant;
	Integer position;
	Integer quantity;
	WeeklyAdItem listItem = new WeeklyAdItem();
	private Activity context;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ad_item_screen);
		Bundle b = getIntent().getExtras();
		context = this;
		quantity = 1;
		// initiate quantity with one

		// set the product Position
		position = b.getInt("position");

		// set the checkbox
		boolean isChecked = b.getBoolean("checked");
		addSign = (CheckBox) findViewById(R.id.checkBox1);
		addSign.setChecked(isChecked);
		addSign.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				CheckBox check = (CheckBox) v;

				ButtonAnimation.animate(v);
				ShopList list = LocalStorage.retrieveList(context);
				
				// add/remove from list depending on check
				if (check.isChecked()) {
					for (Item item : list) {
						if (item.getItem().equals(
								productTitle.getText().toString())) {
							LocalStorage.removeFromList(context, item);		
						}
					}
				}
				else {
					LocalStorage.addToList(context, listItem);					
				}
				CustomList.mCheckStates.put(position, !check.isChecked());
			}
		});

		// set the add button
		addQuant = (ImageView) findViewById(R.id.imageButtonAdd);
		addQuant.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ShopList list = LocalStorage.retrieveList(context);
				for (Item item : list) {
					if (item.getItem()
							.equals(productTitle.getText().toString())) {
						Item tempItem = item;
						list.remove(item);
						tempItem.setQuantity(++quantity);
						list.add(tempItem);

						productQuantity.setText(String.valueOf(quantity));
						LocalStorage.storeList(context, list);
					}
				}
			}
		});

		// set the sub button
		subQuant = (ImageView) findViewById(R.id.imageButtonSub);
		subQuant.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (quantity > 1) {
					ShopList list = LocalStorage.retrieveList(context);
					for (Item item : list) {
						if (item.getItem().equals(
								productTitle.getText().toString())) {
							Item tempItem = item;
							list.remove(item);
							tempItem.setQuantity(--quantity);
							list.add(tempItem);

							productQuantity.setText(String.valueOf(quantity));
							LocalStorage.storeList(context, list);
						}
					}
				}
			}
		});

		// set the image
		Bitmap adImage = FirebaseData.loadImageFromBase64(b
				.getString("pictureBase64"));
		imageView = (ImageView) findViewById(R.id.imageView1);
		imageView.setImageBitmap(adImage);

		// set the product name
		String productName = b.getString("productName");
		listItem.setItem(productName);
		productTitle = (TextView) findViewById(R.id.productName);
		productTitle.setText(productName);

		// set the product price
		String price = b.getString("price");
		listItem.setPrice(price);
		productPrice = (TextView) findViewById(R.id.price);
		productPrice.setText(price);

		// set the product details
		String details = b.getString("adDescript");
		productDetails = (TextView) findViewById(R.id.details);
		productDetails.setText(details);

		// set the product quantity in List
		productQuantity = (TextView) findViewById(R.id.itemQuantity);
		if (isChecked) {
			ShopList list = LocalStorage.retrieveList(context);
			for (Item item : list) {
				if (item.getItem().equals(productTitle.getText().toString())) {
					quantity = item.getQuantity();
					productQuantity.setText(String.valueOf(quantity));
				}
			}
		} else {
			// default item quantity set
			productQuantity.setText(String.valueOf(quantity));
		}
		listItem.setQuantity(quantity);
		
		// set the valid dates
		String adStart = b.getString("adStartDate");
		String adEnd = b.getString("adEndDate");
		// CREATE HELPER CLASS TO PARSE DATES
		productValid = (TextView) findViewById(R.id.adExpiration);
		productValid.setText("Prices Valid: " + adStart + " - " + adEnd);
		
		// set the category if the item gets added
		String category = b.getString("category");
		listItem.setCategory(category);

	}

	@Override
	public void onResume() {
		super.onResume();
	}

}
