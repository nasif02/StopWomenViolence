package com.ewu.bug.swv;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ewu.bug.swv.utils.GPSTracker;

import java.util.HashMap;
import java.util.Map;

public class ServerActivity extends AppCompatActivity {


    String TAG = "xxx";
    EditText fname, lname, age;
    Button b ;

    //http://192.168.0.105/AndroidFileUpload/fileUpload.php
    //String insertUrl = "http://fearless.twomini.com/tutorial/insertStudent.php";
    //String insertUrl = "http://192.168.0.106/testproject/tutorial/insertStudent.php";
    //String insertUrl = "http://192.168.0.106/testproject/tutorial/insertStudent.php";

    //http://178.62.200.168/

    //http://178.62.200.168/
    //String insertUrl = "http://178.62.200.168/testproject/tutorial/insertStudent.php";
    String insertUrl = "http://178.62.200.168/testproject/tutorial/insertData.php";
    RequestQueue requestQueue;

    // GPSTracker class
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        fname = (EditText) findViewById(R.id.editText1);
        lname = (EditText) findViewById(R.id.editText2);
        age = (EditText) findViewById(R.id.editText3);
        b = (Button) findViewById(R.id.button1);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //upData();
                printLocation();
            }
        });

        requestQueue = Volley.newRequestQueue(getApplicationContext());





    }


    private void printLocation(){

        gps = new GPSTracker(ServerActivity.this);

        // Check if GPS enabled
        if(gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else {
            // Can't get location.
            // GPS or network is not enabled.
            // Ask user to enable GPS/network in settings.
            gps.showSettingsAlert();
        }
    }



    private void upData(){

        StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> parameters = new HashMap<String, String>();


//                parameters.put("id", "5");
//                parameters.put("firstname", "first name");
//                parameters.put("lastname", "last name");
//                parameters.put("age", "10");


                parameters.put("id", "55");
                //parameters.put("time", "2.25");
                parameters.put("image", "IMG_556_556");
                parameters.put("lat", "10");
                parameters.put("long", "10");
                //parameters.put("date", "20-12-94");


                return parameters;
            }
        };

        requestQueue.add(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_server, menu);
        return true;
    }

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
