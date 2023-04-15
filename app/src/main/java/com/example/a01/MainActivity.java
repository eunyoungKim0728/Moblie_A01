//FILE          : MainActivity.java
//PROJECT       : PROG3150 - assignment 2
//PROGRAMMER    : Yujin Jeong, Eunyoung Kim. Hyewon Lee, Maísa Wolff Resplande
//FIRST VERSION : 2023.03.18
//DESCRIPTION   : This main file check the button and start new activity
//

package com.example.a01;


import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.util.Log;


import com.example.a01.database.Cities;
import com.example.a01.databinding.ActivityMainBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    public static final String TAG= "MainActivity";
    public Cities[] cities;
    private GoogleMap mMap;

    private String getJsonString() {
        String json = "";

        try {
            InputStream is = getAssets().open("db.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return json;
    }

    private void jsonParsing(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONArray cityArray = jsonObject.getJSONArray("City");

            for (int i = 0; i < cityArray.length(); i++)
            {
                JSONObject cityObject = cityArray.getJSONObject(i);

                Cities city = new Cities();

                city.setCityId(cityObject.getInt("cityID"));
                city.setCityName(cityObject.getString("cityName"));
                //city.setPrice(cityObject.getString("price"));

                // cities.add(city);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate");

        Intent musicService = new Intent(this,MusicService.class);

        ActivityMainBinding binding
                = DataBindingUtil.setContentView(this, R.layout.activity_main);


        Button torontoBtn = binding.buttonToronto;
        torontoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Toronto OnClick");
                startActivity(new Intent(getApplicationContext(), Itinerary.class));
                stopService(musicService);
                startService(musicService);
            }
        });

        Button torontoMapBtn = binding.buttonTorontoMap;
        torontoMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Map OnClick");
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("Lat", 43.6532);
                intent.putExtra("Long", -79.3832);
                intent.putExtra("Name", "Toronto");
                startActivity(intent);
            }
        });

        Button quebecBtn = binding.buttonQuebec;
        quebecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Quebec OnClick");
                setNotification();
                startActivity(new Intent(getApplicationContext(), NoItinerary1.class));
            }
        });


        Button quebecMapBtn = binding.buttonQuebecMap;
        quebecMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Map OnClick");
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("Lat", 46.8131);
                intent.putExtra("Long", -71.2075);
                intent.putExtra("Name", "Quebec");
                startActivity(intent);
            }
        });


        Button vancouverBtn = binding.buttonVancouver;
        vancouverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Vancouver OnClick");
                setNotification();
                startActivity(new Intent(getApplicationContext(), NoItinerary1.class));
            }
        });


        Button vancouverMapBtn = binding.buttonVancouverMap;
        vancouverMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Map OnClick");
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("Lat", 49.2827);
                intent.putExtra("Long", -123.1207);
                intent.putExtra("Name", "Vancouver");
                startActivity(intent);
            }
        });

        Button adminBtn = binding.adminBtn;
        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Admin Info OnClick");
                if (savedInstanceState == null) {
                    Fragment myFragment = new com.example.a01.MyFragment();
                    getFragmentManager().beginTransaction().add(android.R.id.content, myFragment).commit();
                }
            }
        });


    }

    protected void setNotification() {
        Log.d(TAG,"Notification created");
        Intent notificationIntent = new Intent(this, NoItinerary1.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        int pendingFlag = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,notificationIntent,pendingFlag);

        int icon = R.drawable.ic_launcher_background;

        CharSequence tickerText = "MyTickerText";
        CharSequence contentTitle = "Trip Planner";
        CharSequence contentText = "Did you participate our survey?\nWe need your help!";
        NotificationCompat.Builder myBuilder = new NotificationCompat.Builder(this, "My Channel")
                .setSmallIcon(icon)
                //.setTicker(t)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = getString(R.string.Noti_channel_name);
            String description = getString(R.string.Noti_channel_dis);
            NotificationChannel channel = new NotificationChannel("My Channel", name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(description);
            // Register the channel with the system
            manager.createNotificationChannel(channel);
            if (manager.areNotificationsEnabled()) {
                manager.notify(1,myBuilder.build());
            }
        }

    }




    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "OnCreate");
        getMenuInflater().inflate(R.menu.menu_1,menu);
        MenuBuilder m = (MenuBuilder)menu;
        m.setOptionalIconsVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "In ItemSelected");
        boolean result =false;
        Intent intent = null;
        switch(item.getItemId()) {
            case R.id.Toronto:
               intent=new Intent(this,Itinerary.class);
                startActivity(intent);
                result = true;
                break;
            case R.id.Quebec:
                intent=new Intent(this,NoItinerary1.class);
                startActivity(intent);
                result = true;
                break;
            case R.id.Vancouver:
                intent=new Intent(this,NoItinerary1.class);
                startActivity(intent);
                result = true;
                break;
            case R.id.Main:
                intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                result = true;
                break;


            default:
                result = super.onOptionsItemSelected(item);
                break;

        }

        return result;
    }


}