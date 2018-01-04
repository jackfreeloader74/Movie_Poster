package com.example.b1503004.movie_poster;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1503004 on 08/12/2017.
 */

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "";

    private Map<String,String>params;

    public RegisterRequest(String name, String username, String password, Response.Listener<String> listener){

        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
    }

    public Map<String, String> getParams(){
        return params;
    }
}
