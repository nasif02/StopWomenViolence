package com.ewu.bug.swv.hotkey;

/**
 * Created by Xplo on 3/2/2016.
 */

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.ewu.bug.swv.MainActivity;
import com.ewu.bug.swv.utils.PfKey;
import com.ewu.bug.swv.utils.PrefUtils;

import java.util.Calendar;

public class HKService extends Service {

    BroadcastReceiver mReceiver;

    int totalPowerPressed = 0;

    long prevTime = Calendar.getInstance().getTimeInMillis();
    long thisTime;

    long MAX_TIME_DELAY = 1000;

    int count = 0;

    //EasyPref ep;

    @Override
    public void onCreate() {
        super.onCreate();
        // register receiver that handles screen on and screen off logic
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);

        filter.addAction(Intent.ACTION_SCREEN_OFF);
        mReceiver = new HKReceiver();
        registerReceiver(mReceiver, filter);


        //ep=new EasyPref(this);
    }

    @Override
    public void onDestroy() {

        unregisterReceiver(mReceiver);
        Log.i("xxx onDestroy Reciever", "Called");

        super.onDestroy();
    }

    @Override
    public void onStart(Intent intent, int startId) {

        //Log.d("HKService xxx", "OnStart");
        //boolean screenOn = intent.getBooleanExtra("screen_state", false);

//        totalPowerPressed++;
//        pToast("Total Pressed: " + totalPowerPressed);
//
//        ServiceTools.setKeyGuard(getApplicationContext(), false);
//        if(totalPowerPressed%4==0){
//
//            //ServiceTools.setKeyGuard(getApplicationContext(),false);
//
//            if(MainActivity.active==false){
//                Intent intent2 = new Intent(this, MainActivity.class);
//                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent2);
//            }else {
//                pToast("already running");
//            }
//
//
//        }

        jobHotKey();


//        if (!screenOn) {
//            Log.i("screenON", "Called");
//            Toast.makeText(getApplicationContext(), "Awake", Toast.LENGTH_LONG)
//                    .show();
//        } else {
//            Log.i("screenOFF", "Called");
//            // Toast.makeText(getApplicationContext(), "Sleep",
//            // Toast.LENGTH_LONG)
//            // .show();
//        }


    }

    private void startMainAct() {

        count=0;
        if (MainActivity.active == false) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            pToast("already running");
        }

    }

    private void jobHotKey() {

        Log.d("xxx","jobHotKey");
        //pToast("JobHotKey");

        String s = PrefUtils.getString(PfKey.pfHotKeySensivity, "1");
        int hfactor= Integer.valueOf(s);
        //hfactor=11;
        Log.d("xxx","hfactor: " + hfactor);

        //MAX_TIME_DELAY = MAX_TIME_DELAY * hfactor;

        thisTime = Calendar.getInstance().getTimeInMillis();
        Log.d("xxx", prevTime + " " + thisTime  + " : " + (thisTime-prevTime) + " count before: " +count);

        if (prevTime < thisTime) {
            //Check if times are within our max delay
            if ((thisTime - prevTime) <= MAX_TIME_DELAY * hfactor) {
                count++;
                Log.d("xxx", "count: " + count);
               // pToast("Count: " + count);

                if (count >= 2) {
                    Log.d("xxx", "count in: " + count);
                    //pToast("count in: " + count);
//                    Intent intent2 = new Intent(this, MainActivity.class);
//                    startActivity(intent2);
                    startMainAct();

                }

            } else {
                count = 0;
            }
        } else {
            count = 0;
        }
        prevTime = thisTime;
    }

//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        //handleCommand(intent);
//        // We want this service to continue running until it is explicitly
//        // stopped, so return sticky.
//        return START_STICKY;
//    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId)
//    {
//
//        if(intent == null || intent.getAction() == null)
//            return START_NOT_STICKY;
//
//        String action = intent.getAction();
//
//        if(action.equals(Intent.ACTION_SCREEN_OFF))
//        {
//            KeyguardManager.KeyguardLock k1;
//            KeyguardManager km =(KeyguardManager)getSystemService(KEYGUARD_SERVICE);
//            k1= km.newKeyguardLock("IN");
//            k1.disableKeyguard();
//        }
//
//        return START_NOT_STICKY;
//    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    private void pToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}
