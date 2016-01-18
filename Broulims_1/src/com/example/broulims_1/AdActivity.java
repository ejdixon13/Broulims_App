package com.example.broulims_1;


//import com.example.broulims_1.helperClasses.LocalStorage;
import java.util.ArrayList;

import com.example.broulims_1.helperClasses.AdList;
import com.example.broulims_1.helperClasses.ButtonAnimation;
import com.example.broulims_1.helperClasses.FirebaseData;
import com.example.broulims_1.helperClasses.LocalStorage;
import com.example.broulims_1.helperClasses.PersonalItem;
import com.example.broulims_1.helperClasses.ProduceAdItem;
import com.example.broulims_1.helperClasses.QuantifiedAdItem;
import com.example.broulims_1.helperClasses.Item;
import com.example.broulims_1.helperClasses.ShopList;
import com.example.broulims_1.helperClasses.ShoppingListVisitable;
import com.example.broulims_1.helperClasses.Store;
import com.example.broulims_1.helperClasses.WeeklyAdItem;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AdActivity extends Activity {
	GridView list;
	ShopList shopList = new ShopList();
	AdList adList = new AdList();
	Button searchProduct;
	CustomList adapter;
	String category;
	Integer categPosition;
	Spinner categoryDropDown;
	ProgressBar spinner;
	Activity activity;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getIntent().getExtras();
		category = b.getString("category");
		categPosition = b.getInt("position");
		

		setContentView(R.layout.ad_main);
		
		activity = this;
		
		// set the loading bar for retrieving items from Firebase
		spinner = (ProgressBar)findViewById(R.id.progressBar1);
	    spinner.setVisibility(View.GONE);
		
		categoryDropDown = (Spinner) findViewById(R.id.spinner1);
		categoryDropDown.setSelection(categPosition);

//		ShopList aList = new ShopList();
//		Item item = new WeeklyAdItem();
//		item.setItem("Milk");
//		item.setPrice("2.99");
//		aList.add(item);
//		
//		LocalStorage.storeList(this, aList);
		//if a store file doesn't exist create one
		  if (LocalStorage.retrieveList(this) == null) {
			  LocalStorage.storeList(this, shopList);
		  }
		  shopList = LocalStorage.retrieveList(this);

		//CustomList adapter = new CustomList(AdActivity.this, products, imageId);
		adapter = new CustomList(AdActivity.this, adList, shopList);
		list = (GridView)findViewById(R.id.list);
		list.setAdapter(adapter);
		
		//FirebaseData.updateAdData(shopList);	
		updateAdData();

	}

	
	/**
	 * This updates the Weekly ad to its most current data through Firebase
	 */
	private void updateAdData() {
		
		spinner.setVisibility(View.VISIBLE);
		Store curStore = LocalStorage.retrieveStore(activity);
		String firebaseUrl = "https://incandescent-fire-4835.firebaseio.com/TestAd/" + curStore.getCityLocation(); 
				Toast.makeText(activity, firebaseUrl,
						Toast.LENGTH_SHORT).show();
		// Arbitrary store location
		Firebase f = new Firebase(firebaseUrl);
		f.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot snapshot) {
				for (DataSnapshot obj : snapshot.getChildren()) {
					if (obj.child("Name").getValue() != null) {
						String productName = obj.child("Name").getValue()
								.toString();

						if (!adList.productNames.contains(productName)) {
							WeeklyAdItem newItem = FirebaseData.firebaseCategoryParser(obj, productName);
							
							// add accourding to category
							if (category.equals("All")
									|| (newItem.getCategory().equals(category))) {
								Integer position = 0;
								// cycle through adList to see if product is
								// already in list // if not set checked to
								// false
								for (Item item : adList) {
									if (shopList.productNames.contains(item
											.toString())) {
										CustomList.mCheckStates.put(position,
												true);
										item.setChecked(true);
									} else {
										item.setChecked(false);
									}
									position++;
								}

								if (shopList != null) {
									// if its the first item in the list
									if (shopList.productNames.contains(newItem
											.toString())) {
										CustomList.mCheckStates.put(position,
												true);
										newItem.setChecked(true);
									} else {
										newItem.setChecked(false);
									}
								} else {
									Toast.makeText(activity, "List is Empty",
											Toast.LENGTH_SHORT).show();
								}

								adList.add(newItem);
							}

						}
						adapter.notifyDataSetChanged();
						spinner.setVisibility(View.GONE);
					}
				}
			}

			@Override
			public void onCancelled(FirebaseError error) {
				System.err.println("Listener was cancelled");
			}
		});
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		for (int i = 0; i < CustomList.mCheckStates.size(); i++ ) {
			adList.get(i).setChecked(CustomList.mCheckStates.get(i));
		}
		shopList = LocalStorage.retrieveList(this);
		adapter.notifyDataSetChanged();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weekly_ad_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.list) {
		    Intent intent = new Intent(this, Shopping.class);
            startActivity(intent);  
		}
		else if (id == R.id.home) {
			Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent); 
		}
		return super.onOptionsItemSelected(item);
	}
}
