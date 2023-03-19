//FILE          : NoItinerary1.java
//PROJECT       : PROG3150 - assignment 3
//PROGRAMMER    : Yujin Jeong, Eunyoung Kim. Hyewon Lee, Maísa Wolff Resplande
//FIRST VERSION : 2023.03.18
//DESCRIPTION   : This file has an interface to show a message "there is no itinerary" and a button
//                to go back to the main page

package com.example.a01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.example.a01.databinding.NoItineraryPageBinding;



// NAME   : NoItinerary
// PURPOSE: this class has a databinding button that allows user to go back to the main page
public class NoItinerary1 extends AppCompatActivity {

    public static final String TAG= "Noltinerary1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "In OnCreate");
        super.onCreate(savedInstanceState);
        NoItineraryPageBinding binding
                = DataBindingUtil.setContentView(this, R.layout.no_itinerary_page);

        Button mainBtn = binding.mainButton;
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "In OnClick Listener");
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
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
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


    }

}