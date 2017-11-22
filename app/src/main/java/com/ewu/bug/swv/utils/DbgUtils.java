package com.ewu.bug.swv.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.Toast;

import com.ewu.bug.swv.MainApplication;


/**
 * Created by Xplo on 1/4/2016.
 */
public class DbgUtils {

    private static String className = DbgUtils.class.getSimpleName();
    private final static String TAG = className+ " " + AData.DEF_TAG_POSTFIX;


    public static void pToast(String s) {
        Toast.makeText(MainApplication.getContext(), s, Toast.LENGTH_SHORT).show();
    }
    public static void pToast(String s, String tag) {
        Log.d(tag, s);
        Toast.makeText(MainApplication.getContext(), s, Toast.LENGTH_SHORT).show();
    }


    public static void pToast(String s, int n) {

        if (n == 0)
            Toast.makeText(MainApplication.getContext(), s, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainApplication.getContext(), s, Toast.LENGTH_LONG).show();

    }

    public void pToast(String text, Typeface tf) {

        Toast.makeText(MainApplication.getContext(), text, Toast.LENGTH_SHORT)
                .show();
    }

    public static String getClassTag(String className) {
        String tag = className + " " + AData.DEF_TAG_POSTFIX;
        return tag;

    }

    public static String getClassTag(Context context) {
        String className = context.getClass().getSimpleName();
        String tag = className + " " + AData.DEF_TAG_POSTFIX;
        return tag;
    }


}
