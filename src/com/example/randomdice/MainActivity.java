package com.example.randomdice;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends Activity {

	private static String PREFS_EXTRA = "com.example.randomdice.PREFS";
	private static ArrayList<Integer> dice = new ArrayList<Integer>();
	private static Integer lastRollValue = 0;
	

//	private final Runnable mUpdateUITimerTask = new Runnable() {
//	    public void run() {
//			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//			String dieFromPrefs = preferences.getString("dieSidesPreference", "20");
//
//			if (dieFromPrefs.isEmpty()) {
//				dieFromPrefs = "1";
//			}
//			diceResultTextView.setText("Second text to display!");
//	    }
//	};
//	private final Handler mHandler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		mHandler.postDelayed(mUpdateUITimerTask, 10 * 1000);
		
		setContentView(R.layout.activity_main);

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String dieFromPrefs = preferences.getString("dieSidesPreference", "20");

		if (dieFromPrefs.isEmpty()) {
			dieFromPrefs = "1";
		}
		
		TextView diceResultTextView = (TextView) findViewById(R.id.diceResultTextView);
		TextView diceValueTextView = (TextView) findViewById(R.id.diceValueTextView);
		diceValueTextView.setText(dieFromPrefs);
		diceResultTextView.setText("");
		
		if (dice.isEmpty()) {
			dice.add(0, Integer.parseInt(dieFromPrefs));	
		} else {
			dice.set(0, Integer.parseInt(dieFromPrefs));
		}

		Boolean checkBoxSetting = preferences.getBoolean("checkbox", false);

		Button rollButton = (Button) this.findViewById(R.id.rollDiceButton);
		AutoRepeatButton autoRollButton = (AutoRepeatButton) findViewById(R.id.autoRollDiceButton);
		if (checkBoxSetting) {
			rollButton.setVisibility(View.GONE);
			rollButton.setEnabled(false);
			autoRollButton.setVisibility(View.VISIBLE);
			autoRollButton.setEnabled(true);
		} else {
			rollButton.setVisibility(View.VISIBLE);
			rollButton.setEnabled(true);
			autoRollButton.setVisibility(View.GONE);
			autoRollButton.setEnabled(false);
		}

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		setContentView(R.layout.activity_main);

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String dieFromPrefs = preferences.getString("dieSidesPreference", "20");

		if (dieFromPrefs.isEmpty()) {
			dieFromPrefs = "1";
		}

		TextView diceResultTextView = (TextView) findViewById(R.id.diceResultTextView);
		TextView diceValueTextView = (TextView) findViewById(R.id.diceValueTextView);
		diceValueTextView.setText(dieFromPrefs);
		diceResultTextView.setText(lastRollValue.toString());
		
		if (dice.isEmpty()) {
			dice.add(0, Integer.parseInt(dieFromPrefs));	
		} else {
			dice.set(0, Integer.parseInt(dieFromPrefs));
		}

		Boolean checkBoxSetting = preferences.getBoolean("checkbox", false);

		Button rollButton = (Button) this.findViewById(R.id.rollDiceButton);
		AutoRepeatButton autoRollButton = (AutoRepeatButton) findViewById(R.id.autoRollDiceButton);
		if (checkBoxSetting) {
			rollButton.setVisibility(View.GONE);
			rollButton.setEnabled(false);
			autoRollButton.setVisibility(View.VISIBLE);
			autoRollButton.setEnabled(true);
		} else {
			rollButton.setVisibility(View.VISIBLE);
			rollButton.setEnabled(true);
			autoRollButton.setVisibility(View.GONE);
			autoRollButton.setEnabled(false);
		}
		
	}

	@Override
	public void onPause() {
		super.onPause();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent intent = new Intent(this, DiceSettings.class);
			startActivity(intent);
			return true;
		case R.id.action_exit:
			finish();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}


	public void rollDice(View view) {
		TextView diceResultTextView = (TextView) findViewById(R.id.diceResultTextView);
		
		for (Integer dieValue : dice) {
			Integer output = new Random().nextInt(dieValue) + 1;
			diceResultTextView.setText(output.toString());
			lastRollValue = Integer.parseInt(output.toString());
		}
	}

}
