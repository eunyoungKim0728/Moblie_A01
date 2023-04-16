/*
 * FILE            :MapsActivity.java
 * PROGRAMMER      :Eunyoung Kim, Yujin Jeong, Hyewon Lee, Maísa Wolff Resplande
 * FIRST VERSION   :2023-04-11
 * DESCRIPTION      : Programs that show Maps and can zoom in and out
 */

package com.example.a01;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.a01.databinding.ActivityMapsBinding;


/*  -- Class Header Comment
 Name    :   MapsActivity
 Purpose :  Class to run the FragmentActivity
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private static final String TAG= "MapsActivity";


    /*  -- Method Header Comment
   Name     :   onCreate
   Purpose  :   Function that runs when clicked
   Inputs   :   savedInstanceState   Bundle
   Outputs  :   NONE
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate");

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /*  -- Method Header Comment
   Name     :   onMapReady
   Purpose  :   Function that runs when googleMap clicked
   Inputs   :   savedInstanceState   Bundle
   Outputs  :   NONE
    */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "Open the Map");
        mMap = googleMap;
        double latitude = getIntent().getDoubleExtra("Lat", 0);
        double longitude = getIntent().getDoubleExtra("Long", 0);
        String title = getIntent().getStringExtra("Name");
        LatLng location = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(location).title(title));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f));
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }
}