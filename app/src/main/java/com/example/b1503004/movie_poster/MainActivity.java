package com.example.b1503004.movie_poster;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.b1503004.movie_poster.R.id.etPassword;
import static com.example.b1503004.movie_poster.R.id.etUsername;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static EditText etUsername;
    static EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button b;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b=(Button)this.findViewById(R.id.login_button);
        b.setOnClickListener(this);

        final Button register_button = (Button)this.findViewById(R.id.register_button);
        register_button.setOnClickListener(this);


        etPassword = (EditText)this.findViewById(R.id.etPassword);

        etUsername = (EditText)this.findViewById(R.id.etUsername);
    }

    public void onClick(View v){

        final String username = etUsername.getText().toString();
        final String password = etPassword.getText().toString();


        if (v.getId()  == R.id.login_button){

            Response.Listener<String> responseListener = new Response.Listener<String>(){
                @Override
                public void onResponse(String Response){
                    try {
                        JSONObject jsonResponse = new JSONObject(Response);
                        boolean success = jsonResponse.getBoolean("success");

                        if(success){
                            int user_id = jsonResponse.getInt("user_id");
                            Intent in = new Intent(MainActivity.this, FindPoster_Activity.class);
                            in.putExtra("user_id", user_id);

                            startActivity(in);
                        }

                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setMessage("LoginFailed").setNegativeButton("Retry", null).create().show();
                        }
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(loginRequest);

        } else if(v.getId() == R.id.register_button){
            Intent in;

            in = new Intent(this, RegisterActivity.class);
            startActivity(in);
        }

    }
}
