package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
public class InternalWebSide extends AppCompatActivity {
    private WebView internalWebView;
    public void showExternalWebPage(){
        // TODO: Add your code for showing external web page here
        Intent intent = new Intent(InternalWebSide.this, MainActivity.class);
        startActivity(intent);
    }

    public void showInternalWebPage(){
        // TODO: Add your code for showing internal web page here
        //myWebView.loadUrl("file:///android_asset/about.html");
        internalWebView.loadUrl("file:///android_asset/about.html");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_web_side);

        // Hitta WebView i layouten
        internalWebView = findViewById(R.id.my_webview);

        // Konfigurera WebView-inställningar
        WebSettings webSettings = internalWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(false);

        // Ladda den interna webbsidan
        internalWebView.loadUrl("file:///android_asset/about.html");
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