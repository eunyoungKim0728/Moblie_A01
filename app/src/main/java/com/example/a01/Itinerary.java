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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.a01.databinding.ItineraryBinding;

public class Itinerary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //databinding
        ItineraryBinding binding = DataBindingUtil.setContentView(this,R.layout.itinerary);

        Spinner mySpinner = binding.daySpinner;
        ImageView imageView = binding.cntowerImg;

        //spinner
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.day_spinner,
                                                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int positionitem = mySpinner.getSelectedItemPosition();

                switch(positionitem){
                    case 1:
                        startActivity(new Intent(getApplicationContext(),Itinerary.class));
                    case 2:
                        startActivity(new Intent(getApplicationContext(),ItineraryDay2.class));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





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
