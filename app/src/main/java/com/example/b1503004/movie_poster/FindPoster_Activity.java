package com.example.b1503004.movie_poster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.b1503004.movie_poster.R.layout.activity_main;

public class FindPoster_Activity extends AppCompatActivity implements View.OnClickListener{
    Button b;
    Button c;
    Button d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_poster_);

        b = (Button)this.findViewById(R.id.login_butt);
        b.setOnClickListener(this);

        c = (Button)this.findViewById(R.id.myPoster_button);
        c.setOnClickListener(this);

        d = (Button)this.findViewById(R.id.settings_button);
        d.setOnClickListener(this);
    }


    public void onClick(View v){
        Intent in;
        if(v == b){
            in = new Intent(this, MainActivity.class);
            startActivity(in);
        }

        if (v == c){
            in = new Intent(this, MyPosters_Activity.class);
            startActivity(in);

        }

        if (v == d) {
            in = new Intent(this, SettingsActivity.class);
            startActivity(in);
        }



    }
}
