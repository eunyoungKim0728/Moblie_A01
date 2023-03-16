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
import android.widget.Button;
import android.util.Log;
import android.widget.ListView;


import com.example.a01.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final String TAG= "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate");

        ActivityMainBinding binding
                = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ListView listView = findViewById(R.id.tripList);

        List<String> list = new ArrayList<>();
        list.add("Toronto");
        list.add("Quebec");
        list.add("Vancouver");

        ArrayAdapter<String> adpater = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adpater);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Object item = adapterView.getItemAtPosition(position);
                String data = (String) adapterView.getItemAtPosition(position);
                if(data == "Toronto"){
                    Intent intent = new Intent(getApplicationContext(), Itinerary.class);
                    startActivity(intent);
                }else if(data == "Quebec"){
                    Intent intent = new Intent(getApplicationContext(), NoItinerary1.class);
                    startActivity(intent);
                }else if(data == "Vancouver"){
                    Intent intent = new Intent(getApplicationContext(), NoItinerary1.class);
                    startActivity(intent);
                }
            }
        });

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