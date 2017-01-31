package com.example.malek.bigburger.tools;

import android.content.Context;

import com.android.volley.toolbox.JsonArrayRequest;
import com.example.malek.bigburger.models.Burger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by malek on 29/01/2017.
 * class for parse Jsons
 */

public class JsonParser  {
    public JsonParser() {
    }
    // Parse json burges
    public ArrayList<Burger> getListBurger(JSONArray jsonArray){
        ArrayList<Burger>listBurger = new ArrayList<>();
        for (int i=0;i<jsonArray.length();i++){
            try {
                // create a burger from a jsonObject in the array
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Burger burger = new Burger();
                burger.setRef( Long.parseLong(jsonObject.getString("ref")));
                burger.setDescription(jsonObject.getString("description"));
                burger.setTitle(jsonObject.getString("title"));
                burger.setPrice(Float.parseFloat(jsonObject.getString("price"))/100);
                burger.setThumbnail(jsonObject.getString("thumbnail"));
                listBurger.add(burger);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return listBurger;
    }
}
