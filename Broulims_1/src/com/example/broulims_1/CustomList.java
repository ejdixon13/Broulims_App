package com.example.broulims_1;

import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.example.broulims_1.helperClasses.AdList;
import com.example.broulims_1.helperClasses.ButtonAnimation;
import com.example.broulims_1.helperClasses.Item;
import com.example.broulims_1.helperClasses.LocalStorage;
import com.example.broulims_1.helperClasses.ProduceAdItem;
import com.example.broulims_1.helperClasses.ShopList;
import com.example.broulims_1.helperClasses.ShoppingListVisitable;
import com.example.broulims_1.helperClasses.WeeklyAdItem;

import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomList extends ArrayAdapter<WeeklyAdItem> {
	private final Activity context;
	//private final String[] products;
	private final AdList adProducts;
	private final ShopList shopList;
    CheckBox addSign;
    TextView productName;
    TextView productPrice;
    ImageView imageView;
	public static SparseBooleanArray mCheckStates;

	public CustomList(Activity context, AdList adProducts, ShopList shopList) {
		super(context, R.layout.ad_single, adProducts);
		
		this.context = context;
		this.adProducts = adProducts;
		this.shopList = shopList;
		
		mCheckStates = new SparseBooleanArray(adProducts.size());
	}
	
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return adProducts.size();
    }


    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub

        return 0;
    }
    
	// our ViewHolder.
	// caches our view
	static class ViewHolderItem {
	    CheckBox addSign;
	    TextView productName;
	    TextView productPrice;
	    ImageView imageView;
	    WeeklyAdItem item;
	    Integer productPosition;
	}

	@Override
	public View getView(int position, View gridView, ViewGroup parent) {
		final ViewHolderItem viewHolder;
		
		if(gridView == null){
	         
	        // inflate the layout
	    	LayoutInflater inflater = context.getLayoutInflater();
	    	gridView = inflater.inflate(R.layout.ad_single, null, true);
	    	
	    	gridView.setBackgroundResource(R.drawable.grid_item_border);
	         
	        // well set up the ViewHolder
	        viewHolder = new ViewHolderItem();

	        // store the holder with the view.
	        gridView.setTag(viewHolder);
	         
	    }else{
	        // we've just avoided calling findViewById() on resource everytime
	        // just use the viewHolder
	        viewHolder = (ViewHolderItem) gridView.getTag();
	    }
		viewHolder.productPosition = position;
		viewHolder.item = (WeeklyAdItem)adProducts.get(position);
		
		// check to see if checked
		//Toast.makeText(context, "ad Product Position checked: " + position + " : " + 
		//adProducts.get(position).isChecked(), Toast.LENGTH_SHORT).show();
        
		viewHolder.productName = (TextView) gridView.findViewById(R.id.productName);
        viewHolder.productPrice = (TextView) gridView.findViewById(R.id.price);
        viewHolder.imageView = (ImageView) gridView.findViewById(R.id.img);
        
        viewHolder.addSign = (CheckBox) gridView.findViewById(R.id.checkBox1);
        viewHolder.addSign.setTag(position);
        


        boolean checked = mCheckStates.get(position, false);
        viewHolder.addSign.setChecked(checked);

	    viewHolder.addSign.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	boolean isChecked = viewHolder.addSign.isChecked();
				mCheckStates.put((Integer) v.getTag(), isChecked);

				if (isChecked) {
					viewHolder.item.setChecked(true);
					LocalStorage.addToList(context, viewHolder.item);
				}
				else {
					viewHolder.item.setChecked(false);
					LocalStorage.removeFromList(context, viewHolder.item);
				}
				ButtonAnimation.animate(v);
	        }
	    });

	    viewHolder.imageView.setImageBitmap(adProducts.get(position).getAdPicture());
	    
	    viewHolder.imageView.setOnClickListener(new OnClickListener() {			 
			@Override
			public void onClick(View arg0) {			
				
                //boolean isChecked = viewHolder.addSign.isChecked();
				
				// make bundle and pass if it was checked or not
                Bundle b = viewHolder.item.makeBundle(viewHolder.productPosition,mCheckStates.get(viewHolder.productPosition));   
			    Intent intent = new Intent(context, AdItemActivity.class);
			    
			    
			    intent.putExtras(b);
                context.startActivity(intent);   
			}
 
		});
	    
	    viewHolder.productName.setText(adProducts.get(position).toString());
		
		WeeklyAdItem adItem = (WeeklyAdItem) adProducts.get(position);
		viewHolder.productPrice.setText(adItem.getPrice());
		
		return gridView;
	}
	
    public boolean isChecked(int position) {
        return mCheckStates.get(position, false);
    }

    public void setChecked(int position, boolean isChecked) {
        mCheckStates.put(position, isChecked);

    }

    public void toggle(int position) {
        setChecked(position, !isChecked(position));
    }
    
}
