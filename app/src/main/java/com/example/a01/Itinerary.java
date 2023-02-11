package com.example.a01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.a01.databinding.ItineraryBinding;

public class Itinerary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ItineraryBinding binding = DataBindingUtil.setContentView(this,R.layout.itinerary);

        TextView textView = binding.dayText;
        ImageView imageView = binding.cntowerImg;



    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_1,menu);
        MenuBuilder m = (MenuBuilder)menu;
        m.setOptionalIconsVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result =false;
        Intent intent = null;
        switch(item.getItemId()) {
            case R.id.citiesPage:
                intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                result = true;
                break;
            case R.id.ItineraryPage:
                intent=new Intent(this,Itinerary.class);
                startActivity(intent);
                result = true;
                break;
            case R.id.packingPage:
                intent=new Intent(this,Packing.class);
                startActivity(intent);
                result = true;
                break;
            case R.id.restaurant:
                intent=new Intent(this,RestaurantList.class);
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
