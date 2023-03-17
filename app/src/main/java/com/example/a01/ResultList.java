package com.example.a01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a01.databinding.ActivityResultListBinding;
import com.example.a01.databinding.PackingBinding;
import com.example.a01.databinding.ResultListBinding;

import java.util.ArrayList;
import java.util.List;

public class ResultList extends AppCompatActivity {

    private @NonNull ActivityResultListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ResultListBinding binding = DataBindingUtil.setContentView(this,R.layout.result_list);

        TextView textView = binding.resultNameList;
        ListView listView = binding.resultCheckList;


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String checkBox1 = intent.getStringExtra("checkBox1");
        String checkBox2 = intent.getStringExtra("checkBox2");
        String checkBox3 = intent.getStringExtra("checkBox3");
        String checkBox4 = intent.getStringExtra("checkBox4");
        String checkBox5 = intent.getStringExtra("checkBox5");
        String checkBox6 = intent.getStringExtra("checkBox6");


        textView.setText(name +"'s Packing List!" );

        List<String> list = new ArrayList<>();

        list.add(checkBox1);
        if(checkBox1 == null) {
           list.remove(checkBox1);
        }
        list.add(checkBox2);
        if(checkBox2 == null) {
            list.remove(checkBox2);
        }
        list.add(checkBox3);
        if(checkBox3 == null) {
            list.remove(checkBox3);
        }
        list.add(checkBox4);
        if(checkBox4 == null) {
            list.remove(checkBox4);
        }
        list.add(checkBox5);
        if(checkBox5 == null) {
            list.remove(checkBox5);
        }
        list.add(checkBox6);
        if(checkBox6 == null) {
            list.remove(checkBox6);
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);



        // getPlannerDatabaseFromRoom();

        // db.getUsersDAO().insert(new Users(2001, name));
        // db.getTripsDAO().insert(new Trips(3001, 1001, 2001, list));

    }

    // Instantiate the database using Room
    private void getPlannerDatabaseFromRoom() {

        PlannerDatabase database = PlannerDatabase.getInstance(this);
        new PopulateDatabase().execute(database);
    }

    // Populate database with data from list
    private class PopulateDatabase extends AsyncTask<PlannerDatabase, Void, PlannerDatabase> {

        @Override
        protected PlannerDatabase doInBackground(PlannerDatabase... d) {
            PlannerDatabase db = d[0];
            if (db != null) {

                // Populate Cities table
                java.util.List<Cities> cities = db.getCitiesDAO().getCities();
                if (cities.size() == 0) {
                    db.getCitiesDAO().insert(new Cities(1001, "Toronto"));
                    db.getCitiesDAO().insert(new Cities(1002, "Quebec"));
                    db.getCitiesDAO().insert(new Cities(1003, "Vancouver"));
                }

                // Create Users table
                java.util.List<Users> users = db.getUsersDAO().getUsers();
                if (users.size() == 0) {
                    db.getUsersDAO().insert(new Users());
                }

                // Create Trips table
                java.util.List<Trips> trips = db.getTripsDAO().getTrips();
                if (trips.size() == 0) {
                    db.getTripsDAO().insert(new Trips());
                }
            }

            return db;
        }

        // TODO Check if we need to have onPostExecute
        @Override
        protected void onPostExecute(PlannerDatabase db) {

        }
    }
}