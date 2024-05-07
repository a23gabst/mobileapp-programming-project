package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener{
    private final String JSON_URL="https://mobprog.webug.se/json-api?login=a23gabst";

    private ArrayList<MusikInstrument> instruments= new ArrayList<>();
    private ArrayList<RecyclerViewItem> recyclerViewItems=new ArrayList<>();
    private RecyclerViewAdapter adapter;
    private Gson gson;
    private WebView myWebView;
    private RecyclerView view;
    private AppBarLayout appBarLayout;
    private ConstraintLayout constraintLayout;
    public void showExternalWebPage(){
        // TODO: Add your code for showing external web page here
        myWebView.loadUrl("file:///android_res/layout/activity_main.xml");
    }

    public void showInternalWebPage(){
        // TODO: Add your code for showing internal web page here
        //myWebView.loadUrl("file:///android_asset/about.html");
        Intent intent = new Intent(MainActivity.this, InternalWebSide.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myWebView = findViewById(R.id.my_webview);
        myWebView.setVisibility(View.GONE);
        myWebView.setWebViewClient(new WebViewClient()); // Do not open in Chrome!
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(false);
        myWebView.loadUrl("file:///android_res/layout/activity_main.xml");

        gson=new Gson();

        for (int i=0;i<instruments.size();i++) {
            Log.d("Injera", instruments.get(i).toString());
            MusikInstrument instrument = instruments.get(i);
            recyclerViewItems.add(new RecyclerViewItem(instrument.getName(), instrument.getGenre(), instrument.getCost()));
        }


        adapter = new RecyclerViewAdapter(this, instruments, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(MusikInstrument item) {
                String message = "Name: " + item.getName() + "\nGenre: " + item.getGenre() + "\nCost: " + item.getCost();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("artist", item.getArtist());
                intent.putExtra("timeLine", item.getTimeLine());
                intent.putExtra("origin", item.getOrigin());
                startActivity(intent);
            }
        });

        view = findViewById(R.id.recycler_view1);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);

        new JsonTask(this).execute(JSON_URL);
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

    @Override
    public void onPostExecute(String json) {
        Log.d("KLoser", json);
        Type type = new TypeToken<List<MusikInstrument>>() {}.getType();
        List<MusikInstrument> listOfInstruments = gson.fromJson(json, type);
        Log.d("OnePiece1", "" + listOfInstruments.get(0).getTimeLine());
        instruments.clear();
        recyclerViewItems.clear();
        adapter.updateData(listOfInstruments);
    }
}
