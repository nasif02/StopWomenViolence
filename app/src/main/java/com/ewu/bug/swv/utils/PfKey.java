package com.ewu.bug.swv.utils;

import android.content.Context;

import com.ewu.bug.swv.MainApplication;
import com.ewu.bug.swv.R;


/**
 * Created by Xplo on 1/10/2016.
 */
public class PfKey {

    private final static String TAG = DbgUtils.getClassTag(PfKey.class.getSimpleName());
    private static Context mContext = MainApplication.getContext();
    private final static boolean isDbg = true;


    //settings
    public static final String pfIsSound = mContext.getResources().getString(R.string.pfIsSound);
    public static final String pfIsNightMode = mContext.getResources().getString(R.string.pfIsNightMode);

    public static final String pfTheme = mContext.getResources().getString(R.string.pfTheme);
    public static final String pfTextColor = mContext.getResources().getString(R.string.pfTextColor);
    public static final String pfAbout = mContext.getResources().getString(R.string.pfAbout);
    public static final String pfResetAll = mContext.getResources().getString(R.string.pfResetAll);
    public static final String pfDeveloper = mContext.getResources().getString(R.string.pfDeveloper);
    public static final String pfRate = mContext.getResources().getString(R.string.pfRate);
    public static final String pfShare = mContext.getResources().getString(R.string.pfShare);
    public static final String pfFeedback = mContext.getResources().getString(R.string.pfFeedback);
    public static final String pfDevSettings = mContext.getResources().getString(R.string.pfDevSettings);
    public static final String pfCatAdvanceSettings = mContext.getResources().getString(R.string.pfCatAdvanceSettings);

    //settings developer
    public static final String pfIsPro = mContext.getResources().getString(R.string.pfIsPro);
    public static final String pfIsAdRequestReal = mContext.getResources().getString(R.string.pfIsAdRequestReal);

    public static final String pfClearDSP = mContext.getResources().getString(R.string.pfClearDSP);
    public static final String pfClearMSP = mContext.getResources().getString(R.string.pfClearMSP);
    //public static final String pfResetALL = mContext.getResources().getString(R.string.pfResetALL);


    //settings swv
    public static final String pfProfile = mContext.getResources().getString(R.string.pfProfile);
    public static final String pfHotKey = mContext.getResources().getString(R.string.pfHotKey);
    public static final String pfShake = mContext.getResources().getString(R.string.pfShake);
    public static final String pfShakeType = mContext.getResources().getString(R.string.pfShakeType);
    public static final String pfHotKeySensivity = mContext.getResources().getString(R.string.pfHotKeySensivity);




}
