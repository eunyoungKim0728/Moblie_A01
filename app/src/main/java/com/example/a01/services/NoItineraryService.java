package com.example.a01.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.a01.uilayer.NoItinerary;

public class NoItineraryService extends Service {

    public static final String TAG= "NoItineraryService";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG,"Service created");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"Service destroyed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"Service Started");
        Intent activityIntent = new Intent(getApplicationContext(), NoItinerary.class);
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(activityIntent);
        return super.onStartCommand(intent, flags, startId);
    }



}