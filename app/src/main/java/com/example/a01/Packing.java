package com.example.a01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
    private String ch4;
    private String ch5;
    private String ch6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "In OnCreate");

        PackingBinding binding = DataBindingUtil.setContentView(this,R.layout.packing);

        Button nextBtn = binding.nextBtn;
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Go to ViewJSONFileActivity");
                startActivity(new Intent(getApplicationContext(),ViewJSONFile.class));
            }
        });

        Button itineraryBtn = binding.backButton;
        itineraryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Go to MainActivity");
                startActivity(new Intent(getApplicationContext(),Packing.class));
            }
        });


        Button submitBtn = binding.submitBtn;
        EditText nameEdit = binding.nameEdit;
        Button listBtn = binding.listBtn;
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

        CheckBox checkBox4 = binding.checkBox4;
        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Checked Toiletries");
                if(checkBox4.isChecked()){
                    ch4 = checkBox4.getText().toString();
                }
            }
        });

        CheckBox checkBox5 = binding.checkBox5;
        checkBox5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Checked Toiletries");
                if(checkBox5.isChecked()){
                    ch5 = checkBox5.getText().toString();
                }
            }
        });

        CheckBox checkBox6 = binding.checkBox6;
        checkBox6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Checked Toiletries");
                if(checkBox6.isChecked()){
                    ch6 = checkBox6.getText().toString();
                }
            }
        });

        // Instantiate the database
        getPlannerDatabaseFromRoom();

        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Add user information to database");

                long cityId = 1001; // Toronto trip ID

                Log.d(TAG, "Get packing list for user and concatenate in one string");

                String packingList = "";

                if(checkBox1 != null) {
                    packingList += ch1 + "\n";
                }
                if(checkBox2 != null) {
                    packingList += ch2 + "\n";
                }
                if(checkBox3 != null) {
                    packingList += ch3 + "\n";
                }
                if(checkBox4 != null) {
                    packingList += ch4 + "\n";
                }
                if(checkBox5 != null) {
                    packingList += ch5 + "\n";
                }
                if(checkBox6 != null) {
                    packingList += ch6 + "\n";
                }

                PlannerDatabase db = PlannerDatabase.getInstance(getApplicationContext());
                db.getUsersDAO().insert(new Users(name));

                long userId = db.getUsersDAO().getUserIDByName(name);

                db.getTripsDAO().insert(new Trips(cityId, userId, packingList));

                Intent intent = new Intent(getBaseContext(), ResultList.class);

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

    // Instantiate the database using Room
    private void getPlannerDatabaseFromRoom() {

        PlannerDatabase database = PlannerDatabase.getInstance(this);
        new PopulateDatabase().execute(database);
    }

    // Populate database with initial values for cities
    // Other tables will be populate when the user add new trips
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
            }

            return db;
        }

        // TODO Check if we need to have onPostExecute
        @Override
        protected void onPostExecute(PlannerDatabase db) {

        }

    }
}