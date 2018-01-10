package com.example.b1503004.movie_poster;

import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by marc on 09/01/2018.
 */

public class MyHttpRequest extends AsyncTask<String, Void, Void> {

    private String returnEntry;
    private boolean finished;

    public void readResponse(BufferedReader in) {
        String tmp = "";
        StringBuffer response = new StringBuffer();

        do {
            try {
                tmp = in.readLine();
            } catch (IOException ex) {

            }
            if (tmp != null) {
                response.append(tmp);
            }
        } while (tmp != null);

        returnEntry = response.toString();
    }

    public void sendPostRequest(String where){
        URL loc = null;
        HttpURLConnection conn = null;
        InputStreamReader is;
        BufferedReader in;

        try {
            loc = new URL(where);
        }
        catch (MalformedURLException ex){
            return;
        }

        try {
            conn = (HttpURLConnection)loc.openConnection();
            is = new InputStreamReader(conn.getInputStream(), "UTF-8");
            in = new BufferedReader(is);
        }
         catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            conn.disconnect();
        }
    }

    public String getReturnEntry(){
        if (!finished){
            return "Hold tight!";
        }
        return returnEntry;
    }

    @Override
    protected void onPostExecute(Void result){
        finished = true;

        Log.d("Output", returnEntry);
    }

    @Override
    protected Void doInBackground(String...params){
        finished = false;
        sendPostRequest(params[0]);
        return null;
    }

    public Document getResultsAsDom(){
        Document xmlDoc = null;
        DocumentBuilder builder;

        if(finished == false){
            return null;
        }

        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmlDoc = builder.parse(new InputSource(new StringReader(returnEntry)));
        }
        catch(ParserConfigurationException ex1){
            Log.d("Output", "Parser problem");

        } catch (SAXException ex2) {
            Log.d("Output", "SAX problem");

        }
        catch (IOException ex3) {
            Log.d("Output", "IO problem: " + ex3.getMessage());
        }

        return xmlDoc;
    }


    public JSONArray getResultsAsJSON(){
        JSONArray jarr = null;

        if(finished == false){
            return null;
        }
        try{
            jarr = new JSONArray(returnEntry);
        }
        catch (JSONException ex){
            Log.d("Output", "Error is " + ex.getMessage());
        }
        return jarr;
    }
}
