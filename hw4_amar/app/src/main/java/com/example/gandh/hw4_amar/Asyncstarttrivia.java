package com.example.gandh.hw4_amar;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by gandh on 2/10/2017.
 */

public class Asyncstarttrivia extends AsyncTask<String,Void, ArrayList<Questions>> {

    public Asyncstarttrivia(triviadataintf activity) {
        this.activity = activity;
    }

    StringBuilder sb;
    triviadataintf activity;
Questioninforetreiver retreiver = new Questioninforetreiver();
    @Override
    protected ArrayList<Questions> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(con.getInputStream()));
            sb = new StringBuilder();
            String s = "";
            while((s=bfr.readLine())!=null)
            {
                sb.append(s);
            }

            return retreiver.inforetreiver(sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Questions> value) {

        activity.dataintfmethod(value);
    }

    public interface triviadataintf{

        public void dataintfmethod(ArrayList<Questions> data);

    }
}
