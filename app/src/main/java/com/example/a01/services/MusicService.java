package com.example.a01.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.a01.uilayer.Itinerary;
import com.example.a01.R;

public class MusicService extends Service {

    public static final String TAG = "Music Service";
    private MediaPlayer mediaPlayer;

    @Nullable
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
        mediaPlayer.stop();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"Service Started");
        mediaPlayer = MediaPlayer.create(this, R.raw.sunflower);  //mp3시작 및 반복
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        setNotification();
        return super.onStartCommand(intent, flags, startId);
    }


    // FUNCTION COMMENT ===========
    protected void setNotification() {
        Log.d(TAG,"Notification created");
        Intent notificationIntent = new Intent(this, Itinerary.class);
        mediaPlayer.start();
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        int pendingFlag = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,notificationIntent,pendingFlag);

        int icon = R.drawable.ic_launcher_background;

        CharSequence tickerText = "♫Music is playing♫";
        CharSequence contentTitle = "Trip Planner";
        CharSequence contentText = "Post Malone - Sunflower(instrumental) is playing";
        NotificationCompat.Builder myBuilder = new NotificationCompat.Builder(this, "My Channel")
                .setSmallIcon(icon)
                .setTicker(tickerText)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = getString(R.string.Noti_channel_name);
            String description = getString(R.string.Noti_channel_dis);
            NotificationChannel channel = new NotificationChannel("Music channel", name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(description);
            // Register the channel with the system
            manager.createNotificationChannel(channel);
            if (manager.areNotificationsEnabled()) {
                manager.notify(1,myBuilder.build());
            }
        }

    }

}