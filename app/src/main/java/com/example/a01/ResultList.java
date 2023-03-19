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
        textView.setText("Check who's already packaged to your trip!" );



        // getting data from database
        PlannerDatabase db = PlannerDatabase.getInstance(this);
        list = db.getUsersDAO().getAllUsersName();

        // TODO Implement recycler view instead of list view
        // using recycler view
        //setContentView(binding.getRoot());
        recyclerView = binding.travellersList;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter());


        // using list view
        /*ListView listView = binding.resultCheckList;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);*/


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
    private class ListItemHolder extends RecyclerView.ViewHolder {
        //private View itemView;
        private TextView detailsTextView;
        public ListItemHolder(ListItemBinding binding) {
            super(binding.getRoot());
            //super(view);
            //this.itemView = view;
            //myTextView = itemView.findViewById(R.id.my_text_view);
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

    // TODO Write method of ListAdapter for recycler view
    private class ListAdapter extends RecyclerView.Adapter {

        ListItemBinding binding = null;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            binding = ListItemBinding.inflate(getLayoutInflater());
//            View view = getLayoutInflater().inflate(R.layout.list_item, parent, false);
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