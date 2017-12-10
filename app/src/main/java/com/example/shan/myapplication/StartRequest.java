package com.example.shan.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ~SHAN~ on 12/9/2017.
 */

public class StartRequest extends StringRequest {
    private static  final String REGISTER_REQUEST_URL = "http://10.0.2.2/Android/Start.php";
    private Map<String, String> params;

    public StartRequest(String route_ID, String driver_ID, String guard_ID, String loco_ID, String date, String start_time, String any_failures, Response.Listener<String> listener){
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("route_ID", route_ID);
        params.put("driver_ID", driver_ID);
        params.put("guard_ID", guard_ID);
        params.put("loco_ID", loco_ID);
        params.put("date", date);
        params.put("start_time", start_time);
        params.put("any_failures", any_failures);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

