package io.sixth.atten.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by walle on 02/02/15.
 */
public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
