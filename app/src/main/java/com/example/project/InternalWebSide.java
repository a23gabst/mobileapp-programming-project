package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
public class InternalWebSide extends AppCompatActivity {
    private WebView internalWebView;

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
}