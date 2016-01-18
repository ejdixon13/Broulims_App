package com.example.broulims_1;

import java.io.File;

import com.example.broulims_1.helperClasses.FirebaseData;
import com.example.broulims_1.helperClasses.LocalStorage;
import com.example.broulims_1.helperClasses.Store;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.os.Build;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;

public class MainActivity extends ActionBarActivity {

	//ImageView imgView;
	ImageView weeklySalesBtn;
	ImageView listBtn;
	ImageView locationBtn;
	Spinner stores;
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private ViewFlipper mViewFlipper;	
	private AnimationListener mAnimationListener;
	private Context mContext;
	
	@SuppressWarnings("deprecation")
	private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());
	final static String[] storeNames = {"Afton", "Alpine_Market", "Driggs", 
		"Montpelier", "Rexburg", "Rigby", "Shelly", "Soda_Springs", 
		"St_Anthony"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addListenerOnButton();
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		
		// view flipper code
		mContext = this;
		mViewFlipper = (ViewFlipper) this.findViewById(R.id.view_flipper);
		mViewFlipper.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(final View view, final MotionEvent event) {
				detector.onTouchEvent(event);
				return true;
			}
		});
		
		// sets auto flipping
		mViewFlipper.setAutoStart(true);
		mViewFlipper.setFlipInterval(8000);
		mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext,
				R.anim.right_in));
		mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,
				R.anim.right_out));
		// controlling animation
		mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
		mViewFlipper.showNext();
		mViewFlipper.startFlipping();
		
		// if a list doesn't exist create one
		if (LocalStorage.retrieveStore(this) == null) {
			LocalStorage.storeStore(this, new Store(storeNames[0]));
		}

		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	public void addListenerOnButton() {
		final Context context = this;
		
		// This adds listener for when our weekly Sales image is pressed
		weeklySalesBtn = (ImageView)findViewById(R.id.weekly_sales);
		
		weeklySalesBtn.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
 
				
			    Intent intent = new Intent(context, CategoryActivity.class);
                            startActivity(intent);   
 
			}
 
		});
		
		// This adds listener for when our list image is pressed
		listBtn = (ImageView)findViewById(R.id.shopping_list);
		
		listBtn.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
 
				
			    Intent intent = new Intent(context, Shopping.class);
                            startActivity(intent);   
 
			}
 
		});
		
		// This adds listener for when our locations image is pressed
		locationBtn = (ImageView)findViewById(R.id.locater);
		
		locationBtn.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
 
				
			    Intent intent = new Intent(context, Locations.class);
                            startActivity(intent);   
 
			}
 
		});
		stores = (Spinner) findViewById(R.id.stores);
        stores.setOnItemSelectedListener(
                new OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        LocalStorage.storeStore(context, new Store(storeNames[position]));
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        showToast("Spinner1: unselected");
                    }
                });

		
		
	}
	
    void showToast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    
    class SwipeGestureDetector extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			try {
				// right to left swipe
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
					mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
					// controlling animation
					mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
					mViewFlipper.showNext();
					return true;
				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
					mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.right_out));
					// controlling animation
					mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
					mViewFlipper.showPrevious();
					return true;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			return false;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
