//FILE          : Itinerary.java
//PROJECT       : PROG3150 - assignment 2
//PROGRAMMER    : Yujin Jeong, Eunyoung Kim. Hyewon Lee, Maísa Wolff Resplande
//FIRST VERSION : 2023.03.18
//DESCRIPTION   : This file shows the itinerary page and button
//
package com.example.a01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.util.Log;


import com.example.a01.databinding.ItineraryBinding;

public class Itinerary extends AppCompatActivity {
    public static final String TAG = "Itinerary";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate");
        //databinding
        ItineraryBinding binding = DataBindingUtil.setContentView(this,R.layout.itinerary);

        Intent musicService = new Intent(this,MusicService.class);
        Button nextBtn=binding.nextBtn;
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "OnClick Add Travellers");
                startActivity(new Intent(getApplicationContext(),ResultList.class));
            }
        });



        ImageView cntowerImg = binding.cntowerImg;
        cntowerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "IMAGE[CN Tower]");
                String url = "https://www.cntower.ca/";
                Intent intent = new Intent(getApplicationContext(), WebViewList.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

        ImageView  aquaImg = binding.aquaImg;
        aquaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "IMAGE[Aquarium]");
                String url = "https://www.ripleyaquariums.com/canada/";
                Intent intent = new Intent(getApplicationContext(), WebViewList.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

        ImageView  res1Img = binding.res1Img;
        res1Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://paitoronto.com/";
                Log.d(TAG, "IMAGE[PAI]");
                Intent intent = new Intent(getApplicationContext(), WebViewList.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

        ImageView  casalomaImg = binding.casalomaImg;
        casalomaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "IMAGE[Casa Loma]");
                String url = "https://casaloma.ca/";
                Intent intent = new Intent(getApplicationContext(), WebViewList.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });


    }

    /*
     * FUNCTION 	: onCreateOptionsMenu(Menu menu)
     * DESCRIPTION 	: Display option menu
     * PARAMETERS	:
     *      menu menu: menus
     * RETURNS	: true (no error)
     */
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "In OnCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.menu_1,menu);
        MenuBuilder m = (MenuBuilder)menu;
        m.setOptionalIconsVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "In OnOptionsItemSelected");
        Intent musicService = new Intent(this,MusicService.class);
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
                stopService(musicService);
                break;

            default:
                result = super.onOptionsItemSelected(item);
                break;

        }

        return result;
    }
}
