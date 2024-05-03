package com.example.project;

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
        myWebView.loadUrl("file:///android_asset/about.html");
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
        webSettings.setUseWideViewPort(true);
        myWebView.loadUrl("file:///android_res/layout/activity_main.xml");


        gson=new Gson();


        instruments.add(new MusikInstrument("1","GabeLoginValue", "Guitar", "Rock", 10000));
        instruments.add(new MusikInstrument("","Gabe2LoginValue", "Piano", "Jazz", 200000));
        instruments.add(new MusikInstrument("3","Gabe3LoginValue", "Ukulele", "Reggae", 100));

        for (int i=0;i<instruments.size();i++) {
            Log.d("Injera", instruments.get(i).toString());
            recyclerViewItems.add(new RecyclerViewItem(instruments.get(i).toString()));
        }


        adapter=new RecyclerViewAdapter(this, recyclerViewItems, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(RecyclerViewItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
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
            view.setVisibility(View.VISIBLE);
            myWebView.setVisibility(View.GONE);
            showExternalWebPage();
            return true;
        }

        if (id == R.id.action_internal_web) {
            Log.d("==>","Will display internal web page");
            myWebView.setVisibility(View.VISIBLE);
            view.setVisibility(View.GONE);
            appBarLayout=findViewById(R.id.appBarLayout);
            constraintLayout = findViewById(R.id.constraintLayout);
            // Ändra constrainten för WebView

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);

            // Uppdatera constrainten för WebView för att placera den under appBarLayout
            constraintSet.connect(myWebView.getId(), ConstraintSet.TOP, appBarLayout.getId(), ConstraintSet.BOTTOM);
            constraintSet.applyTo(constraintLayout); // Tillämpa de uppdaterade constrainterna på ConstraintLayout
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
        instruments.clear();
        recyclerViewItems.clear();
        instruments.addAll(listOfInstruments);
        for(int i=0;i<instruments.size();i++) {
            Log.d("OnePiece", instruments.get(i).toString());
            recyclerViewItems.add(new RecyclerViewItem(instruments.get(i).toString()));
        }
        adapter.notifyDataSetChanged();
    }
}
