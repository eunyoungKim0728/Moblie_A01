package com.example.a01.json;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a01.MainActivity;
import com.example.a01.R;
import com.example.a01.databinding.ViewJsonfileBinding;
import com.example.a01.services.MusicService;

import android.util.Log;

public class ViewJSONFile extends AppCompatActivity {

    public static final String TAG= "ViewJSONFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_jsonfile);
        Log.d(TAG, "OnCreate");

        ViewJsonfileBinding binding = DataBindingUtil.setContentView(this, R.layout.view_jsonfile);

        TextView cityTV = binding.cities;
        TextView priceTV = binding.price;
        TextView guideTV = binding.guide;
        TextView destTV = binding.destination;
        Intent musicService = new Intent(this, MusicService.class);

        Button homeBtn = binding.homeBtn;
        homeBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.d(TAG, "Home button OnClick");
               stopService(musicService);
               startActivity(new Intent(getApplicationContext(), MainActivity.class));
               stopService(musicService);
           }
        });

        /*try {
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
        }*/
    }
}
