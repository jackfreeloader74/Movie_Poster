package com.example.b1503004.movie_poster;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Movie;
import android.util.Log;

/**
 * Created by marc on 12/01/2018.
 */

public class MovieDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MOVIE_INFO.DB";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_QUERY =
    "CREATE TABLE "+ MovieInfo.NewMovieInfo.TABLE_NAME +"(" + MovieInfo.NewMovieInfo.MOVIE_TITLE + " TEXT,"
            + MovieInfo.NewMovieInfo.MOVIE_GENRE + " TEXT;";



    public MovieDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "Database created");
    }


    //Inserting Movies

    public void addInformation(String title, String genre,SQLiteDatabase db){

        ContentValues contentValues = new ContentValues();

        contentValues.put(MovieInfo.NewMovieInfo.MOVIE_TITLE, title);
        contentValues.put(MovieInfo.NewMovieInfo.MOVIE_GENRE, genre);

        db.insert(MovieInfo.NewMovieInfo.TABLE_NAME, null, contentValues);

        Log.e("DATABASE OPERATIONS", "Movie Added");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
