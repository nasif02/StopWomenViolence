package com.ewu.bug.swv;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ewu.bug.swv.adapter.CustomListAdapterSupport;
import com.ewu.bug.swv.model.ListItem;

import java.util.ArrayList;

import xplo.library.ekush.MyBanglaSupport;


public class SupportActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{



    private final String TAG = "xxx";
    private ListView lv;
    private CustomListAdapterSupport mCustomListAdapter;

    private Toolbar mToolbar;

    Typeface tf = null;

    //TextView tvSupervisor, tvAbout, tvSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        ActionBar ab = getSupportActionBar();
        //ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        //setTitle(getString(R.string.bn_who_will_help));

        tf = Typeface.createFromAsset(getAssets(),
                getString(R.string.font_bn));
        setTitle(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_who_will_help), tf));


        lv = (ListView) findViewById(R.id.listView1);



        //ra = new ResAdapter(this);



        // fetchDataForArray(dbName);
        ArrayList<ListItem> listItem = new ArrayList<ListItem>();
        listItem = getListItems();

        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        // android.R.layout.simple_list_item_1, listItem);


        mCustomListAdapter = new CustomListAdapterSupport(SupportActivity.this,
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


//        Intent intent;
//        switch (position){
//
//            case 1:
//                intent = new Intent(this, HelpActivity.class);
//                startActivity(intent);
//                break;
//
//            case 2:
//                intent = new Intent(this, SupportActivity.class);
//                startActivity(intent);
//                break;
//
//            case 3:
//                intent = new Intent(this, RightActivity.class);
//                startActivity(intent);
//                break;
//
//
//
//        }
        //int id = mItem.getId();


        //Log.d(TAG,ids.toString());

        Intent intent = new Intent(this, SupporterDetailedActivityUP.class);

        Bundle mBundle = new Bundle();
//        mBundle.putString("dbName", dbName);
//        mBundle.putString("tableName", tableName);
        mBundle.putString("titleBar", mItem.getData());
        mBundle.putInt("position", position);
        intent.putExtras(mBundle);
//
//        //Log.d(TAG, " ids[" + position + "]= " + ids.get(position) + " " + ids.toString());
//
        //DbgUtils.pToast(mBundle.toString(), TAG);
        Log.d(TAG, mBundle.toString());
        startActivity(intent);

    }

    private ArrayList<ListItem> getListItems() {

        ArrayList<ListItem> al = new ArrayList<>();

        al.add(new ListItem(1, "WE CAN"));
        al.add(new ListItem(2, "USAID"));
        al.add(new ListItem(3, "CARE BANGLADESH"));
        al.add(new ListItem(4, "BRAC"));
        //al.add(new ListItem(, ""));

        return al;

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_support, menu);
//        return true;
//    }
//
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
    private void pToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}
