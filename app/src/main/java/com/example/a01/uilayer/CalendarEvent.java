package com.example.a01.uilayer;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.a01.R;
import com.example.a01.databinding.AddCalendarBinding;

public class CalendarEvent extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AddCalendarBinding binding = DataBindingUtil.setContentView(this, R.layout.add_calendar);

        // binding edit text for tile, location and description
        EditText eventTitle = binding.eventTitle;
        EditText eventLocation = binding.eventLocation;
        EditText eventDescription = binding.eventDescription;

        // button to add event to calendar
        Button addBtn = binding.addBtn;
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get strings from edit text
                String title = eventTitle.getText().toString();
                String location = eventLocation.getText().toString();
                String description = eventDescription.getText().toString();

                // check if user filled all the fields
                if (!title.isEmpty() && !location.isEmpty() && !description.isEmpty()) {

                    // instantiate new intent to get user input and send to calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);

                    // user calendar contract to set the data with information provided by the user
                    intent.setData(CalendarContract.Events.CONTENT_URI);

                    // put the values get from the edit text
                    intent.putExtra(CalendarContract.Events.TITLE, title);
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, location);
                    intent.putExtra(CalendarContract.Events.DESCRIPTION, description);

                    // check if the application supports using the calendar
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(CalendarEvent.this, "Application not supported",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // if not all fields are filled show a toast
                    Toast.makeText(CalendarEvent.this, "Please fill the fields before add event",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // button to go back to itinerary screen
        Button itineraryBtn = binding.backButton;
        itineraryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Itinerary.class));
            }
        });
    }

}
