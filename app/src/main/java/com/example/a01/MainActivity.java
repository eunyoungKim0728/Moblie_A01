//FILE          : MainActivity.java
//PROJECT       : PROG3150 - assignment 2
//PROGRAMMER    : Yujin Jeong, Eunyoung Kim. Hyewon Lee, Maísa Wolff Resplande
//FIRST VERSION : 2023.03.18
//DESCRIPTION   : This main file check the button and start new activity
//

package com.example.a01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.a01.database.Cities;
import com.example.a01.databinding.ActivityMainBinding;
import com.example.a01.databinding.AdminInfoBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends AppCompatActivity {

    private static final String TAG= "MainActivity";
    private static final int PERMISSIONS_REQUEST = 1;
    public Cities[] cities;
    private GoogleMap mMap;
    private Context myContext = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate");

        // check permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, PERMISSIONS_REQUEST);
        }


        Intent musicService = new Intent(this,MusicService.class);
        Intent NoItineraryService = new Intent(this,NoItineraryService.class);

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
                startService(NoItineraryService);

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
                startService(NoItineraryService);

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
                Uri adminUri = Uri.parse("content://" + MyContentProvider.PATH + "/admin");
                Cursor cursor = getContentResolver().query(adminUri,null,null,null,null);
                String[] columns = {AdminListDB.ADMIN_NAME, AdminListDB.ADMIN_INFO};
                int[] to = {R.id.adminName, R.id.adminInfo};
                SimpleCursorAdapter myAdapter = new SimpleCursorAdapter(
                        getApplicationContext(), R.layout.admin_info_list,
                        cursor,
                        columns, to, 0
                );
                // need to modify it!!!!
                //ListView myList = binding.adminListView;
                //myList.setAdapter(myAdapter);

            }
        });

    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
        ad.setIcon(R.mipmap.ic_launcher);
        ad.setTitle("Trip Planner");
        ad.setMessage("Do you want to quit Trip Planner?");

        ad.setPositiveButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                MainActivity.super.onBackPressed();
            }
        });

        ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        ad.show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Log.d(TAG, "Permission denied");
                }
                return;
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