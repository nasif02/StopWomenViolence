package com.ewu.bug.swv;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.ewu.bug.swv.hotkey.HKService;
import com.ewu.bug.swv.model.GPSLoc;
import com.ewu.bug.swv.shake.ShakeService;
import com.ewu.bug.swv.utils.GPSTracker;
import com.ewu.bug.swv.utils.MixTools;
import com.ewu.bug.swv.utils.NetworkTools;
import com.ewu.bug.swv.utils.PfKey;
import com.ewu.bug.swv.utils.PrefUtils;
import com.ewu.bug.swv.utils.RTPermission;
import com.ewu.bug.swv.utils.ServiceTools;
import com.karan.churi.PermissionManager.PermissionManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;
import xplo.library.ekush.MyBanglaSupport;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    // LogCat tag
    private static final String TAG = MainActivity.class.getSimpleName() + " xxx";

    public static boolean active = false;


    // Camera activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    private Uri fileUri; // file url to store image/video

//    private Button btnCapturePicture;
//    private Button  btnRecordVideo;

    private Button btHotKey;
    private Button btCall;
    //private ImageButton ibtCall;
    private Button btMore;
    ImageView ivBanner;

    Typeface tf = null;

    RequestQueue requestQueue;

    //EasyPref ep;


    GPSTracker gps;
    GPSLoc mGpsLoc;

    private static final String SHOWCASE_ID = "showcase_id";

    String flag = "xxx";

    private final int PRC_READ_CONTACTS = 11;
    private final int PRC_ACCESS_FINE_LOCATION = 22;
    private final int PRC_RECORD_AUDIO = 33;
    private final int PRC_WRITE_EXTERNAL_STORAGE = 44;
    private final int PRC_CALL_PHONE = 55;


    private final String PHONE_NO = "10921";

    PermissionManager permissionManager;
    RTPermission rtPermission;

    String mCurrentPhotoPath;
    static final int RC_CPATURE_PHOTO = 11;
    static final int RC_CPATURE_VIDEO = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        String s1 = PrefUtils.getString(PrefUtils.USER_NAME, "");
//        String s2 = PrefUtils.getString(PrefUtils.USER_NO, "");
//
//        pToast(s1 + " " + s2);

        try {
            flag = getIntent().getStringExtra("flag");
            //pToast(flag);
            Log.d(TAG, flag + " get successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, flag + "getting error");
        }

        if (flag == null) {
            flag = "xxx";
        }


//        permissionManager=new PermissionManager() {};
//        permissionManager.checkAndRequestPermissions(this);


//        captureImage();

        //ep = new EasyPref(this);

        btHotKey = (Button) findViewById(R.id.btHotKey);
        btCall = (Button) findViewById(R.id.btCall);
        //ibtCall = (ImageButton) findViewById(R.id.btCall);
        btMore = (Button) findViewById(R.id.btMore);
        ivBanner = findViewById(R.id.ivBanner);

        tf = Typeface.createFromAsset(getAssets(),
                getString(R.string.font_bn));

        btHotKey.setTypeface(tf);
        btCall.setTypeface(tf);
        btMore.setTypeface(tf);

        btHotKey.setOnLongClickListener(this);

        btHotKey.setOnClickListener(this);
        btCall.setOnClickListener(this);
        btMore.setOnClickListener(this);


        if (flag.equals("image")) {
            captureImage();
            return;
        } else if (flag.equals("video")) {
            captureVideo();
            return;
        }

        presentShowcaseSequence();

        firstOpen();

        getAllPermission();
        rtPermission = new RTPermission(this);


        // Checking camera availability
        if (!isDeviceSupportCamera()) {
            pToast("Sorry! Your device doesn't support camera");
            finish();
        }

        //this.startService(new Intent(this, HKService.class));
        //ServiceTools.setKeyGuard(getApplicationContext(), false);
        //ServiceTools.setKeyGuard(getApplicationContext(), false);


//        s1 = PrefUtils.getString(PrefUtils.USER_NAME, "");
//        s2 = PrefUtils.getString(PrefUtils.USER_NO, "");
//
//        pToast(s1 + " " + s2);

        updatePrefs();


//        Intent intent = new Intent(this, HKService.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        this.startService(intent);

        //presentShowcaseSequence();


//        s1 = PrefUtils.getString(PrefUtils.USER_NAME, "");
//        s2 = PrefUtils.getString(PrefUtils.USER_NO, "");
//
//        pToast(s1 + " " + s2);

        if (isGpsEnabled()) {

            pToast("GPS Enabled");
        } else {
            pToast("Please Enable GPS");
            openGPS();
        }


    }




    /**
     * launch camara app to capture image
     */
    private void captureImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {

                String authorities = this.getApplicationContext().getPackageName() + ".provider";

                Log.d(TAG,"authorities: " + authorities);
                //pToast(authorities);

                Uri photoURI = FileProvider.getUriForFile(this, authorities, photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, RC_CPATURE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


    /**
     * Receiving activity result method will be called after closing the camera
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        switch (requestCode) {

            case RC_CPATURE_PHOTO:

                if (resultCode == RESULT_OK) {
                    //here data null
                    //pToast("photo saved in: " + mCurrentPhotoPath);

//                    Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
//                    ivBanner.setImageBitmap(bitmap);

                    launchUploadActivity(true);
                } else if (resultCode == RESULT_CANCELED) {
                    pToast("User cancelled image capture");
                } else {
                    pToast("Sorry! Failed to capture image");
                }

                return;

            case  RC_CPATURE_VIDEO:
                return;
        }

//        if (requestCode == RC_CPATURE_PHOTO && resultCode == RESULT_OK) {
//
//            //here data null
//            pToast("photo saved in: " + mCurrentPhotoPath);
//
////            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
////            ivBanner.setImageBitmap(bitmap);
//
//            launchUploadActivity(true);
//
//
//        } else if (resultCode == RESULT_CANCELED) {
//            pToast("User cancelled image capture");
//        } else {
//            pToast("Sorry! Failed to capture image");
//        }




         if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // video successfully recorded
                // launching upload activity
                launchUploadActivity(false);

            } else if (resultCode == RESULT_CANCELED) {

                // user cancelled recording
                Toast.makeText(getApplicationContext(),
                        "User cancelled video recording", Toast.LENGTH_SHORT)
                        .show();

            } else {
                // failed to record video
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to record video", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }


    private void getAllPermission() {


        permissionManager = new PermissionManager() {
        };
        permissionManager.checkAndRequestPermissions(this);

//        askPermission(Manifest.permission.ACCESS_FINE_LOCATION,PRC_ACCESS_FINE_LOCATION,"");
//        askPermission(Manifest.permission.READ_CONTACTS,PRC_READ_CONTACTS,"");
//        askPermission(Manifest.permission.RECORD_AUDIO,PRC_RECORD_AUDIO,"");
//        askPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,PRC_WRITE_EXTERNAL_STORAGE,"");
    }


    @Override
    protected void onResume() {
        super.onResume();

        Log.d("xxx", "onResume");


    }

    @Override
    protected void onNewIntent(Intent intent) {
        //called when the activity is relaunched by a new intent
        Log.d("xxx", "On New Intent");
        super.onNewIntent(intent);
        setIntent(intent);
        Log.d("xxx", intent.toString());

        //pToast("On New Intent");
    }



    private void dialWithPermission() {

        if (isPermissionAvailable(Manifest.permission.CALL_PHONE)) {
            dial(PHONE_NO);
        } else {
            askPermission(Manifest.permission.CALL_PHONE, PRC_CALL_PHONE, "Call phone permission is needed to make call");
        }

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//
//            //permission already available
//            dial(PHONE_NO);
//
//        } else {
//
//            //permission not available yet
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
//                pToast("Call phone permission is needed to make call");
//            }
//
//            //pToast("Permission Request");
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PRC_CALL_PHONE);
//
//        }

    }


    private void askPermission(String permission, int requestCode, String explanation) {
        if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            //permission already available
            pToast("permission already available");
        } else {
            //pToast("here");
            //permission not available yet
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                pToast(explanation);
            }
            //pToast("Permission Request");
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    private boolean isPermissionAvailable(String permission) {
        if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            //permission already available
            return true;
        }
        //permisiion not available
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {

            case PRC_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay!
                    dial(PHONE_NO);

                } else {

                    pToast("permission denied");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void dial(String no) {
        // TODO Auto-generated method stub

        if (no != null) {

            String number = "tel:" + no;
            Intent callIntent = new Intent(Intent.ACTION_CALL,
                    Uri.parse(number));
            // Intent callIntent = new Intent(Intent.ACTION_DIAL,
            // Uri.parse(number));
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }


            startActivity(callIntent);

        } else {

            String userWarning = "no no";

            Toast.makeText(getApplicationContext(), userWarning,
                    Toast.LENGTH_LONG).show();

        }
    }

    private void firstOpen() {

        //pToast(getString(R.string.bn_know_more_jana));


        if (PrefUtils.getBoolean(PrefUtils.IS_FIRST_OPEN, true)) {
            //pToast("Please Set Your Name and Contact No");
            PrefUtils.putBoolean(PrefUtils.IS_FIRST_OPEN, false);

            String name = MixTools.getOwnerName(getApplication());
            String number = NetworkTools.getSimNumber();

            if (name != null) {
                PrefUtils.putString(PrefUtils.USER_NAME, name);
            } else {
                PrefUtils.putString(PrefUtils.USER_NAME, "");
            }
            if (number != null) {
                PrefUtils.putString(PrefUtils.USER_NO, number);
            } else {
                PrefUtils.putString(PrefUtils.USER_NO, "");
            }


            //presentShowcaseSequence();

            AlertDialog.Builder ab = new AlertDialog.Builder(this);

            // ab.setTitle(MyBanglaSupport.getBanglaSpnString(title, tf));
            // ab.setMessage(MyBanglaSupport.getBanglaSpnString(message, tf));

            ab.setTitle(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_info_title)));
            ab.setMessage(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_info)));
            ab.setCancelable(false);

            ab.setPositiveButton(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_yes)), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {

                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);

                }

            });

            ab.setNegativeButton(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_no)), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });

            ab.show();


        }

    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {


            case R.id.btHotKey:

                openGPS();
                captureImage();

                break;

            case R.id.btCall:
                //pToast("call");

                //dial(PHONE_NO);
                dialWithPermission();
                //getLocation();




//                intent = new Intent(MainActivity.this,ServerActivity.class);
//                startActivity(intent);
                //upData();


                break;

            case R.id.btMore:
                //pToast("More");

                intent = new Intent(MainActivity.this, MoreActivity.class);
                //intent = new Intent(MainActivity.this, MainIntroActivity.class);
                startActivity(intent);

                break;


        }

    }


    /**
     * Checking device has camera hardware or not
     */
    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }


    /**
     * Launching camera app to record video
     */
    private void captureVideo() {
//        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//
//        fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
//
//        // set video quality
//        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
//
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file
//        // name
//
//        // start the video capture Intent
//        startActivityForResult(intent, CAMERA_CAPTURE_VIDEO_REQUEST_CODE);
    }


    private boolean isGpsEnabled() {

        GPSTracker gps = new GPSTracker(MainActivity.this);
        GPSLoc gpsLoc = new GPSLoc(0.0, 0.0);

        // Check if GPS enabled
        if (gps.canGetLocation()) {
            return true;
        } else {
            //pToast("Please Enable GPS");
            return false;
        }
    }

    private void openGPS() {

        mGpsLoc = getLocation();


    }

    private GPSLoc getLocation() {

        //pToast("getLocation");
        gps = new GPSTracker(MainActivity.this);
        GPSLoc gpsLoc = new GPSLoc(0.0, 0.0);

        // Check if GPS enabled
        if (gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            // \n is for new line
            //pToast("Your Location is - \nLat: " + latitude + "\nLong: " + longitude);
            Log.d(TAG,"Your Location is - \nLat: " + latitude + "\nLong: " + longitude);

            if (latitude <= 0.0 || longitude <= 0.0) {
                //gpsLoc=null;
            } else {
                gpsLoc = new GPSLoc(latitude, longitude);
            }
            //return gpsLoc;

        } else {
            // Can't get location.
            // GPS or network is not enabled.
            // Ask user to enable GPS/network in settings.
            gps.showSettingsAlert();
            //pToast("Please wait for a moment (2sec) to get location before upload");

        }

        return gpsLoc;
    }


    private void updatePrefs() {

        if (ServiceTools.isMyServiceRunning(this, HKService.class)) {
            //pToast("Service Running");
            ServiceTools.setKeyGuard(getApplicationContext(), false);
            PrefUtils.putBoolean(PfKey.pfHotKey, true);

        } else {
            //pToast("Service Stopped");
            ServiceTools.setKeyGuard(getApplicationContext(), true);
            PrefUtils.putBoolean(PfKey.pfHotKey, false);
        }

        if (ServiceTools.isMyServiceRunning(this, ShakeService.class)) {
            //pToast("Service Running");
            ServiceTools.setKeyGuard(getApplicationContext(), false);
            PrefUtils.putBoolean(PfKey.pfShake, true);

        } else {
            //pToast("Service Stopped");
            ServiceTools.setKeyGuard(getApplicationContext(), true);
            PrefUtils.putBoolean(PfKey.pfShake, false);
        }


    }

    @Override
    public boolean onLongClick(View view) {

        switch (view.getId()) {


            case R.id.btHotKey:
                //pToast("Hot Key");
                //openGPS();
                //captureVideo();
                return true;

        }
        return false;
    }


    /**
     * Here we store the file url as it will be null after returning from camera
     * app
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }


    private void launchUploadActivity(boolean isImage) {
        //Intent i = new Intent(MainActivity.this, UploadActivity.class);
        Intent i = new Intent(MainActivity.this, UploadActivity2.class);
        //i.putExtra("filePath", fileUri.getPath());
        i.putExtra("filePath", mCurrentPhotoPath);
        i.putExtra("isImage", isImage);
        startActivity(i);
    }


    @Override
    protected void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        active = false;
    }

    private void presentShowcaseSequence() {

        //MaterialShowcaseView.resetAll(this);

        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view

        //config.setContentTextColor(ContextCompat.getColor(this,R.color.tuto_content));
        //config.setDismissTextColor(getResources().getColor(R.color.accent));


        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, SHOWCASE_ID);


        sequence.setOnItemShownListener(new MaterialShowcaseSequence.OnSequenceItemShownListener() {
            @Override
            public void onShow(MaterialShowcaseView itemView, int position) {
                //Toast.makeText(itemView.getContext(), "Item #" + position, Toast.LENGTH_SHORT).show();
            }
        });

        sequence.setConfig(config);

        //sequence.addSequenceItem(btHotKey, "Hot Key", "GOT IT");
        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(btHotKey)
                        .setDismissTextColor(getResources().getColor(R.color.tuto_dismiss))
                        .setContentTextColor(getResources().getColor(R.color.tuto_content))
                        .setDismissText(R.string.tuto_dismiss)
                        .setContentText(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_tuto_hotkey)))
                        .withCircleShape()
                        .build()
        );
        //sequence.addSequenceItem(btCall, "Call", "GOT IT");
        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(btCall)
                        .setDismissTextColor(getResources().getColor(R.color.tuto_dismiss))
                        .setContentTextColor(getResources().getColor(R.color.tuto_content))
                        .setDismissText(R.string.tuto_dismiss)
                        .setContentText(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_tuto_helpline)))
                        .withRectangleShape()
                        .build()
        );

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(btMore)
                        .setDismissTextColor(getResources().getColor(R.color.tuto_dismiss))
                        .setContentTextColor(getResources().getColor(R.color.tuto_content))
                        .setDismissText(R.string.tuto_dismiss)
                        .setContentText(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_tuto_more)))
                        .withCircleShape()
                        .build()
        );

        //sequence.addSequenceItem(btMore, "Click to Know more", "GOT IT");

//        sequence.addSequenceItem(
//                new MaterialShowcaseView.Builder(this)
//                        .setTarget(btCall)
//                        .setDismissText("GOT IT")
//                        .setContentText("Call Emergency")
//                        .withRectangleShape(true)
//                        .build()
//        );
//
//        sequence.addSequenceItem(
//                new MaterialShowcaseView.Builder(this)
//                        .setTarget(btMore)
//                        .setDismissText("GOT IT")
//                        .setContentText("More Button")
//                        .withRectangleShape()
//                        .build()
//        );

        sequence.start();

    }

    private void pToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }


}