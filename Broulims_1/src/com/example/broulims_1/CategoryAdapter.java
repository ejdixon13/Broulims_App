package com.example.broulims_1;

import java.util.List;

import com.example.broulims_1.helperClasses.ProduceAdItem;
import com.example.broulims_1.helperClasses.ShopList;

import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CategoryAdapter extends ArrayAdapter<String> {
	 private final Context context;
	 private final String[] categories;
	 private static boolean rowColor;
	public CategoryAdapter(Activity context, String[] categories) {
		super(context, R.layout.category_single, categories);
		this.context = context;
		this.categories = categories;
		
		//used for alternating row colors
		this.rowColor = false;
		
	}
	
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.category_single, parent, false);
	    TextView textView = (TextView) rowView.findViewById(R.id.categoryBlock);
	    
	    textView.setText(categories[position]);
	    // change the icon for Windows and iPhone
	    String category = categories[position];

	    if (category.equals("All")) {
	      rowView.setBackgroundColor(rowView.getResources().getColor(R.color.reddish));
	    } else {
	    	if (rowColor){
	    		rowView.setBackgroundColor(rowView.getResources().getColor(R.color.greenish));
	    		rowColor = !rowColor;
	    	}
	    	else {
	    		rowColor = !rowColor;
	    	}
	    }

	    return rowView;
	  }
}
