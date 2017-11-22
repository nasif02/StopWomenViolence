package com.ewu.bug.swv;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ewu.bug.swv.utils.PrefUtils;

import xplo.library.ekush.MyBanglaSupport;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    Typeface tf = null;

    TextView tv1,tv2,tv3;

    EditText etName,etNo;
    Button btSave;

    String name,no;

    //EasyPref ep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        ActionBar ab = getSupportActionBar();
        //ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        tf = Typeface.createFromAsset(getAssets(),
                getString(R.string.font_bn));
        setTitle(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_profile), tf));
        //setTitle("Profile");

        //ep=new EasyPref(this);

        etName = (EditText) findViewById(R.id.etName);
        etNo = (EditText) findViewById(R.id.etNo);
        btSave = (Button) findViewById(R.id.btSave);


        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        //tv3 = (TextView) findViewById(R.id.tv3);



        tv1.setTypeface(tf);
        tv2.setTypeface(tf);
        btSave.setTypeface(tf);
        etName.setTypeface(tf);
        etNo.setTypeface(tf);


        //etName.setText(ep.getString("name","none"));
        //etNo.setText(ep.getString("no","00"));

        String s1 = PrefUtils.getString(PrefUtils.USER_NAME, "");
        String s2 = PrefUtils.getString(PrefUtils.USER_NO, "");

        //pToast(s1+ " " + s2);


        etName.setText(PrefUtils.getString(PrefUtils.USER_NAME, ""));
        etNo.setText(PrefUtils.getString(PrefUtils.USER_NO,""));

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pToast("Save");


//                String name = MixTools.getOwnerName(getApplication());
//                String number = NetworkTools.getSimNumber();
//
//                if(name!=null){
//                    PrefUtils.putString(PrefUtils.USER_NAME, name);
//                }
//                if(number!=null){
//                    PrefUtils.putString(PrefUtils.USER_NO,number);
//                }

                name=etName.getText().toString();
                no=etNo.getText().toString();

                if(etName==null || etNo==null){
                    pToast("Enter Info");
                }else{

                    //ep.putString("name",name);
                    //ep.putString("no",no);
                    PrefUtils.putString(PrefUtils.USER_NAME,name);
                    PrefUtils.putString(PrefUtils.USER_NO,no);

                    String s1 = PrefUtils.getString(PrefUtils.USER_NAME, "");
                    String s2 = PrefUtils.getString(PrefUtils.USER_NO, "");

                    //pToast(s1 + " " + s2);

                    pToast("Data Saved");
                    finish();
                }


            }
        });



    }

    private void pToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_settings, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
