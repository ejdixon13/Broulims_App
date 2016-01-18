package com.example.broulims_1;

import java.util.ArrayList;









import com.example.broulims_1.CustomList.ViewHolderItem;
import com.example.broulims_1.helperClasses.Item;
import com.example.broulims_1.helperClasses.LocalStorage;
import com.example.broulims_1.helperClasses.ShopList;
import com.example.broulims_1.helperClasses.WeeklyAdItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class Shop_View extends ArrayAdapter<Item> {
  private final Context context;
  private final ShopList values;
  
//  ImageView addQuant;
//  ImageView subQuant;

  public Shop_View(Context context, ShopList values) {
    super(context, R.layout.list_layout, values);
    this.context = context;
    this.values = values;
  }

	// our ViewHolder.
	// caches our view
	static class ViewHolderItem {
		TextView textView;
		TextView textView1;
		TextView productQuantity;
		ImageView addQuant;
		ImageView subQuant;
	}
  @Override
  public View getView(final int position, View rowView, ViewGroup parent) {
	  
		final ViewHolderItem viewHolder;
		
		if(rowView == null){
	         
		    LayoutInflater inflater = (LayoutInflater) context
		            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		        rowView = inflater.inflate(R.layout.list_layout, parent, false);
	         
	        // well set up the ViewHolder
	        viewHolder = new ViewHolderItem();

	        // store the holder with the view.
	        rowView.setTag(viewHolder);
	         
	    }else{
	        // we've just avoided calling findViewById() on resource everytime
	        // just use the viewHolder
	        viewHolder = (ViewHolderItem) rowView.getTag();
	    }

    viewHolder.textView = (TextView) rowView.findViewById(R.id.itemLine);
    viewHolder.textView1 = (TextView) rowView.findViewById(R.id.priceLine);
    viewHolder.productQuantity = (TextView) rowView.findViewById(R.id.itemQuantity1);
    viewHolder.productQuantity.setText(values.get(position).getQuantity().toString());
    viewHolder.textView.setText(values.get(position).getItem());
    viewHolder.textView1.setText(values.get(position).getPrice().toString());
    viewHolder.productQuantity.setTag(position);
    
    viewHolder.addQuant = (ImageView) rowView.findViewById(R.id.imageButtonAdd1);
    viewHolder.subQuant = (ImageView) rowView.findViewById(R.id.imageButtonSub1);
    
    viewHolder.addQuant.setOnClickListener(new OnClickListener() {

        public void onClick(View v) {
        ShopList list = LocalStorage.retrieveList(context);
        Integer newQuantity = list.get(position).getQuantity() + 1;
        list.get(position).setQuantity(newQuantity);
        values.get(position).setQuantity(newQuantity);
        
        viewHolder.productQuantity.setText(newQuantity.toString());
        
        LocalStorage.storeList(context, list);
    }
    });
    
   viewHolder.subQuant.setOnClickListener(new OnClickListener() {

        public void onClick(View v) {
        ShopList list = LocalStorage.retrieveList(context);
        Integer quantity = list.get(position).getQuantity();
        Integer newQuantity = quantity;
        if (quantity > 1) {
	        list.get(position).setQuantity(quantity - 1);
	        values.get(position).setQuantity(quantity - 1);
	        newQuantity = quantity - 1;
        }
        viewHolder.productQuantity.setText(newQuantity.toString());
        LocalStorage.storeList(context, list);
    }
    });

    
    CheckBox cBox = (CheckBox) rowView.findViewById(R.id.checkBox1); // your
    // CheckBox
    cBox.setOnClickListener(new OnClickListener() {

        public void onClick(View v) {

        CheckBox cb = (CheckBox) v.findViewById(R.id.checkBox1);
        ShopList list = LocalStorage.retrieveList(context);
        if (cb.isChecked()) {
            values.get(position).setChecked(true);
            list.get(position).setChecked(true);
            
        } else if (!cb.isChecked()) {
            values.get(position).setChecked(false);
            list.get(position).setChecked(false);
        }
        LocalStorage.storeList(context, list);
    }
    });
    cBox.setChecked(values.get(position).isChecked());
    return rowView;
  }
} 