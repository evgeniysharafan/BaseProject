package change.me.util;

import android.app.Application;
import android.preference.PreferenceManager;

import com.evgeniysharafan.utils.Utils;

import change.me.BuildConfig;
import change.me.R;

public final class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this, BuildConfig.DEBUG);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

}
