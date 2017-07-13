package com.example.gandh.hw4_amar;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by gandh on 2/10/2017.
 */

public class Questioninforetreiver {

    public  ArrayList<Questions> inforetreiver(String data) throws JSONException {
        JSONObject ob1 = new JSONObject(data);
        JSONArray array1 = ob1.getJSONArray("questions");
        ArrayList<Questions> totaldata = new ArrayList<>();
        for(int i=0; i< array1.length();i++)
        {
            Questions quest = new Questions();
            JSONObject ob2 = array1.getJSONObject(i);
            quest.setId(ob2.getInt("id"));

            if(ob2.has("image")) {
                quest.setImage(ob2.getString("image"));
            }
            else
            {
                quest.setImage("NO");
            }
            quest.setText(ob2.getString("text"));
            JSONObject ob3 = ob2.getJSONObject("choices");
            JSONArray array2 = ob3.getJSONArray("choice");

            String choice[] = new String[array2.length()];
            Choices choiceclass = new Choices();

            for (int j=0; j<array2.length();j++)
            {

                choice[j] = array2.getString(j);
            }
            choiceclass.setChoice(choice);
            choiceclass.setAnswer(ob3.getInt("answer"));
            choiceclass.setSelected(-1);
            quest.setChoices(choiceclass);
            totaldata.add(quest);

        }
        return totaldata;
    }
}
