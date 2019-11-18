package com.example.tictactoe;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
