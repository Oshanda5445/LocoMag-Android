package com.example.shan.myapplication;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ~SHAN~ on 12/10/2017.
 */

public class RegisterRequest extends StringRequest {

    private static  final String REGISTER_REQUEST_URL = "http://10.0.2.2/Android/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String driver_id, String username, String password, String mobile, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("driver_id", driver_id);
        params.put("username", username);
        params.put("password", password);
        params.put("mobile", mobile);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
