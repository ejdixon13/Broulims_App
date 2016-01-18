package com.example.broulims_1.helperClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//import java.util.Base64;
//import java.util.Base64.Decoder;
//import java.util.Base64.Encoder;
//import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
//import java.util.Base64.*;

//import javax.xml.bind.DatatypeConverter;








import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;

import com.example.broulims_1.AdActivity;
import com.example.broulims_1.CustomList;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class FirebaseData {

	/**
	 * @param args
	 */
	/**
	 * This updates the Weekly ad to its most current data through Firebase
	 */
//	public static void updateAdData(ShopList aShopList) {
//		final ShopList shopList = aShopList;
//		AdActivity.spinner.setVisibility(View.VISIBLE);
//		// Arbitrary store location
//		Firebase f = new Firebase(
//				"https://incandescent-fire-4835.firebaseio.com/TestAd/Afton");
//		f.addValueEventListener(new ValueEventListener() {
//			@Override
//			public void onDataChange(DataSnapshot snapshot) {
//				for (DataSnapshot obj : snapshot.getChildren()) {
//					if (obj.child("Name").getValue() != null) {
//						String productName = obj.child("Name").getValue()
//								.toString();
//
//						if (!AdActivity.adList.productNames.contains(productName)) {
//							WeeklyAdItem newItem = FirebaseData.firebaseCategoryParser(obj, productName);
//							Integer position = 0;
//							for (Item item : AdActivity.adList) {
//								if (shopList.productNames.contains(item.toString())) {
//									CustomList.mCheckStates.put(position, true);
//								}
//								position++;
//							}
//							if (shopList.productNames.contains(newItem.toString())) {
//								CustomList.mCheckStates.put(position, true);
//							}
//							if (!AdActivity.adList.productNames.contains(newItem.toString())) {
//								AdActivity.adList.add(newItem);
//							}
//						}
//						AdActivity.adapter.notifyDataSetChanged();
//						AdActivity.spinner.setVisibility(View.GONE);
//					}
//				}
//			}
//
//			@Override
//			public void onCancelled(FirebaseError error) {
//				System.err.println("Listener was cancelled");
//			}
//		});
//	}
	
	public static WeeklyAdItem firebaseCategoryParser(DataSnapshot obj, String productName) {
		String productPrice = "0.00";
		String category = "Other";
		String adPicture = "picture";
		String adStart = "000000";
		String adEnd = "000000";
		String description = "Nothing";
		String adId = "00000";
		
		if(obj.getName() != null) {
			adId = obj.getName();
		}
		if (obj.child("Price").getValue() != null) {
			productPrice = obj.child("Price").getValue().toString();
		}
		if (obj.child("Category").getValue() != null) {
			category = obj.child("Category").getValue().toString();
		}
		if (obj.child("Ad_Picture").getValue() != null) {
			adPicture = obj.child("Ad_Picture").getValue().toString();
		}
		if (obj.child("Ad_Start").getValue() != null) {
			adStart = obj.child("Ad_Start").getValue().toString();
		}
		if (obj.child("Ad_End").getValue() != null) {
			adEnd = obj.child("Ad_End").getValue().toString();
		}
		if (obj.child("Description").getValue() != null) {
			description = obj.child("Description").getValue().toString();
		}
		WeeklyAdItem newItem = null;
		/**
		 * Future Feature parse by category for different qualities of product
		 */
		if (category.equals("Produce")) {
			newItem = new ProduceAdItem(productName, productPrice, 1.0);
			newItem.setCategory(category);
			newItem.setAdStartDate(adStart);
			newItem.setAdEndDate(adEnd);
			newItem.setAdDescript(description);
			newItem.setPictureBase64(adPicture);
			newItem.setItemId(adId);
		} else {
			newItem = new WeeklyAdItem();
			newItem.setItem(productName);
			newItem.setPrice(productPrice);
			newItem.setCategory(category);
			newItem.setAdStartDate(adStart);
			newItem.setAdEndDate(adEnd);
			newItem.setAdDescript(description);
			newItem.setPictureBase64(adPicture);
			newItem.setItemId(adId);
		}

		return newItem;
	}
	
	
	public static Bitmap loadImageFromBase64(String base64) {
		byte[] imageAsBytes = decodeImage(base64);
		return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
	}
	
	public boolean saveImage(File file) {
		try {

			// Reading a Image file from file system
			FileInputStream imageInFile = new FileInputStream(file);
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);

			// Converting Image byte array into Base64 String
			//String imageDataString = "stuff";
			String imageDataString = encodeImage(imageData);

			Firebase f = new Firebase(
					"https://incandescent-fire-4835.firebaseio.com/Images");

//			// add first and last name inputted
//			Map<String, Object> toSet = new HashMap<String, Object>();
//			toSet.put("image01", imageDataString);
//			f.push().setValue(toSet);
			f.child("image01").setValue(imageDataString);

//			f.addValueEventListener(new ValueEventListener() {
//				@Override
//				public void onDataChange(DataSnapshot snapshot) {
//					// TextView newsFeed =
//					// (TextView)findViewById(R.id.newsFeed);
//					for (DataSnapshot obj : snapshot.getChildren()) {
//						String firstName = obj.child("first").getValue()
//								.toString();
//						String lastName = obj.child("last").getValue()
//								.toString();
//						// if (!listItems.contains(lastName + ", " +
//						// firstName)){
//						// listItems.add(lastName + ", " + firstName);
//						// }
//						// adapter.notifyDataSetChanged();
//					}
//				}
//
//				@Override
//				public void onCancelled(FirebaseError error) {
//					System.err.println("Listener was cancelled");
//				}
//			});

			imageInFile.close();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}
		return false;
	}

	/**
	 * Encodes the byte array into base64 string
	 *
	 * @param imageByteArray
	 *            - byte array
	 * @return String a {@link java.lang.String}
	 */
	private String encodeImage(byte[] imageByteArray) {
		return Base64.encodeToString(imageByteArray, Base64.DEFAULT);
	}

	/**
	 * Decodes the base64 string into byte array
	 *
	 * @param imageDataString
	 *            - a {@link java.lang.String}
	 * @return byte array
	 */
	private static byte[] decodeImage(String imageDataString) {
		return Base64.decode(imageDataString, Base64.DEFAULT);
	}

}
