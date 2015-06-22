package com.example.shalom.lyrics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.melnykov.fab.FloatingActionButton;

public class MainActivity extends ActionBarActivity {

    // List view
    private ListView lv;


    // Listview Adapter
    ArrayAdapter<String> adapter;
    String products[];

    String textlol;
    String music;
    // Search EditText
    EditText inputSearch;
    private Toolbar toolbar;


    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.ic_music);

        /*ActionBar bar = getActionBar();

        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff6e40")));
        bar.setDisplayShowTitleEnabled(false);  // required to force redraw, without, gray color
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setIcon(R.drawable.lol);
        bar.setTitle(Html.fromHtml("<large><b>   Sing Along</b></large>"));

            // Listview Data
        /*String products[] = {"Dell Inspiron", "HTC One X", "HTC Wildfire S", "HTC Sense", "HTC Sensation XE",
                "iPhone 4S", "Samsung Galaxy Note 800",
                "Samsung Galaxy S3", "MacBook Air", "Mac Mini", "MacBook Pro"};*/
        final String adobe_products [] = getResources().getStringArray(R.array.adobe_products);



        lv = (ListView) findViewById(R.id.linear);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(lv);
        inputSearch = (EditText) findViewById(R.id.inputSearch);





        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, adobe_products);
        lv.setAdapter(adapter);
        Object arg0;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                int Low = 0;
                int High = 800;
                int R = r.nextInt(High-Low) + Low;
                String music=adobe_products[R];
                Intent i = new Intent(getApplicationContext(), second.class);
                // sending data to new activity
                i.putExtra("textlol", music);
                startActivity(i);


                Toast.makeText(v.getContext(), "" + music, Toast.LENGTH_SHORT).show();

            }


        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_LONG).show();


                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), second.class);
                // sending data to new activity
                i.putExtra("textlol", text);
                startActivity(i);
            }
        });


        /**
         * Enabling Search Filter
         *
         * */

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                MainActivity.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Toast.makeText(getApplicationContext(), "Nothing here :P",
                    Toast.LENGTH_LONG).show();

        }
        if (id == R.id.action_user) {
            Toast.makeText(getApplicationContext(), "aboutus",
                    Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), aboutus.class);
            // sending data to new activity


            startActivity(i);


        }
        return super.onOptionsItemSelected(item);
    }
}