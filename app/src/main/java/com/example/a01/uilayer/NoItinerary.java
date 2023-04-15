//FILE          : NoItinerary1.java
//PROJECT       : PROG3150 - assignment 3
//PROGRAMMER    : Yujin Jeong, Eunyoung Kim. Hyewon Lee, Maísa Wolff Resplande
//FIRST VERSION : 2023.03.18
//DESCRIPTION   : This file has an interface to show a message "there is no itinerary" and a button
//                to go back to the main page

package com.example.a01.uilayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.example.a01.MainActivity;
import com.example.a01.R;
import com.example.a01.databinding.NoItineraryPageBinding;


// NAME   : NoItinerary
// PURPOSE: this class has a databinding button that allows user to go back to the main page
public class NoItinerary extends AppCompatActivity {

    public static final String TAG= "Noltinerary1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "In OnCreate");
        super.onCreate(savedInstanceState);
        NoItineraryPageBinding binding
                = DataBindingUtil.setContentView(this, R.layout.no_itinerary_page);

        Intent NoItineraryService = new Intent(this, com.example.a01.services.NoItineraryService.class);

        Button mainBtn = binding.mainButton;
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "In OnClick Listener");
                stopService(NoItineraryService);
                setNotification();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });



        TextView text = binding.textView;
        SeekBar priceSeek = binding.priceSeek;
        priceSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "In OnProgressChanged");
                text.setText(String.format("$%d",seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "In OnStopTrackingToch");
                text.setText(String.format("$%d",seekBar.getProgress()));
            }
        });

        Button submitBtn = binding.submit;
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "In OnClick MainActivity");
                Toast.makeText(getApplicationContext(),"Thank you for taking your time",Toast.LENGTH_SHORT).show();
                stopService(NoItineraryService);
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


    }

    protected void setNotification() {
        Log.d(TAG,"Notification created");
        Intent notificationIntent = new Intent(this, NoItinerary.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        int pendingFlag = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,notificationIntent,pendingFlag);

        int icon = R.drawable.ic_launcher_background;

        CharSequence tickerText = "Oops! you missed a survey!";
        CharSequence contentTitle = "Trip Planner";
        CharSequence contentText = "Did you participate our survey?\nWe need your help!";
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
            NotificationChannel channel = new NotificationChannel("No Itinerary Channel", name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(description);

            // Register the channel with the system
            manager.createNotificationChannel(channel);
            if (manager.areNotificationsEnabled()) {
                manager.notify(1,myBuilder.build());
            }
        }

    }

}