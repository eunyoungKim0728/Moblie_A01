package com.example.a01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a01.databinding.ViewJsonfileBinding;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class ViewJSONFile extends AppCompatActivity {
    public static final String TAG= "Summary Page";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_jsonfile);

        ViewJsonfileBinding binding = DataBindingUtil.setContentView(this, R.layout.view_jsonfile);

        TextView cityTV = binding.cities;
        TextView priceTV = binding.price;
        TextView guideTV = binding.guide;
        TextView destTV = binding.destination;
        Button homeBtn = binding.homeBtn;
        Intent musicService = new Intent(this,MusicService.class);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Home button OnClick");
                stopService(musicService);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });

        try {
            InputStream inputStream = getAssets().open("db.json");
            BufferedReader reader = new BufferedReader((new InputStreamReader(inputStream)));
            String line = null;

            StringBuffer buffer = new StringBuffer();

            line = reader.readLine();
            while (line != null) {
                buffer.append(line + "\n");
                line = reader.readLine();
            }
            String jsonData = buffer.toString();

            JSONObject jsonObj = new JSONObject(jsonData);
            String name = jsonObj.getString("name");
            String price = jsonObj.getString("price");
            String guide = jsonObj.getString("tour guide");
            String spot = jsonObj.getString("spot");

            cityTV.setText(name);
            priceTV.setText(price);
            guideTV.setText(guide);
            destTV.setText(spot);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
