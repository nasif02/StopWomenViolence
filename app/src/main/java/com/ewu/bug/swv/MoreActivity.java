package com.ewu.bug.swv;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ewu.bug.swv.adapter.CustomListAdapter;
import com.ewu.bug.swv.model.ListItem;

import java.util.ArrayList;

import xplo.library.ekush.MyBanglaSupport;


public class MoreActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private final String TAG = "xxx";
    private ListView lv;
    private CustomListAdapter mCustomListAdapter;

    private Toolbar mToolbar;

    Typeface tf = null;

    TextView tvAbout, tvSettings;

    ImageView ivAbout,ivSettings;

    TextView tvTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);



        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        ActionBar ab = getSupportActionBar();
        //ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        tf = Typeface.createFromAsset(getAssets(),
                getString(R.string.font_bn));
        setTitle(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_know_more),tf));


        lv = (ListView) findViewById(R.id.listView1);
        //tvSupervisor = (TextView) findViewById(R.id.tvSupervisor);
        tvAbout = (TextView) findViewById(R.id.tvAbout);
        tvSettings = (TextView) findViewById(R.id.tvSettings);

        ivAbout = (ImageView) findViewById(R.id.ivAbout);
        ivSettings = (ImageView) findViewById(R.id.ivSettings);


        ivAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutOp();
            }
        });

        ivSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsOp();
            }
        });




        //ra = new ResAdapter(this);



        //tvSupervisor.setTypeface(tf);
        tvAbout.setTypeface(tf);
        tvSettings.setTypeface(tf);


        tvAbout.setOnClickListener(this);
        tvSettings.setOnClickListener(this);


        // fetchDataForArray(dbName);
        ArrayList<ListItem> listItem = new ArrayList<ListItem>();
        listItem = getListItems();

        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        // android.R.layout.simple_list_item_1, listItem);


        mCustomListAdapter = new CustomListAdapter(MoreActivity.this,
                listItem, tf);

        lv.addHeaderView(new View(this));
        lv.addFooterView(new View(this));

        lv.setAdapter(mCustomListAdapter);
        lv.setOnItemClickListener(this);


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long arg3) {

        //Log.d(TAG,"onItemClick");

        //DbgUtils.pToast(""+position,TAG);

        ListItem mItem = (ListItem) parent.getItemAtPosition(position);
        Log.d(TAG, mItem.toString());
        //pToast(mItem.toString());


        Intent intent;
        switch (position) {

            case 1:
                intent = new Intent(this, HelpActivity.class);
                startActivity(intent);
                break;

            case 2:
                intent = new Intent(this, SupportActivity.class);
                startActivity(intent);
                break;

            case 3:
                intent = new Intent(this, RightActivity.class);
                startActivity(intent);
                break;


        }
        //int id = mItem.getId();


        //Log.d(TAG,ids.toString());

//        Intent intent = new Intent(this, TextScreenSlideActivity.class);
//
//        Bundle mBundle = new Bundle();
//        mBundle.putString("dbName", dbName);
//        mBundle.putString("tableName", tableName);
//        mBundle.putString("titleBar", titleBar);
//        mBundle.putInt("position", position);
//        mBundle.putIntegerArrayList("ids", ids);
//        intent.putExtras(mBundle);
////
////        //Log.d(TAG, " ids[" + position + "]= " + ids.get(position) + " " + ids.toString());
////
//        //DbgUtils.pToast(mBundle.toString(), TAG);
//        Log.d(TAG, mBundle.toString());
//        startActivity(intent);

    }

    @Override
    public void onClick(View view) {


        Intent intent;
        switch (view.getId()) {
            case R.id.tvAbout:
                //pToast("About");
                aboutOp();

                break;

            case R.id.tvSettings:
                //pToast("Settings");

                settingsOp();
                break;


        }
    }


    private ArrayList<ListItem> getListItems() {

        ArrayList<ListItem> al = new ArrayList<>();

        al.add(new ListItem(1, getString(R.string.bn_how_to_use)));
        al.add(new ListItem(2, getString(R.string.bn_where_to_get_help)));
        al.add(new ListItem(3, getString(R.string.bn_which_my_right)));

        return al;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_more, menu);

//
//        // Get the action view used in your toggleservice item
//        final MenuItem menuItem = menu.findItem(R.id.action_service);
//        final SwitchCompat swService = (SwitchCompat) menuItem.getActionView();
//
//        boolean isServiceRunning=ServiceTools.isMyServiceRunning(this,HKService.class);
//
//        if(isServiceRunning){
////            this.stopService(new Intent(this, HKService.class));
////            ServiceTools.setKeyGuard(getApplicationContext(), false);
////            this.startService(new Intent(this, HKService.class));
//            swService.setChecked(true);
//        }else{
//            swService.setChecked(false);
//        }
//
//        swService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                // Start or stop your Service
//
//                if (isChecked) {
//                    //pToast("Checked");
//                    setHotKey(true);
//                    //parmanetService(true);
//
//                } else {
//                    //pToast("Not Checked");
//                    setHotKey(false);
//                    //parmanetService(false);
//
//                }
//            }
//        });
//
        return super.onCreateOptionsMenu(menu);


        //return true;
    }


//    private void setHotKey(boolean b) {
//
//        boolean isServiceRunning= ServiceTools.isMyServiceRunning(this, HKService.class);
//
//        if(b){
//
////            if(!isServiceRunning){
////                return;
////            }
//
//            ServiceTools.setKeyGuard(getApplicationContext(),false);
//            this.startService(new Intent(this, HKService.class));
//
//        }else {
////            if(!isServiceRunning){
////                return;
////            }
//            ServiceTools.setKeyGuard(getApplicationContext(),true);
//            this.stopService(new Intent(this,
//                    HKService.class));
//
//        }
//
//    }
//
//    private void parmanetService(boolean b) {
//
//        pToast("parmanetService");
//        Intent intent = new Intent("com.ewu.bug.swv.hotkey.MyServiceEX");
//        //Intent intent = new Intent("com.ewu.bug.swv.START_SERVICE");
//        //Intent intent = new Intent(MyServiceEX.class);
//        if(b){
//
//            this.startService(intent);
//        }else {
//            this.stopService(intent);
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        Intent intent;
        switch (id) {
            case R.id.action_about:
                //pToast("About");
                //intent = new Intent(MoreActivity.this, AboutActivity.class);
                //startActivity(intent);

                aboutOp();

                break;

            case R.id.action_settings:
                //pToast("Settings");
                settingsOp();


                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void settingsOp(){

        //pToast("Under Developing");
        Intent intent = new Intent(MoreActivity.this, SettingsActivity.class);
        startActivity(intent);

    }

    private void aboutOp(){

        //String msg="Nasif Ahmed (Team Leader)\nNadim Hussain\nAbdullah Al Mamun\nAsif Rahaman\nAlamgir Hussain";
        //showMessage(this, "EWU_BUGS", msg, "Ok", true);

        Intent intent = new Intent(MoreActivity.this, AboutActivity.class);
        startActivity(intent);


    }




    public static void showMessage(Context mContext,String title, String message, String pText, boolean cancelable){

        AlertDialog.Builder ab = new AlertDialog.Builder(mContext);

        // ab.setTitle(MyBanglaSupport.getBanglaSpnString(title, tf));
        // ab.setMessage(MyBanglaSupport.getBanglaSpnString(message, tf));

        ab.setTitle(title);
        ab.setMessage(message);
        ab.setCancelable(cancelable);

        ab.setPositiveButton(pText, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {



            }

        });

//		ab.setNegativeButton(nText, new DialogInterface.OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface arg0, int arg1) {
//
//			}
//		});

        ab.show();
    }

    private void pToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }


}
