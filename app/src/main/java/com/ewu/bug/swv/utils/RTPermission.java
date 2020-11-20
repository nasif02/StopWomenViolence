package com.ewu.bug.swv.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by Xplo on 11/22/2017.
 */

public class RTPermission {

//    private final int PRC_READ_CONTACTS = 11;
//    private final int PRC_ACCESS_FINE_LOCATION = 22;
//    private final int PRC_RECORD_AUDIO = 33;
//    private final int PRC_WRITE_EXTERNAL_STORAGE = 44;
//    private final int PRC_CALL_PHONE = 55;

    public static final int PRC_RECORD_AUDIO = 101;
    public static final int PRC_WRITE_EXTERNAL_STORAGE = 102;
    public static final int PRC_CAMERA = 103;
    public static final int PRC_CALL_PHONE = 104;
    public static final int PRC_ACCESS_FINE_LOCATION = 105;
    public static final int PRC_READ_CONTACTS = 106;


    Activity activity;

    public RTPermission(Activity activity) {
        this.activity = activity;
    }

    public boolean isPermissionForRecordGranted(){
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    public boolean isPermissionForExternalStorageGranted(){
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    public boolean isPermissionForCameraGranted(){
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    public boolean isPermissionForCallPhone(){
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    public boolean isPermissionForAccessFineLocation(){
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    public boolean isPermissionForReadContatcs(){
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }














    public void requestPermissionForRecord(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECORD_AUDIO)){
            Toast.makeText(activity, "Microphone permission needed for recording. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.RECORD_AUDIO}, PRC_RECORD_AUDIO);
        }
    }

    public void requestPermissionForExternalStorage(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Toast.makeText(activity, "External Storage permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PRC_WRITE_EXTERNAL_STORAGE);
        }
    }

    public void requestPermissionForCamera(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)){
            Toast.makeText(activity, "Camera permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.CAMERA}, PRC_CAMERA);
        }
    }



    private void pToast(String s) {
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
    }



}
