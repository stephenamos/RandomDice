package com.example.randomdice;

import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.text.InputType;

public class DiceSettings extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		PrefFrag p = new PrefFrag();
		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		fragmentTransaction.replace(android.R.id.content, p);
		
		fragmentTransaction.commit();
	}

	public static class PrefFrag extends PreferenceFragment {
		
		@Override public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			addPreferencesFromResource(R.xml.preferences);	
			
			EditTextPreference editTextPreference = (EditTextPreference) findPreference("dieSidesPreference");
			editTextPreference.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
		}
	}

	
}
