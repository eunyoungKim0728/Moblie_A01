package com.example.a01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

import com.example.a01.databinding.PackingBinding;


public class Packing extends AppCompatActivity{

    public static final String TAG= "RestaurantList";
    private String name;
    private String ch1;
    private String ch2;
    private String ch3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "In OnCreate");

        PackingBinding binding = DataBindingUtil.setContentView(this,R.layout.packing);

        Button mainBtn = binding.mainButton;
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Go to MainActivity");
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        Button itineraryBtn = binding.backButton;
        itineraryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Go to Itinerary");
                startActivity(new Intent(getApplicationContext(),Itinerary.class));
            }
        });

        EditText nameEdit = binding.nameEdit;
        Button submitBtn = binding.submitBtn;
        TextView nameTextView = binding.nameTextView;

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "In OnClick Text");
                name = nameEdit.getText().toString();
                nameTextView.setText("Hello! "+name);
                nameEdit.setText("");
                // close the keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        nameEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent event) {
                Log.d(TAG, "In OnEditorAction");
                if (i == EditorInfo.IME_ACTION_DONE || i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    InputMethodManager imm = (InputMethodManager)textView.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
                }
                return true;
            }

        });

        View packingLayout = binding.PackingLayout;
        packingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "In OnClick Input Name");
                InputMethodManager imm = (InputMethodManager)nameEdit.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(nameEdit.getWindowToken(), 0);
            }
        });

        CheckBox checkBox1 = binding.checkBox1;

        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Checked Presto Card");
                if(checkBox1.isChecked()){
                    ch1 = checkBox1.getText().toString();
                }

            }
        });


        CheckBox checkBox2 = binding.checkBox2;
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Checked Wallet");
                if(checkBox2.isChecked()){
                    ch2 = checkBox2.getText().toString();
                }
            }
        });


        CheckBox checkBox3 = binding.checkBox3;
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Checked Toiletries");
                if(checkBox3.isChecked()){
                    ch3 = checkBox3.getText().toString();
                }
            }
        });


        Button listBtn = binding.listButton;
        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Go to List");

                Intent intent = new Intent(getBaseContext(), ResultList.class);
                intent.putExtra("name", name);
                intent.putExtra("checkBox1", ch1);
                intent.putExtra("checkBox2", ch2);
                intent.putExtra("checkBox3", ch3);

                startActivity(intent);
            }
        });


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