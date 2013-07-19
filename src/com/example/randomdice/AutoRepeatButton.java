package com.example.randomdice;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class AutoRepeatButton extends Button {

	//  private long initialRepeatDelay = 500;
	private long initialRepeatDelay = 0;
	private long repeatIntervalInMilliseconds = 50;

	private Runnable repeatClickWhileButtonHeldRunnable = new Runnable() {
		@Override
		public void run() {
			//Perform the present repetition of the click action provided by the user
			// in setOnClickListener().
			performClick();

			//Schedule the next repetitions of the click action, using a faster repeat
			// interval than the initial repeat delay interval.
			postDelayed(repeatClickWhileButtonHeldRunnable, repeatIntervalInMilliseconds);
		}
	};

	private void commonConstructorCode() {
		this.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction(); 
				if(action == MotionEvent.ACTION_DOWN) 
				{
					v.setPressed(true);
					
					//Just to be sure that we removed all callbacks, 
					// which should have occurred in the ACTION_UP
					removeCallbacks(repeatClickWhileButtonHeldRunnable);

					//Perform the default click action.
					performClick();

					//Schedule the start of repetitions after a one half second delay.
					postDelayed(repeatClickWhileButtonHeldRunnable, initialRepeatDelay);
				}
				else if(action == MotionEvent.ACTION_UP) {
					v.setPressed(false);
					
					//Cancel any repetition in progress.
					removeCallbacks(repeatClickWhileButtonHeldRunnable);
				}

				//Returning true here prevents performClick() from getting called 
				// in the usual manner, which would be redundant, given that we are 
				// already calling it above.
				return true;
			}
		});
	}

	public AutoRepeatButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		commonConstructorCode();
	}


	public AutoRepeatButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		commonConstructorCode();
	}

	public AutoRepeatButton(Context context) {
		super(context);
		commonConstructorCode();
	}
}