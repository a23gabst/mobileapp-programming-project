package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private WebView detailWebview;
    public void showExternalWebPage(){
        // TODO: Add your code for showing external web page here
        Intent intent = new Intent(DetailActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void showInternalWebPage(){
        // TODO: Add your code for showing internal web page here
        //myWebView.loadUrl("file:///android_asset/about.html");
        Intent intent = new Intent(DetailActivity.this, InternalWebSide.class);
        startActivity(intent);
    }

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

            detailWebview=findViewById(R.id.detail_webview);
            // Hitta WebView i layouten
            detailWebview = findViewById(R.id.detail_webview);

            // Konfigurera WebView-inställningar
            WebSettings webSettings = detailWebview.getSettings();
            webSettings.setJavaScriptEnabled(true);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId(); // Hämta ID för det valda menyalternativet

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_external_web) {
            Log.d("==>","Will display external web page");
            showExternalWebPage();
            return true;
        }

        if (id == R.id.action_internal_web) {
            Log.d("==>","Will display internal web page");
            showInternalWebPage();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}