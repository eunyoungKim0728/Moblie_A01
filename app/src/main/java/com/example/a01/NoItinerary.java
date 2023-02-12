//FILE          : NoItinerary.java
//PROJECT       : PROG3150 - assignment 1
//PROGRAMMER    : Yujin Jeong, Eunyoung Kim. Hyewon Lee, Maísa Wolff Resplande
//FIRST VERSION : 2023.02.11
//DESCRIPTION   : This file has an interface to show a message "there is no itinerary" and a button
//                to go back to the main page

package com.example.a01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.a01.databinding.NoItineraryPageBinding;



// NAME   : NoItinerary
// PURPOSE: this class has a databinding button that allows user to go back to the main page
public class NoItinerary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NoItineraryPageBinding binding
                = DataBindingUtil.setContentView(this, R.layout.no_itinerary_page);

        Button mainBtn = binding.button2;
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }

}