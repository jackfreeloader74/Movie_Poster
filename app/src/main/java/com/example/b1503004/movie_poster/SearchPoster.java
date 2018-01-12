package com.example.b1503004.movie_poster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SearchPoster extends AppCompatActivity implements View.OnClickListener {

    Button find_poster, myPosters, settings_button, butt, jsonButt, xmlButt;
    TextView output;
    EditText etMovie;
    MyHttpRequest task, task2;
    Document doc;
    JSONObject jdoc;
    JSONArray jarr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_poster);

        jsonButt = (Button)this.findViewById(R.id.cmdJson);
        jsonButt.setOnClickListener(this);

        output = (TextView)this.findViewById(R.id.txtOutput);

        etMovie = (EditText)this.findViewById(R.id.etMovie);

        butt = (Button)this.findViewById(R.id.cmdRequest);
        butt.setOnClickListener(this);


        myPosters = (Button)this.findViewById(R.id.myPosters);
        myPosters.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        {

            String str;
            NodeList n;

            if (task == null) {

                String search = etMovie.getText().toString();
                https://api.themoviedb.org/3/search/movie?api_key=a12071111b5bcd7d74000849b4b7fa7f&language=en-US&query=&page=1&include_adult=false
                task = new MyHttpRequest();
                task.execute("https://boardgamegeek.com/xmlapi/collection/drakkos");

                task2 = new MyHttpRequest();
                task2.execute("https://bgg-json.azurewebsites.net/collection/drakkos");

            }

            if (view == butt) {
                str = task2.getReturnEntry();

                output.setText(str);
            }
            else if (view== xmlButt){
                doc = task.getResultAsDom();

                if(doc != null){
                    str = "";

                    n = doc.getElementsByTagName("item");

                    for (int i = 0; i < n.getLength(); i++) {
                        Element ele = (Element)n.item(i);
                        NodeList n2 = ele.getElementsByTagName ("title");
                        Element ele2 = (Element)n2.item (0);
                        str += ele2.getFirstChild().getNodeValue() + "\n";
                    }

                    output.setText(str);
                    output.setMovementMethod(new ScrollingMovementMethod());
                }
                else {
                    output.setText("Still loading DOM");
                }
            }
            else if (view == jsonButt) {
                JSONObject tmp;
                try {
                    jarr = task2.getResultAsJSON();

                    if (jarr== null) {
                        output.setText ("Still loading JSON");
                        return;
                    }

                    str = "";

                    for (int i = 0; i < jarr.length(); i++) {
                        tmp = jarr.getJSONObject(i);
                        str += tmp.getString ("title") + "\n";

                    }
                    output.setText(str);
                    output.setMovementMethod(new ScrollingMovementMethod());


                }
                catch (JSONException ex) {
                    output.setText("Some horror: " + ex.getMessage());
                }

            }

            else if(view == myPosters){
                Intent in;
                in = new Intent(SearchPoster.this, MyPosters_Activity.class);
                startActivity(in);
            }
        }
    }
}
