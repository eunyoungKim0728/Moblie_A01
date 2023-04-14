package com.example.a01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a01.databinding.ActivityResultListBinding;
import com.example.a01.databinding.ResultListBinding;
import com.example.a01.databinding.ListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ResultList extends AppCompatActivity {

    private @NonNull ActivityResultListBinding binding;

    RecyclerView recyclerView = null;
    private List<String> list = null;

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
        textView.setText("Check who's already packaged to your trip!");

        // getting data from database
        PlannerDatabase db = PlannerDatabase.getInstance(this);
        list = db.getUsersDAO().getAllUsersName();

        // using recycler view
        recyclerView = binding.travellersList;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter());
    }


    private class ListItemHolder extends RecyclerView.ViewHolder {
        private TextView detailsTextView;
        public ListItemHolder(ListItemBinding binding) {
            super(binding.getRoot());
            detailsTextView = binding.listItem;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String userName = detailsTextView.getText().toString();
                    PlannerDatabase db = PlannerDatabase.getInstance(getApplicationContext());
                    String packingList = db.getTripsDAO().getPackingListFromUserName(userName);

                    Intent myIntent = new Intent(getApplicationContext(),
                            ViewDetails.class);
                    myIntent.putExtra("name",userName);
                    myIntent.putExtra("packingList",packingList);
                    startActivity(myIntent);
                }
            });
        }

        public TextView getMyTextView() {
            return detailsTextView;
        }

        public void setMyTextView(TextView detailsTextView) {
            this.detailsTextView = detailsTextView;
        }
    }



    private class ListAdapter extends RecyclerView.Adapter {

        ListItemBinding binding = null;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            binding = ListItemBinding.inflate(getLayoutInflater());
            return new ListItemHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            String item = list.get(position);
            ((ListItemHolder)holder).getMyTextView().setText(item);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}