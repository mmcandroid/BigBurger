package com.example.malek.bigburger.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.malek.bigburger.R;
import com.example.malek.bigburger.models.Burger;
import com.example.malek.bigburger.models.CommandeBurger;
import com.example.malek.bigburger.tools.Const;
import com.example.malek.bigburger.ui.activity.BurgerDetail;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * Created by malek on 29/01/2017.
 * RecyclerView adapter for show burgers
 */

public class ListBurgerAdapter extends RecyclerView.Adapter<ListBurgerAdapter.ViewHolder> {
    private ArrayList<Burger> burgerArrayList;
    private ArrayList<CommandeBurger> commandeBurgers;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private Context context;

    public ListBurgerAdapter() {
    }
    // Getter and setter
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }




    public void setCommandeBurgers(ArrayList<CommandeBurger> commandeBurgers) {
        this.commandeBurgers = commandeBurgers;
    }

    public ArrayList<Burger> getBurgerArrayList() {
        return burgerArrayList;
    }

    public void setBurgerArrayList(ArrayList<Burger> burgerArrayList) {
        this.burgerArrayList = burgerArrayList;
    }


    @Override
    public ListBurgerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_burger, parent, false));
    }

    @Override
    public void onBindViewHolder(final ListBurgerAdapter.ViewHolder holder, int position) {
        // add burger infos to the view
        final Burger burger = burgerArrayList.get(position);
        holder.description.setText(burger.getDescription());
        holder.title.setText(burger.getTitle());
        imageLoader.displayImage(burgerArrayList.get(holder.getAdapterPosition()).getThumbnail(), holder.burgerImage);
        // title of btn
        if (!burger.getChossed()) {
            holder.choisirBtn.setText(R.string.choissir);
        } else {
            holder.choisirBtn.setText(R.string.supprimer);
        }
        holder.choisirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chose a burger
                if (!burger.getChossed()) {
                    if (holder.getAdapterPosition() > -1) {
                        // add a commande
                        CommandeBurger commandeBurger = new CommandeBurger();
                        commandeBurger.setBurgerSelectionner(burger);
                        commandeBurgers.add(commandeBurger);
                        burgerArrayList.get(holder.getAdapterPosition()).setChossed(true);
                    }
                } else {
                    //remove Commande
                    try {
                        if (holder.getAdapterPosition() > -1) {
                            for (CommandeBurger commandeBurger : commandeBurgers) {
                                if (commandeBurger.getBurgerSelectionner().getRef() == burger.getRef())
                                    commandeBurgers.remove(commandeBurger);
                            }
                            burgerArrayList.get(holder.getAdapterPosition()).setChossed(false);
                        }
                    } catch (ConcurrentModificationException ignored) {

                    }


                }
                ListBurgerAdapter.this.notifyDataSetChanged();


            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // show activity to show an burger
                Intent intent = new Intent(context, BurgerDetail.class);
                intent.putExtra("burger", burger);
                ((Activity) context).startActivityForResult(intent, Const.requestDetail);
            }
        });


    }

    @Override
    public int getItemCount() {
        return burgerArrayList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        public TextView description;
        public Button choisirBtn;
        public ImageView burgerImage;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.titleBurger);
            description = (TextView) itemView.findViewById(R.id.description);
            choisirBtn = (Button) itemView.findViewById(R.id.commaderBtn);
            burgerImage = (ImageView) itemView.findViewById(R.id.imageBurger);
        }
    }
}
