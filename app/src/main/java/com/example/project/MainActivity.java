package com.example.project;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener{
    private final String JSON_URL="https://mobprog.webug.se/json-api?login=a23gabst";

    private ArrayList<MusikInstrument> instruments= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        instruments.add(new MusikInstrument("1","GabeLoginValue", "Guitar", "Rock", 10000));
        instruments.add(new MusikInstrument("2","Gabe2LoginValue", "Piano", "Jazz", 200000));
        instruments.add(new MusikInstrument("3","Gabe3LoginValue", "Ukulele", "Reggae", 100));

        for (int i=0;i<instruments.size();i++) {
            Log.d("Injera", instruments.get(i).toString());
        }

        new JsonTask(this).execute(JSON_URL);
    }

    @Override
    public void onPostExecute(String json) {

    }
}
