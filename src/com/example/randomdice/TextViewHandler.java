package com.example.randomdice;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class TextViewHandler extends Activity {

	private Handler mHandler;
	private TextView text;
	private int i;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		text = (TextView) findViewById(R.id.diceValueTextView);
		i = 0;
		mHandler = new Handler();
		mHandler.post(mUpdate);

	}

	private Runnable mUpdate = new Runnable() {
		public void run() {

			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
			String dieFromPrefs = preferences.getString("dieSidesPreference", "20");

			if (dieFromPrefs.isEmpty()) {
				dieFromPrefs = "1";
			}

			text.setText(dieFromPrefs);
			mHandler.postDelayed(this, 1000);
		}
	};}