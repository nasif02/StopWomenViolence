package com.ewu.bug.swv.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.KeyguardManager;
import android.content.Context;

import java.util.List;

/**
 * Created by Xplo on 3/2/2016.
 */
public class ServiceTools {

    public static boolean isMyServiceRunning(Context context,Class<?> serviceClass) {

        //this
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<RunningServiceInfo> alServices = activityManager.getRunningServices(Integer.MAX_VALUE);
        String serviceClassName=serviceClass.getName();

        for (RunningServiceInfo runningServiceInfo : alServices) {
            if (runningServiceInfo.service.getClassName().equals(serviceClassName)){
                return true;
            }
        }
        return false;
    }

    public static void setKeyGuard(Context context, boolean b){

        //getApplicatiionContext
        KeyguardManager _guard = (KeyguardManager) context
                .getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock _keyguardLock = _guard
                .newKeyguardLock("KeyguardLockWrapper");

        if(b){
           // _keyguardLock.reenableKeyguard();
        }else {
            _keyguardLock.disableKeyguard();
        }


    }


//
//    public static boolean isServiceRunning(String serviceClassName){
//        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        final List<RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE);
//
//        for (RunningServiceInfo runningServiceInfo : services) {
//            if (runningServiceInfo.service.getClassName().equals(serviceClassName)){
//                return true;
//            }
//        }
//        return false;
//    }
}
