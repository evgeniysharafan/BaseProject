package change.me.util;

import android.preference.PreferenceManager;
import android.support.multidex.MultiDexApplication;

import com.evgeniysharafan.utils.Utils;

import change.me.BuildConfig;
import change.me.R;

public final class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this, BuildConfig.DEBUG);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

}
