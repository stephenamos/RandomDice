package com.example.randomdice;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
public class MainActivity extends Activity {

	private static String PREFS_EXTRA = "com.example.randomdice.PREFS";
	private static ArrayList<Integer> dice = new ArrayList<Integer>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String dieFromPrefs = preferences.getString("dieSidesPreference", "20");
		
		if (dieFromPrefs.isEmpty()) {
			dieFromPrefs = "1";
		}
		
		TextView diceValueTextView = (TextView) findViewById(R.id.diceValueTextView);
		diceValueTextView.setText(dieFromPrefs);
		
		if (dice.isEmpty()) {
			dice.add(0, Integer.parseInt(dieFromPrefs));	
		} else {
			dice.set(0, Integer.parseInt(dieFromPrefs));
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
		
		TextView diceValueTextView = (TextView) findViewById(R.id.diceValueTextView);
		diceValueTextView.setText(dieFromPrefs);
		
		if (dice.isEmpty()) {
			dice.add(0, Integer.parseInt(dieFromPrefs));	
		} else {
			dice.set(0, Integer.parseInt(dieFromPrefs));
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
		TextView result = (TextView) this.findViewById(R.id.diceResultTextView);

		for (Integer dieValue : dice) {
			Integer output = new Random().nextInt(dieValue) + 1;
			result.setText(output.toString());
		}
	}

}
