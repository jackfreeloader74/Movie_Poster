package com.example.b1503004.movie_poster;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.b1503004.movie_poster.R.layout.activity_main;

public class FindPoster_Activity extends AppCompatActivity implements View.OnClickListener {

    Button find_poster, myPoster_button, settings_button, butt, jsonButt, xmlButt;
    TextView output;
    MyHttpRequest task, task2;
    Document doc;
    JSONObject jdoc;
    JSONArray jarr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_poster_);

        find_poster = (Button)this.findViewById(R.id.findPoster_button);
        find_poster.setOnClickListener(this);

        myPoster_button = (Button)this.findViewById(R.id.myPoster_button);
        myPoster_button.setOnClickListener(this);

        settings_button = (Button)this.findViewById(R.id.settings_button);
        settings_button.setOnClickListener(this);

        butt = (Button)this.findViewById(R.id.butt);
        butt.setOnClickListener(this);

        jsonButt = (Button)this.findViewById(R.id.jsonButt);
        jsonButt.setOnClickListener(this);

        xmlButt = (Button)this.findViewById(R.id.xmlButt);
        xmlButt.setOnClickListener(this);

        output = (TextView)this.findViewById(R.id.output);
    }



    @Override
    public void onClick(View view) {
        String str;
        NodeList n;

        if(task == null){
            task = new MyHttpRequest();
            task.execute("https://boardgamegeek.com/xmlapi/collection/drakkos");
            task2 = new MyHttpRequest();
            task2.execute("https://bgg-json.azurewebsites.net/collection/drakkos");
        }

        else if(view==butt){
            str = task.getReturnEntry();

            output.setText(str);
        }

        else if(view == xmlButt){
            doc = task.getResultsAsDom();

            if(doc != null){

                str = "";

                n = doc.getElementsByTagName("item");

                for(int i = 0; i< n.getLength(); i++){
                    Element ele = (Element)n.item(i);
                    NodeList n2 = ele.getElementsByTagName("name");
                    Element ele2 = (Element)n2.item(0);
                    str += ele2.getFirstChild().getNodeValue() + "\n";
                }

                output.setText(str);
                output.setMovementMethod(new ScrollingMovementMethod());

            }
            else {
                output.setText("Still loading DOM");
            }
        }

        else if(view==jsonButt){
            JSONObject tmp;
            try{
                jarr = task2.getResultsAsJSON();

                if(jarr==null){
                    output.setText("Still loading JSON");
                    return;
                }

                str = "";

                for(int i = 0; i < jarr.length(); i++){
                    tmp = jarr.getJSONObject(i);
                    str += tmp.getString("name") + "\n";
                }
            } catch (JSONException ex) {
                output.setText("Some horror: " + ex.getMessage());
            }
        }



    }
}




