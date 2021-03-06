package com.androidrapp.ruichin.foodscanning.Muslim;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.androidrapp.ruichin.foodscanning.R;

import org.json.JSONException;
import org.json.JSONObject;

public class result extends AppCompatActivity {


    TextView txtv1,txtv2;
    String str;
    Button btnCheck;
    ProgressDialog pD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtv1 = (TextView)findViewById(R.id.txtv1);
        txtv2 = (TextView)findViewById(R.id.txtv2);



        btnCheck = (Button)findViewById(R.id.btnCheck) ;

        Intent i = getIntent();
        str = getIntent().getStringExtra("barcode");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtv1.setText(str);
            }
        });


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable run = new Runnable() {
                    @Override
                    public void run() {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pD = new ProgressDialog(result.this);
                                pD.setMessage("Loading...");
                                pD.setProgressStyle(ProgressDialog.STYLE_SPINNER);// Progress Dialog Style Spinner
                                pD.show();
                            }
                        });


                        final String barcode = getIntent().getStringExtra("barcode");

                        Response.Listener<String>responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean SUCCESS = jsonResponse.getBoolean("SUCCESS");

                                    if (SUCCESS) {
                                        String barcode = jsonResponse.getString("barcode");
                                        String status = jsonResponse.getString("status");
                                        String companyInfo = jsonResponse.getString("companyInfo");

                                        Intent i = new Intent(result.this, com.androidrapp.ruichin.foodscanning.Muslim.status.class);
                                        i.putExtra("barcode",barcode);
                                        i.putExtra("status",status);
                                        i.putExtra("companyInfo",companyInfo);
                                        startActivity(i);

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                pD.dismiss();
                                            }
                                        });



                                    } else if(!SUCCESS)
                                    {

                                        String barcode = jsonResponse.getString("barcode");
                                        String status = jsonResponse.getString("status");
                                        String companyInfo = jsonResponse.getString("companyInfo");

                                        Intent i = new Intent(result.this,status2.class);
                                        i.putExtra("barcode",barcode);
                                        i.putExtra("status",status);
                                        i.putExtra("companyInfo",companyInfo);
                                        startActivity(i);

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                pD.dismiss();
                                            }
                                        });


                                    }





                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        };
                        dataRequest dataRequest = new dataRequest(barcode,responseListener);
                        RequestQueue queue = Volley.newRequestQueue(result.this);
                        queue.add(dataRequest);

                    }
                };
                Thread thr = new Thread(run);
                thr.start();

                            }
        });
    }

}
