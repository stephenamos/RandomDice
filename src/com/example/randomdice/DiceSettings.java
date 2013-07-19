package com.example.randomdice;

import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class DiceSettings extends PreferenceActivity {

	private static EditTextPreference editTextPreference;
	private static Resources resources;
	
	static SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
		@Override
		public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
			if (key.equals("dieSidesPreference")) {
				Editor edit = sharedPreferences.edit();
				
				String dieFromPrefs = sharedPreferences.getString(key, "");

				if (dieFromPrefs.isEmpty() || Integer.parseInt(dieFromPrefs) <= 0) {
					edit.putString(key, "1");
					dieFromPrefs = "1";
					edit.commit();
					editTextPreference.setSummary(resources.getString(R.string.editTextPreferenceSummaryErrorLow));
				} else if (Integer.parseInt(dieFromPrefs) > 1000) {
					edit.putString(key, "1000");
					dieFromPrefs = "1000";
					edit.commit();
					editTextPreference.setSummary(resources.getString(R.string.editTextPreferenceSummaryErrorHigh));
				} else {
					editTextPreference.setSummary(resources.getString(R.string.editTextPreferenceSummary));
				}
				
				editTextPreference.setTitle(resources.getString(R.string.editTextPreferenceTitle) + sharedPreferences.getString("dieSidesPreference", ""));
			}
		}
	};
	
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

			editTextPreference = (EditTextPreference) findPreference("dieSidesPreference");
//			editTextPreference.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER); don't need this anymore since specified in preferences.xml
			resources = getActivity().getResources();
			
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());	
			prefs.registerOnSharedPreferenceChangeListener(listener);
			
			editTextPreference.setTitle(resources.getString(R.string.editTextPreferenceTitle) + prefs.getString("dieSidesPreference", ""));
		}
	}


}
