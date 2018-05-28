package com.assignment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FutureListActivity extends AppCompatActivity {

    private ExpandableListView mExpandableListView;
    private final String JSON = "{\"values\":[{\"name\":\"Non Padded\",\"description\":\"The two layers provide you more support & coverage to prevent nipple show through\"},{\"name\":\"Wired\",\"description\":\"Underwired for essential support and shaping\"},{\"name\":\"3/4th Coverage\",\"description\":\"3/4th coverage reduces spillage & offers great support.\"},{\"name\":\"Polyester Spandex\",\"description\":\"Polyester Spandex retains shape and offers good stretch and recovery.\"},{\"name\":\"Wirefree\",\"description\":\"Designed with no underwire for all-day comfort and support\"},{\"name\":\"T-Shirt Bra\",\"description\":\"T-shirt bras have smooth, moulded and seamless padded cups for a no-show through look.\"},{\"name\":\"Nylon Spandex\",\"description\":\"Nylon infused with Lycra to ensure great stretch and recovery.\"}]}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mExpandableListView = findViewById(R.id.feature_list);
        try {
            ArrayList<Feature> features = new ArrayList<>();
            JSONObject o = new JSONObject(JSON);
            JSONArray arr = o.getJSONArray("values");
            for (int i = 0; i < arr.length(); i++) {
                Feature f = Feature.from(arr.getJSONObject(i).toString());
                features.add(f);
            }

            while(features.size() %3 !=0){
                features.add(null);
            }

            mExpandableListView.setAdapter(new FutureListAdapter(mExpandableListView, features));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_future_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
