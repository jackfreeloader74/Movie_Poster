package com.example.b1503004.movie_poster;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1503004 on 08/12/2017.
 */

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "https://textbookapp.000webhostapp.com/Register.php";

    private Map<String,String>params;

    public RegisterRequest(String name, String username, String password, String email, Response.Listener<String> listener){

        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("email", email);


    }

    public Map<String, String> getParams(){

        return params;
    }
}
