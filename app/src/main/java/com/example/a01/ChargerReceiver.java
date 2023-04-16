/*
 * FILE            :ChargerReceiver.java
 * PROGRAMMER      :Eunyoung Kim, Yujin Jeong, Hyewon Lee, Maísa Wolff Resplande
 * FIRST VERSION   :2023-04-11
 * DESCRIPTION      : Programs that show ChargerReceiver
 */
package com.example.a01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


/*  -- Class Header Comment
 Name    :   ChargerReceiver
 Purpose :  Class to run the BroadcastReceiver
 */
public class ChargerReceiver extends BroadcastReceiver {
    public final static String MyAction = "com.example.a01.ACTION_MY_BROADCAST";
    private static final String TAG= "ChargerReceiver";


    /*  -- Method Header Comment
   Name     :   onReceive
   Purpose  :   Function that runs when received
   Inputs   :   Context         context
                Intent          intent
   Outputs  :   NONE
    */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "OnReceive");
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