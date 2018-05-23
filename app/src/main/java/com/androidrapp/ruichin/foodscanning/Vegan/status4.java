package com.androidrapp.ruichin.foodscanning.Vegan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.androidrapp.ruichin.foodscanning.R;

public class status4 extends AppCompatActivity {

    TextView textView, textView2, textView3, textView4, textView5, textView6;

    String str1, str2, str3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status4);

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);

        Intent i = getIntent();
        str1 = getIntent().getStringExtra("barcode");
        str2 = getIntent().getStringExtra("status");
        str3 = getIntent().getStringExtra("companyInfo");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView4.setText(str1);
                textView5.setText(str2);
                textView6.setText(str3);
            }
        });


    }


}

