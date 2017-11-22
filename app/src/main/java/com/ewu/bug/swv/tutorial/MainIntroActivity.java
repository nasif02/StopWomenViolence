package com.ewu.bug.swv.tutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.ewu.bug.swv.R;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

public class MainIntroActivity extends AppCompatActivity {


    private Button btHotKey;
    private Button btCall;
    //private ImageButton ibtCall;
    private Button btMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_intro);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        btHotKey = (Button) findViewById(R.id.btHotKey);
        btCall = (Button) findViewById(R.id.btCall);
        //ibtCall = (ImageButton) findViewById(R.id.btCall);
        btMore = (Button) findViewById(R.id.btMore);

        new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(R.id.btHotKey, this))
                //.setContentTitle("ShowcaseView")
                .setContentText("This is highlighting the Home button")
                .hideOnTouchOutside()
                .build();

        new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(R.id.btCall, this))
                .setContentText("call")
                .hideOnTouchOutside()
                .build();




    }

}
