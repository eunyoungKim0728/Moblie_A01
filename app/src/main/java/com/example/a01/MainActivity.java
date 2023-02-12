/*
 * FILE				:MainActivity.java
 * PROGRAMMER		:Eunyoung Kim, Yujin Jeong, Hyewon Lee, Maísa Wolff Resplande
 * FIRST VERSION	:2023-02-11
 * DESCRIPTION		:Programs that list and run what appears in the main
 */

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


import com.example.a01.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    /*  -- Method Header Comment
	Name	:	onCreate
	Purpose :	Function that runs when clicked
	Inputs	:	savedInstanceState	Bundle
	Outputs	:	NONE
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding
                = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Button torontoBtn = binding.buttonToronto;
        torontoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Itinerary.class));
            }
        });

    }



    /*  -- Method Header Comment
	Name	:	onCreateOptionsMenu
	Purpose :	Method that runs when a menu is clicked
	Inputs	:	menu	Menu
	Outputs	:	NONE
    */
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_1,menu);
        MenuBuilder m = (MenuBuilder)menu;
        m.setOptionalIconsVisible(true);
        return true;
    }


    /*  -- Method Header Comment
	Name	:	onOptionsItemSelected
	Purpose :	Method for displaying a list
	Inputs	:	item	MenuItem
	Outputs	:	NONE
    */
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