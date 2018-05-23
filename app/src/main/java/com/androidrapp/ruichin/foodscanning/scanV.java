package com.androidrapp.ruichin.foodscanning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.androidrapp.ruichin.foodscanning.Vegan.result2;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class scanV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_v);

        Runnable runV = new Runnable() {
            @Override
            public void run() {
                IntentIntegrator integrator = new IntentIntegrator(scanV.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Place Your Barcode Here.");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        };
        Thread thrV = new Thread(runV);
        thrV.start();


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(scanV.this, "Cancelled", Toast.LENGTH_LONG).show();
                    }
                });

            } else {
                Log.d("MainActivity", "Scanned");
                Intent i = new Intent(scanV.this,result2.class);
                i.putExtra("barcode",result.getContents());
                startActivity(i);

            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
