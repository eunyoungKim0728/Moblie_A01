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

        Button nextBtn=binding.nextBtn;
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "OnClick Packing");
                startActivity(new Intent(getApplicationContext(),Packing.class));
            }
        });

        Button moreInfoBtn = binding.moreInfoBtn;
        moreInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "OnClick RestaurantList");
                startActivity(new Intent(getApplicationContext(),RestaurantList.class));
            }
        });

        ImageView cntowerImg = binding.cntowerImg;
        cntowerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "IMAGE[CN Tower]");
                Toast.makeText(getApplicationContext(),"CN Tower",Toast.LENGTH_SHORT).show();
            }
        });

        ImageView  aquaImg = binding.aquaImg;
        aquaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "IMAGE[Aquarium]");
                Toast.makeText(getApplicationContext(),"Ripley's Aquarium",Toast.LENGTH_SHORT).show();
            }
        });

        ImageView  res1Img = binding.res1Img;
        res1Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "IMAGE[PAI]");
                Toast.makeText(getApplicationContext(),"PAI",Toast.LENGTH_SHORT).show();
            }
        });

        ImageView  casalomaImg = binding.casalomaImg;
        casalomaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "IMAGE[Casa Loma]");
                Toast.makeText(getApplicationContext(),"Casa Loma",Toast.LENGTH_SHORT).show();
            }
        });


    }

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
