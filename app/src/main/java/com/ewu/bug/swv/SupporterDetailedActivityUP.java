package com.ewu.bug.swv;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import xplo.library.ekush.MyBanglaSupport;


public class SupporterDetailedActivityUP extends AppCompatActivity{


    private final String TAG = "xxx";
    //    private ListView lv;
//    private CustomListAdapterSupport mCustomListAdapter;

    TextView title, description, address, phone, website;
    ImageView icon;
    Bundle mBundle;

    TextView tv1,tv2,tv3,tv4;


    private Toolbar mToolbar;

    Typeface tf = null;

    String titleBar;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supporter_detailed_up);

        mBundle = new Bundle();
        try {
            mBundle = getIntent().getExtras();

            // Log.d(TAG, mBundle.toString());
        } catch (Exception e) {
            // Log.e(TAG, "intent value can't find");
        }

        titleBar = mBundle.getString("titleBar");


        mToolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(mToolbar);

        //mToolbar.setNavigationOnClickListener(this);


        ActionBar ab = getSupportActionBar();
        //ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        tf = Typeface.createFromAsset(getAssets(),
                getString(R.string.font_bn));
        //setTitle(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_), tf));
        setTitle(MyBanglaSupport.getBanglaSpnString(titleBar, tf));

        title = (TextView) findViewById(R.id.tvHeader);
        description = (TextView) findViewById(R.id.tvDesc);
        address = (TextView) findViewById(R.id.address);
        phone = (TextView) findViewById(R.id.phone);
        website = (TextView) findViewById(R.id.webSite);

        title.setTypeface(tf);
        description.setTypeface(tf);
        address.setTypeface(tf);
        //phone.setTypeface(tf);
        //website.setTypeface(tf);

        icon = (ImageView) findViewById(R.id.lvIcon);


        //ra = new ResAdapter(this);


        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        //tv4 = (TextView) findViewById(R.id.tv4);
//
//
        tv1.setTypeface(tf);
        tv2.setTypeface(tf);
        tv3.setTypeface(tf);
        //tv4.setTypeface(tf);


        showItemsOnClick();

    }

    private void showItemsOnClick() {
        position = mBundle.getInt("position", -1);

        switch (position) {
            case 1:
                icon.setImageResource(R.drawable.logo_we_can_red);
                title.setText("WE CAN" + "\n" + "(আমরাই পারি)");
                description.setText(getResources().getText(R.string.we_can_details));
                address.setText(getResources().getText(R.string.we_can_address));
                website.setText(getResources().getText(R.string.we_can_web));
                phone.setText(getResources().getText(R.string.we_can_contact));
                break;
            case 2:
                icon.setImageResource(R.drawable.logo_usaid);
                title.setText("USAID");
                description.setText(getResources().getText(R.string.usaid_details));
                address.setText(getResources().getText(R.string.usaid_address));
                website.setText(getResources().getText(R.string.usaid_web));
                phone.setText(getResources().getText(R.string.usaid_contact));
                break;
            case 3:
                icon.setImageResource(R.drawable.logo_care);
                title.setText("CARE BANGLADESH");
                description.setText(getResources().getText(R.string.care_bangladesh_details));
                address.setText(getResources().getText(R.string.care_bangladesh_address));
                website.setText(getResources().getText(R.string.care_bangladesh_web));
                phone.setText(getResources().getText(R.string.care_bangladesh_contact));
                break;
            case 4:
                icon.setImageResource(R.drawable.logo_brac);
                title.setText("BRAC");
                description.setText(getResources().getText(R.string.brac_details));
                address.setText(getResources().getText(R.string.brac_address));
                website.setText(getResources().getText(R.string.brac_web));
                phone.setText(getResources().getText(R.string.brac_contact));
                break;

        }

    }

    private void dial(String no) {
        // TODO Auto-generated method stub

        if (no != null) {

            String number = "tel:" + no;
            Intent callIntent = new Intent(Intent.ACTION_CALL,
                    Uri.parse(number));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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

    public void call(View view) {
        dial(phone.getText().toString());
    }

    public void website(View view) {

        openURL(website.getText().toString());


    }

    private void openURL(String url) {

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

    }

    /*@Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SupportActivity.class);
        startActivity(intent);
    }*/

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_more, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();


        if (id == android.R.id.home) {
            finish();
            return true;
        }

        Intent intent;
        switch (id) {
            case R.id.action_about:
                //pToast("About");
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);

                break;

            case R.id.action_settings:
                //pToast("Settings");

                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
