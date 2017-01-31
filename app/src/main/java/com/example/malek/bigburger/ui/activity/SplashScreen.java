package com.example.malek.bigburger.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.malek.bigburger.R;
import com.example.malek.bigburger.models.Burger;
import com.example.malek.bigburger.tools.Const;
import com.example.malek.bigburger.tools.JsonParser;
import com.example.malek.bigburger.tools.VolleySingleton;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
/*
*Created by malek on 29/01/2017.
* splach screen for load data
* */
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //request json of burgers
         JsonArrayRequest catalogRequest = new JsonArrayRequest(Const.url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //parse the json in an asyncktask
                 new AsyncTask<JSONArray, Void, ArrayList<Burger>>() {
                    @Override
                    protected ArrayList<Burger> doInBackground(JSONArray... params) {
                        return new JsonParser().getListBurger(params[0]);
                    }

                    @Override
                    protected void onPostExecute(ArrayList<Burger> burgers) {
                        super.onPostExecute(burgers);
                        // Start the main activity
                        Intent intent = new Intent(SplashScreen.this, ListBurger.class);
                        intent.putExtra("burgers", burgers);
                        startActivity(intent);
                        finish();
                    }
                }.execute(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // In case of error in the loading of the json
                Toast.makeText(SplashScreen.this, "Erreur de connexion", Toast.LENGTH_LONG).show();
                findViewById(R.id.progressBar).setVisibility(View.GONE);
            }
        });
        catalogRequest.setShouldCache(false);
        VolleySingleton.getInstance(this).addToRequestQueue(catalogRequest);
    }
}
