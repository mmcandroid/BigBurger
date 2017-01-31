package com.example.malek.bigburger.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.malek.bigburger.R;
import com.example.malek.bigburger.models.Burger;
import com.example.malek.bigburger.models.CommandeBurger;
import com.example.malek.bigburger.ui.fragment.ListBurguerFragment;
import com.example.malek.bigburger.ui.fragment.ListCommadeFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
/*
* Created by malek on 29/01/2017.
* Main Activity with framgent of commande and cataologue
* */
public class ListBurger extends AppCompatActivity  {

    private ArrayList<Burger> burgersCatlog;
    private ArrayList<CommandeBurger> commandeBurgers;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_burger);
         if (getIntent()!=null && getIntent().getExtras()!=null){
             // Get List of catalog
            burgersCatlog = (ArrayList<Burger>)  getIntent().getExtras().getSerializable("burgers");
            if(burgersCatlog!=null){
                // instialisation of universal imageLoader
                DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .showImageForEmptyUri(R.drawable.default_image)
                        .showImageOnFail(R.drawable.default_image)
                        .showImageOnLoading(R.drawable.default_image)
                        .resetViewBeforeLoading(false)
                        .build();

                ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                        .defaultDisplayImageOptions(defaultOptions)

                        .build();
                ImageLoader.getInstance().init(config);
                //Creation of listBurguerFragment Fragement to show catalogue
                commandeBurgers=new ArrayList<>();
                ListBurguerFragment listBurguerFragment =ListBurguerFragment.newInstance(burgersCatlog,commandeBurgers);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment, listBurguerFragment).commit();
                currentFragment=listBurguerFragment;
            }

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(getString(R.string.catalogue));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_catalogue, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(currentFragment instanceof ListCommadeFragment){
            swipeToCatlogue();
        }else {
        super.onBackPressed();}

    }

    public void swipeToCatlogue (){
        // Save commande lists
        commandeBurgers=((ListCommadeFragment)currentFragment).getCommandeBurgers();
        burgersCatlog=((ListCommadeFragment)currentFragment).getBurgers();
        currentFragment =ListBurguerFragment.newInstance(burgersCatlog,commandeBurgers);
        // Swipe to catalogue
        setTitle(getString(R.string.catalogue));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, currentFragment).commit();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_shopping:
                // Swipe from catalogue to commande

                if (currentFragment instanceof ListBurguerFragment){
                    // Save commande lists and BurgesCatalogue with states of burges
                    commandeBurgers=((ListBurguerFragment)currentFragment).getCommandeBurgers();
                    burgersCatlog=((ListBurguerFragment) currentFragment).getBurgers();
                    // Swipe to commande
                    currentFragment= ListCommadeFragment.newInstance(commandeBurgers,burgersCatlog);
                    setTitle(getString(R.string.commande));
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment, currentFragment).commit();

                }else{
                    swipeToCatlogue ();

                 }

                 return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
