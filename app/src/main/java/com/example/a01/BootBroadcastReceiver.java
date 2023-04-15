package com.example.a01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootBroadcastReceiver extends BroadcastReceiver {
    public final static String MyAction = "com.example.a01.ACTION_MY_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        // if either power connected or unconnected, display toast message
        if(Intent.ACTION_POWER_CONNECTED.equals(intent.getAction()))
        {
            Toast.makeText(context, "Power Connected", Toast.LENGTH_SHORT).show();
        }
        else if(Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction()))
        {
            Toast.makeText(context, "Power Unconnected", Toast.LENGTH_SHORT).show();
        }
    }
}