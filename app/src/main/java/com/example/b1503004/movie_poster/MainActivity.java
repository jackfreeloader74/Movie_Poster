package com.example.b1503004.movie_poster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static EditText editText;
    static EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button b;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b=(Button)this.findViewById(R.id.login_button);
        b.setOnClickListener(this);

        final Button register_button = (Button)this.findViewById(R.id.register_button);
        register_button.setOnClickListener(this);
    }

    public void onClick(View v){

        if (v.getId()  == R.id.register_button){
            Intent in = new Intent(this, RegisterActivity.class);
            startActivity(in);
        }


        else if(v.getId() == R.id.login_button){
            Intent in;

            in = new Intent(this, FindPoster_Activity.class);
            startActivity(in);
        }

    }
}
