package com.ewu.bug.swv.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.ewu.bug.swv.MainActivity;
import com.ewu.bug.swv.R;

/**
 * Created by Xplo on 3/19/2016.
 */
public class WidgetProvider extends AppWidgetProvider {

    Context mContext;
    // our actions for our buttons
    public static String ACTION_WIDGET_IMAGE = "ACTION_WIDGET_IMAGE";
    public static String ACTION_WIDGET_VIDEO = "ACTION_WIDGET_VIDEO";
    //public static String ACTION_WIDGET_ABOUT = "ActionReceiverAbout";


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        mContext=context;
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        Intent active = new Intent(context, WidgetProvider.class);
        active.setAction(ACTION_WIDGET_IMAGE);
        PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context, 0, active, 0);
        remoteViews.setOnClickPendingIntent(R.id.ibCapImage, actionPendingIntent);

        active = new Intent(context, WidgetProvider.class);
        active.setAction(ACTION_WIDGET_VIDEO);
        actionPendingIntent = PendingIntent.getBroadcast(context, 0, active, 0);
        remoteViews.setOnClickPendingIntent(R.id.ibCapVideo, actionPendingIntent);

//        active = new Intent(context, WidgetProvider.class);
//        active.setAction(ACTION_WIDGET_ABOUT);
//        actionPendingIntent = PendingIntent.getBroadcast(context, 0, active, 0);
//        remoteViews.setOnClickPendingIntent(R.id.button_about, actionPendingIntent);

        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    @Override
    public void onReceive(Context context, Intent intent) {


        mContext=context;

        Intent intent1;
        //pToast("OnReceive");
        if (intent.getAction().equals(ACTION_WIDGET_IMAGE)) {
            Log.i("onReceive xxx", ACTION_WIDGET_IMAGE);
            pToast("Capture Image");

            intent1 = new Intent(context, MainActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent1.putExtra("flag","image");
            context.startActivity(intent1);



        } else if (intent.getAction().equals(ACTION_WIDGET_VIDEO)) {
            Log.i("onReceive xxx", ACTION_WIDGET_VIDEO);
            pToast("Capture Video");

            intent1 = new Intent(context, MainActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent1.putExtra("flag", "video");
            context.startActivity(intent1);
        //} else if (intent.getAction().equals(ACTION_WIDGET_ABOUT)) {
        //    Log.i("onReceive", ACTION_WIDGET_ABOUT);
        } else {
            super.onReceive(context, intent);
        }
    }

    private void pToast(String s) {
        Toast.makeText(mContext.getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

}