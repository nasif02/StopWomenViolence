package com.ewu.bug.swv.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import com.ewu.bug.swv.MainApplication;
import com.ewu.bug.swv.R;

/**
 * Created by Xplo on 3/21/2016.
 */
public class MixTools {

    public static String getShakeType(String s) {
        int n = Integer.valueOf(s);
        //Log.d("xxx", "n: " + n);

        Context mContext = MainApplication.getContext();

        String sum = "none";
        switch (n) {
            case 14:
                sum = "Easy";
                sum = mContext.getString(R.string.bn_easy);
                break;
            case 18:
                sum = "Medium";
                sum = mContext.getString(R.string.bn_medium);
                break;
            case 25:
                sum = "Hard";
                sum = mContext.getString(R.string.bn_hard);
                break;
            case 30:
                sum = "Extreme";
                sum = mContext.getString(R.string.bn_extreme);
                break;


        }

        return sum;
    }
  public static String getHotKeySensivity(String s) {
        int n = Integer.valueOf(s);
        //Log.d("xxx", "n: " + n);

        Context mContext = MainApplication.getContext();

        String sum = "none";
        switch (n) {
            case 1:
                sum = "Basic";
                sum = mContext.getString(R.string.bn_basic);
                break;
            case 11:
                sum = "Extreme";
                sum = mContext.getString(R.string.bn_extreme);
                break;


        }

        return sum;
    }

    public static String getOwnerName(Context context) {
        String s=null;
        Cursor c = null;


        try {
            //this code is for getting username from contacts profile
            c = context.getContentResolver().query(ContactsContract.Profile.CONTENT_URI, null, null, null, null);
            c.moveToFirst();
            s = c.getString(c.getColumnIndex("display_name"));

        } catch (Exception e) {

            //Toast.makeText(this, "No username found", Toast.LENGTH_SHORT).show();
            Log.d("xxx","No username found");
        } finally {

            if(c!=null){
                c.close();
            }


        }



        return s;
    }



}
