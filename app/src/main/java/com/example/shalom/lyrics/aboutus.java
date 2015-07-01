package com.example.shalom.lyrics;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

/**
 * Created by Shalom on 6/20/2015.
 */
public class aboutus extends Activity{


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);
        ShimmerTextView  tv = (ShimmerTextView) findViewById(R.id.textView2);
         Shimmer shimmer= new Shimmer();
        getWindow().setStatusBarColor(getResources().getColor(R.color.pur));
        shimmer.start(tv);



}}
