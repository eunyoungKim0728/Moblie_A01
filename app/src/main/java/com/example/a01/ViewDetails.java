package com.example.a01;

import androidx.annotation.NonNull;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.a01.databinding.ViewDetailsBinding;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class ViewDetails extends AppCompatActivity {

    // TODO On create display details for traveller retrieving information for database
    // Traveller name
    // Packing list

    TextView detailsTextView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_details);
        detailsTextView = findViewById(R.id.detailsTextView);
        detailsTextView.setText(getIntent().getStringExtra("packingList"));
    }
}
