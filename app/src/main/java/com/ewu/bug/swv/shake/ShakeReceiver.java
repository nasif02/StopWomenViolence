package com.ewu.bug.swv.shake;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Xplo on 3/19/2016.
 */
public class ShakeReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        //Intent intent1 = new Intent(context, ShakeService.class);

        //pToast("Boot Receiver",context);
        Log.d("xxx","Boot Receiver");
        Intent intent1 = new Intent(context, ShakeService.class);

        context.startService(intent1);
    }

    private void pToast(String s,Context context) {
        Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}
