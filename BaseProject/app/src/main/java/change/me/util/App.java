package change.me.util;

import android.app.Application;

import change.me.BuildConfig;
import change.me.util.library.Utils;


public final class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this, BuildConfig.DEBUG);
    }

}
