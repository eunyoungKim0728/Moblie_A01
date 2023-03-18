package com.example.a01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.MatrixCursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
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


        Button itineraryBtn = binding.backButton;
        itineraryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Itinerary.class));
            }
        });

        Button addNewBtn = binding.addTraveller;
        addNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Packing.class));
            }
        });



        TextView textView = binding.resultNameList;
        textView.setText("Check who's already packaged to your trip!" );


        // TODO Implement recycler view instead of list view
        /*RecyclerView recyclerView = binding.resultCheckList;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/

        ListView listView = binding.resultCheckList;
        PlannerDatabase db = PlannerDatabase.getInstance(this);
        List<String> list = new ArrayList<>();
        list = db.getUsersDAO().getAllUsersName();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);


       /* Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String checkBox1 = intent.getStringExtra("checkBox1");
        String checkBox2 = intent.getStringExtra("checkBox2");
        String checkBox3 = intent.getStringExtra("checkBox3");
        String checkBox4 = intent.getStringExtra("checkBox4");
        String checkBox5 = intent.getStringExtra("checkBox5");
        String checkBox6 = intent.getStringExtra("checkBox6");*/



        //textView.setText(name +"'s Packing List!" );



    }

    // TODO Write method for ListItemHolder

    // TODO Write method of ListAdapter for recycler view
    /*private class ListAdapter extends RecyclerView.Adapter {

        ListBinding binding = null;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            binding = ListItemBinding.inflate(getLayoutInflater());
//            View view = getLayoutInflater().inflate(R.layout.list_item, parent, false);
            return new ListItemHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            String item = myList.get(position);
            ((ListItemHolder)holder).getMyTextView().setText(item);
        }

        @Override
        public int getItemCount() {
            return myList.size();
        }
    }*/
}