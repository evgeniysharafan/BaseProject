package change.me.ui.fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.evgeniysharafan.utils.Res;
import com.evgeniysharafan.utils.Utils;

import change.me.R;
import change.me.ui.activity.DebugSettingsActivity;

public class SettingsFragment extends PreferenceFragment {

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
        setHasOptionsMenu(true);
        addDebugSettingsIfNeeded();
    }

    private void addDebugSettingsIfNeeded() {
        if (Utils.isDebug()) {
            Preference debugSettings = new Preference(getActivity());
            debugSettings.setKey(Res.getString(R.string.key_debug_settings));
            debugSettings.setTitle(R.string.title_debug_settings);
            debugSettings.setIntent(DebugSettingsActivity.getIntent(getActivity(),
                    "evgeniysharafan@gmail.com"));
            debugSettings.setSummary(R.string.summary_debug_settings);
            getPreferenceScreen().addPreference(debugSettings);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        prepareActionBar();
        return view;
    }

    private void prepareActionBar() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.action_settings);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
