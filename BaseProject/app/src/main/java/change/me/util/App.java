package change.me.util;

import android.app.Application;

import com.evgeniysharafan.utils.Utils;

import change.me.BuildConfig;


public final class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this, BuildConfig.DEBUG);
    }

}
