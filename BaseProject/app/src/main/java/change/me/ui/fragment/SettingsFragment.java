package change.me.ui.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import change.me.R;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }

}
