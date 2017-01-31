package com.example.malek.bigburger.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.malek.bigburger.R;
import com.example.malek.bigburger.models.Burger;
import com.nostra13.universalimageloader.core.ImageLoader;
/*
* Created by malek on 29/01/2017.
* show detail of a burger
* */
public class BurgerDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger_detail);
        // Intialisation of views
        TextView titleBurger = (TextView)findViewById(R.id.titleBurger);
        TextView desriptionBurger =(TextView)findViewById(R.id.description);
        TextView prixBurger =(TextView)findViewById(R.id.prix);
        ImageView imageBurger =(ImageView)findViewById(R.id.imageBuger);
         if (getIntent() !=null){
             // Get the Burger from intent and show it
             Burger burger = (Burger) getIntent().getSerializableExtra("burger");
             setTitle(burger.getTitle());
             titleBurger.setText(burger.getTitle());
             desriptionBurger.setText(burger.getDescription());
             prixBurger.setText(burger.getPrice()+" €");
             ImageLoader.getInstance().displayImage(burger.getThumbnail(),imageBurger);

        }
    }

}