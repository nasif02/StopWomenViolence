package com.ewu.bug.swv;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import xplo.library.ekush.MyBanglaSupport;

public class AboutActivity extends AppCompatActivity {

    Typeface tf;

    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tf = Typeface.createFromAsset(getAssets(),
                getString(R.string.font_bn));

        setTitle(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_about_us), tf));


        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);
        tv7 = (TextView) findViewById(R.id.tv7);
        tv8 = (TextView) findViewById(R.id.tv8);
        tv9 = (TextView) findViewById(R.id.tv9);


        tv1.setTypeface(tf);
        tv2.setTypeface(tf);
        tv3.setTypeface(tf);
        tv4.setTypeface(tf);
        tv5.setTypeface(tf);
        tv6.setTypeface(tf);
        tv7.setTypeface(tf);
        tv8.setTypeface(tf);
        tv9.setTypeface(tf);
        //tv.setTypeface(tf);




    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_about, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
