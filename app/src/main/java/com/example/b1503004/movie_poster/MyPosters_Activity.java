package com.example.b1503004.movie_poster;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MyPosters_Activity extends AppCompatActivity implements View.OnClickListener {

    Button b;
    Button d;
    Button showPosters, addMovie;
    TextView prefDisp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posters_);

        b = (Button) this.findViewById(R.id.findPoster_button);
        b.setOnClickListener(this);

        d = (Button)this.findViewById(R.id.settings_button);
        d.setOnClickListener(this);



        addMovie = (Button)this.findViewById(R.id.addMovie);
        addMovie.setOnClickListener(this);




    }

    public void onClick(View v) {
        Intent in;
        if(v == b) {
            in = new Intent(this, SearchPoster.class);
            startActivity(in);
        }

        else if (v == d) {
            in = new Intent(this, SettingsActivity.class);
            startActivity(in);
        }

        else if (v == addMovie){
            in = new Intent(this, AddMovie.class);
            startActivity(in);
        }



    }



}
