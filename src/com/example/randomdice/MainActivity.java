package com.example.randomdice;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
public class MainActivity extends Activity {

	private static String PREFS_EXTRA = "com.example.randomdice.PREFS";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent intent = new Intent(this, DiceSettings.class);
			startActivity(intent);
		}
		
		return super.onOptionsItemSelected(item);
	}

	public void rollDice(View view) {
		TextView result = (TextView) this.findViewById(R.id.diceResultTextView);
		
		Integer output = new Random().nextInt(19) + 1;
		result.setText(output.toString());
	}

}
