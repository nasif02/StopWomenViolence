package com.ewu.bug.swv.shake;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.ewu.bug.swv.MainActivity;
import com.ewu.bug.swv.utils.PfKey;
import com.ewu.bug.swv.utils.PrefUtils;

/**
 * Created by Xplo on 3/19/2016.
 */
public class ShakeService extends Service implements AccelerometerListener
{
    boolean flag=false;
    int shakeCount = 0;
    Context mContext;

    @Override
    public IBinder onBind(Intent intent)
    {

        return null;
    }
    public void onCreate()
    {
        flag=true;
        //Log.d(MainShake.TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy()
    {
        flag=false;
        //Log.d(MainShake.TAG, "onDestroy");
        //super.onDestroy();


        if (AccelerometerManager.isListening()) {

            // Start Accelerometer Listening
            AccelerometerManager.stopListening();

            // Toast.makeText(getBaseContext(),
            // TAG + "onPuse Accelerometer Stoped", Toast.LENGTH_SHORT)
            // .show();
        }
    }




    public void onStart(Intent intent, int startId)
    {

        Log.d("xxx", "onStart");
        //sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        //lastUpdate = System.currentTimeMillis();

        // Check device supported Accelerometer senssor or not
        if (AccelerometerManager.isSupported(this)) {

            Log.d("xxx","AccelerometerManager is Supported");
            // Start Accelerometer Listening
            AccelerometerManager.startListening(this);
        }else {
            Log.d("xxx","AccelerometerManager is not Supported");
        }



    }





    private void shakeJob() {

        shakeCount++;
        //pToast("shake :" + shakeCount);
        Log.d("xxx", "shake :" + shakeCount);

        if(MainActivity.active==false){
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else {
            pToast("already running");
        }



    }



    protected void onResume()
    {
        // register this class as a listener for the orientation and
        // accelerometer sensors
        //sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        // Check device supported Accelerometer senssor or not
        if (AccelerometerManager.isSupported(this)) {

            // Start Accelerometer Listening
            AccelerometerManager.startListening(this);
        }

    }


    protected void onPause()
    {
        Log.d("xxx","onPause");
        // unregister listener
        //sensorManager.unregisterListener(this);

        if (AccelerometerManager.isListening()) {

            // Start Accelerometer Listening
            AccelerometerManager.stopListening();

            // Toast.makeText(getBaseContext(),
            // TAG + "onPuse Accelerometer Stoped", Toast.LENGTH_SHORT)
            // .show();
        }

    }

    private void pToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAccelerationChanged(float x, float y, float z) {

    }

    @Override
    public void onShake(float force) {

        pToast("onShake Force: " + force);
        Log.d("xxx", "onShake Force: " + force);

        String s = PrefUtils.getString(PfKey.pfShakeType,"14");
        float f = Float.valueOf(s);
        Log.d("xxx", "f: " + f);

        if(force>=f){
            shakeJob();
        }


    }
}
