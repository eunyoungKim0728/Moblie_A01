package com.example.a01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a01.databinding.ActivityResultListBinding;
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

        Button mainBtn = binding.mainListBtn;
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Go to MainActivity");
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


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

    }


    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "In OnCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.menu_1,menu);
        MenuBuilder m = (MenuBuilder)menu;
        m.setOptionalIconsVisible(true);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "In OnOptionsItemSelected");
        boolean result =false;
        Intent intent = null;
        switch(item.getItemId()) {
            case R.id.Toronto:
                intent=new Intent(this,Itinerary.class);
                startActivity(intent);
                result = true;
                break;
            case R.id.Quebec:
                intent=new Intent(this,NoItinerary1.class);
                startActivity(intent);
                result = true;
                break;
            case R.id.Vancouver:
                intent=new Intent(this,NoItinerary1.class);
                startActivity(intent);
                result = true;
                break;
            case R.id.Main:
                intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                result = true;
                break;

            default:
                result = super.onOptionsItemSelected(item);
                break;

        }

        return result;
    }
}