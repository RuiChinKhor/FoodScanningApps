package com.androidrapp.ruichin.foodscanning.Vegan;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ruichin on 12/5/2018.
 */

public class dataRequest2 extends StringRequest {
    private static final String DATA_REQUEST_URL = "https://ahchin0428.000webhostapp.com/ProductInfoV.php";
    private Map<String, String> params;

    public dataRequest2(String barcode, Response.Listener<String> listener){
        super(Request.Method.POST, DATA_REQUEST_URL, listener,null);
        params = new HashMap<>();
        params.put("barcode", barcode);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
