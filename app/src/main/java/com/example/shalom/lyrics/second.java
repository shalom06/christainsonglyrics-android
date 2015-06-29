package com.example.shalom.lyrics;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;



import java.io.ByteArrayOutputStream;
import java.io.InputStream;


public class second extends Activity{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.second);
        Button button = (Button) findViewById(R.id.button);

        TextView txtProduct = (TextView) findViewById(R.id.product_label1);
        TextView textView = (TextView) findViewById(R.id.textView);

        txtProduct.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        Intent i = getIntent();
// getting attached intent data
        String products = i.getStringExtra("textlol");
        String product=products.replaceAll(" ", "_").toLowerCase();
// displaying selected product name

        InputStream ins = getResources().openRawResource(
                getResources().getIdentifier(product,
                        "raw", getPackageName()));
        String sxml = readTextFile(ins);
        final String lyrics = sxml.toString();

        String lyrics1 = lyrics.replace("’", "'");


                txtProduct.setText(lyrics1);
        textView.setText(products);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = lyrics;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));


            }
        });
    }




    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }





}
