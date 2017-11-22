package com.ewu.bug.swv.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.ewu.bug.swv.MainApplication;


/**
 * Created by Xplo on 1/3/2016.
 */
public class PrefUtils {

//    private static String className = PrefUtils.class.getSimpleName();
//    private final static String TAG = className+ " " + AData.DEF_TAG_POSTFIX;

    private final static String TAG = DbgUtils.getClassTag(PrefUtils.class.getSimpleName());
    private static Context mContext = MainApplication.getContext();
    private final static boolean isDbg = true;


    //boolean variable key
    public static final String IS_FIRST_OPEN = "IS_FIRST_OPEN";
    public static final String IS_INTERSTIAL_ENABLED = "IS_INTERSTIAL_ENABLED";

    public static final String IS_SOUND_ENABLED = "IS_SOUND_ENABLED";
    public static final String IS_PRO = "IS_PRO";
    public static final String AD_VIEW_MODE = "AD_VIEW_MODE";

    //int or long
    public static final String LAUNCH_COUNTER = "LAUNCH_COUNTER";
    public static final String DONT_SHOW_AGAIN = "DONT_SHOW_AGAIN";
    public static final String DATE_FIRST_LAUNCH = "DATE_FIRST_LAUNCH";


    public static final String ADV_1 = "ADV_1";
    public static final String ADV_2 = "ADV_2";

    public static final String USER_NAME = "USER_NAME";
    public static final String USER_NO = "USER_NO";



    //system code
    private static final String PREF_NAME = "Default";

    //private static SharedPreferences sp = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);   //custom
    private static SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);    //default
    //private static SharedPreferences.Editor ed = sp.edit();


    public static void putInt(String key, int value) {

        SharedPreferences.Editor ed = sp.edit();
        ed.putInt(key, value);
        ed.apply();

    }

    public static void putLong(String key, long value) {

        SharedPreferences.Editor ed = sp.edit();
        ed.putLong(key, value);
        ed.apply();

    }

    public static void putFloat(String key, float value) {

        SharedPreferences.Editor ed = sp.edit();
        ed.putFloat(key, value);
        ed.apply();

    }

    public static void putString(String key, String value) {

        SharedPreferences.Editor ed = sp.edit();
        ed.putString(key, value);
        ed.apply();

    }

    public static void putBoolean(String key, boolean value) {

        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean(key, value);
        ed.apply();

    }


    public static int getInt(String key, int defValue) {

        return sp.getInt(key, defValue);
    }

    public static long getLong(String key, long defValue) {

        return sp.getLong(key, defValue);
    }

    public static boolean getBoolean(String key, boolean defValue) {

        return sp.getBoolean(key, defValue);
    }

    public static float getFloat(String key, float defValue) {

        return sp.getFloat(key, defValue);
    }

    public static String getString(String key, String defValue) {

        return sp.getString(key, defValue);
    }

    public static void remove(String key) {

        SharedPreferences.Editor ed = sp.edit();

        try {
            ed.remove(key);
            ed.apply();
            Log.d(TAG, key + " removed successfully");
        } catch (Exception e) {
            Log.e(TAG, key + " not removed successfully");
            e.printStackTrace();
        }

    }

    public static void clear() {

        SharedPreferences.Editor ed = sp.edit();
        try {
            ed.clear();
            //ed.commit();
            ed.apply();
            Log.d(TAG, PREF_NAME + " Preference cleared successfully");
        } catch (Exception e) {
            Log.e(TAG, PREF_NAME + " Preference not cleared successfully");
            e.printStackTrace();

        }
    }

    private void dbg(String s) {

        if (isDbg) {
            Log.d(TAG, s);
        }

    }


}
