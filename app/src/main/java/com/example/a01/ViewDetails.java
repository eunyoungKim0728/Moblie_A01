package com.example.a01;

import androidx.annotation.NonNull;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;


import java.util.Arrays;
import java.util.List;

public class ViewDetails extends AppCompatActivity {
    TextView detailsTextView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_details);
        detailsTextView = findViewById(R.id.detailsTextView);
        detailsTextView.setText(getIntent().getStringExtra("packingList"));

        TextView textView = findViewById(R.id.travellerDetail);
        textView.setText(getIntent().getStringExtra("name")+"'s Packing List");
    }
}