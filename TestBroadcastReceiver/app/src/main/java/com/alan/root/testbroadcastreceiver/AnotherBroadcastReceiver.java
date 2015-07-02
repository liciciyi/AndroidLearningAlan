package com.alan.root.testbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by root on 15-6-22.
 */
public class AnotherBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received in my AnotherBroadcastReceiver",
                Toast.LENGTH_SHORT).show();
    }
}
