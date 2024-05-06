package com.example.project;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Log.d("==>1", extras.toString());
            String artist = extras.getString("artist");
            String origin = extras.getString("origin");
            int timeLine = extras.getInt("timeLine");
            Log.d("SendData", artist +" " +timeLine +" "+ origin);

            TextView artistText=findViewById(R.id.textView12);
            TextView timeLineText=findViewById(R.id.textView11);
            TextView originText=findViewById(R.id.textView10);

            artistText.setText(artist);
            timeLineText.setText(String.valueOf(timeLine));
            originText.setText(origin);

            // Do something with the name and number
        }

    }
}