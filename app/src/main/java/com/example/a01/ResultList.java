package com.example.a01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.a01.databinding.PackingBinding;
import com.example.a01.databinding.ResultListBinding;

import java.util.ArrayList;
import java.util.List;

public class ResultList extends AppCompatActivity {
    public static final String TAG= "ResultList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "In OnCreate");

        ResultListBinding binding = DataBindingUtil.setContentView(this,R.layout.result_list);

        ListView listView = binding.tripList;

        List<String> list = new ArrayList<>();
        Intent it = getIntent();
        String value1 = it.getStringExtra("checked1");
        String value2 = it.getStringExtra("checked2");
        String value3 = it.getStringExtra("checked3");
        String value4 = it.getStringExtra("checked4");
        String value5 = it.getStringExtra("checked5");
        String value6 = it.getStringExtra("checked6");
        String name = it.getStringExtra("name");

        list.add(value1);
        list.add(value2);
        list.add(value3);
        list.add(value4);
        list.add(value5);
        list.add(value6);






    }
}
