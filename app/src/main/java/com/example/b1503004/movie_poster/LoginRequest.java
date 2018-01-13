package com.example.b1503004.movie_poster;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by marc on 05/01/2018.
 */

//References the php when user tries to login

public class LoginRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "https://textbookapp.000webhostapp.com/Login.php";

    private Map<String, String> params;

    public LoginRequest(String username, String password, Response.Listener<String> Listener){
        super(Method.POST, LOGIN_REQUEST_URL, Listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams(){

        return params;
    }
}

