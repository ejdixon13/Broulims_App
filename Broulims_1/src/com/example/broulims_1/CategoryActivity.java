package com.example.broulims_1;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

public class CategoryActivity extends ListActivity {
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    String[] categories = new String[] { "All", "Dairy", "Frozen Foods",
        "Bread/Bakery", "Meat", "Produce", "Canned Goods", "Dry/Baking Goods",
        "Paper Goods", "Cleaners", "Personal Care", "Other" };
	CategoryAdapter adapter = new CategoryAdapter(CategoryActivity.this, categories);
    setListAdapter(adapter);
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    String item = (String) getListAdapter().getItem(position);
    
    Intent intent = new Intent(this, AdActivity.class);
    Bundle b = new Bundle();
    b.putString("category", item);
    b.putInt("position", position);
    intent.putExtras(b);
    startActivity(intent);  
    
  }
} 