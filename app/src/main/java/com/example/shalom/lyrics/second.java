package com.example.shalom.lyrics;


 import android.app.Activity;
 import android.content.Context;
 import android.content.Intent;
 import android.content.res.Resources;
 import android.os.Bundle;
 import android.text.method.ScrollingMovementMethod;
 import android.widget.TextView;

 import java.io.BufferedReader;
 import java.io.ByteArrayOutputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.Reader;

public class second extends Activity{
@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
this.setContentView(R.layout.second);

TextView txtProduct = (TextView) findViewById(R.id.product_label);
    txtProduct.setMovementMethod(new ScrollingMovementMethod());
    txtProduct.setTextSize((float) 16.00);
 
Intent i = getIntent();
// getting attached intent data
String products = i.getStringExtra("textlol");
String product=products.replaceAll(" ", "_").toLowerCase();
// displaying selected product name

    InputStream ins = getResources().openRawResource(
            getResources().getIdentifier(product,
                    "raw", getPackageName()));
    String sxml = readTextFile(ins);


txtProduct.setText(sxml);}



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