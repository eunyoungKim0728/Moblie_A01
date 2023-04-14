package com.example.a01;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.a01.database.PlannerDatabase;
import com.example.a01.database.Trips;
import com.example.a01.databinding.ViewDetailsBinding;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.List;

public class ViewDetails extends AppCompatActivity {

    public static final String TAG= "ViewDetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "In OnCreate");

        ViewDetailsBinding binding = DataBindingUtil.setContentView(this,R.layout.view_details);

        TextView detailsTextView = binding.detailsTextView;
        detailsTextView.setText(getIntent().getStringExtra("packingList"));

        TextView travellerDetail = binding.travellerDetail;
        String userName = getIntent().getStringExtra("name");
        travellerDetail.setText(userName+"'s Packing List");

        Button deleteBtn = binding.deleteTraveller;
        PlannerDatabase db = PlannerDatabase.getInstance(getApplicationContext());

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Delete traveller from database");
                List<Trips> tripsList = db.getTripsDAO().getTripsFromUserName(userName);

                // delete trip of the user
                for (int i = 0; i < tripsList.size(); i++) {
                    db.getTripsDAO().delete(tripsList.get(i));
                }

                // TODO Delete user from Users table

                startActivity(new Intent(getApplicationContext(),ResultList.class));
            }
        });


    }


}