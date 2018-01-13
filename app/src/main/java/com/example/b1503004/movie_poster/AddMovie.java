package com.example.b1503004.movie_poster;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMovie extends AppCompatActivity implements View.OnClickListener{

    EditText etTitle, etGenre;
    Context context;
    MovieDbHelper movieDbHelper;
    SQLiteDatabase sqLiteDatabase;
    Button addMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        addMovie = (Button)this.findViewById(R.id.addMovie);
        addMovie.setOnClickListener(this);
    }

    public void onClick(View v){

        if(v == addMovie){
            String title = etTitle.getText().toString();
            String genre = etGenre.getText().toString();


            movieDbHelper = new MovieDbHelper(context);
            sqLiteDatabase = movieDbHelper.getWritableDatabase();
            movieDbHelper.addInformation(title, genre, sqLiteDatabase);

            Toast.makeText(getBaseContext(), "Movie Saved", Toast.LENGTH_LONG).show();
            movieDbHelper.close();


        }

    }
}

