package com.example.a01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.a01.databinding.NoItineraryPageBinding;


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