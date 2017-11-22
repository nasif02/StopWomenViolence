package com.ewu.bug.swv;

import android.app.Application;
import android.content.Context;

/**
 * Created by Xplo on 1/4/2016.
 */
public class MainApplication extends Application {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

    }
}
