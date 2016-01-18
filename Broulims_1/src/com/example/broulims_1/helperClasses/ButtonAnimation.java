package com.example.broulims_1.helperClasses;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.CheckBox;

public class ButtonAnimation {
	private static CheckBox button;
	private static ObjectAnimator animation3;
	private static ObjectAnimator animation3_1;
	
	public static void animate(View view) {
		
		button = (CheckBox) view;
		if (button.isChecked()){
			button.setChecked(false);
		}
		else {
			button.setChecked(true);
		}
		animation3 = ObjectAnimator.ofFloat(button, "alpha",
			0);
		animation3.setDuration(300);
		animation3.start();
	    animation3.addListener(new AnimatorListenerAdapter() {

		      @Override
		      public void onAnimationEnd(Animator animation) {			    	  
				animation3_1 = ObjectAnimator.ofFloat(button, "alpha",
						1);
				animation3_1.setDuration(300);
				animation3_1.start();
				 if (!button.isChecked()) {
					  button.setChecked(true);
				  }
				 else {
					 button.setChecked(false);
				 }
					
		      }
		    });
	}
}
