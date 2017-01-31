package com.example.malek.bigburger.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.malek.bigburger.R;
import com.example.malek.bigburger.models.CommandeBurger;
import com.example.malek.bigburger.tools.PriceChangeListener;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by malek on 29/01/2017.
 */

public class ListCommadesAdapter extends RecyclerView.Adapter<ListCommadesAdapter.ViewHolder> {
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private float prixTotalCommande;
    private PriceChangeListener priceChangeListenr;
    private ArrayList<CommandeBurger> commandeBurgers;
    //get and setter

    public void setPriceChangeListenr(PriceChangeListener priceChangeListenr) {
        this.priceChangeListenr = priceChangeListenr;
    }

    public void setPrixTotalCommande(float prixTotalCommande) {
        this.prixTotalCommande = prixTotalCommande;
    }

    public void setCommandeBurgers(ArrayList<CommandeBurger> commandeBurgers) {
        this.commandeBurgers = commandeBurgers;
    }

    @Override
    public ListCommadesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListCommadesAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commande, parent, false));
    }

    @Override
    public void onBindViewHolder(final ListCommadesAdapter.ViewHolder holder, int position) {
        // show commande
        final CommandeBurger commandeBurger = commandeBurgers.get(holder.getAdapterPosition());
        holder.title.setText(commandeBurger.getBurgerSelectionner().getTitle());
        holder.quantityCommade.setText(commandeBurger.getNbrDeBurger() + " ");
        holder.prixCommade.setText(String.format("%s €", new DecimalFormat("#.###").format(commandeBurger.getPrixCommande())));
        holder.moinsBtn.setEnabled(commandeBurgers.get(position).getNbrDeBurger() != 1);
        holder.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add a burger to commande
                commandeBurger.setNbrDeBurger(commandeBurger.getNbrDeBurger() + 1);
                prixTotalCommande = prixTotalCommande + commandeBurger.getBurgerSelectionner().getPrice();
                // update the total price
                priceChangeListenr.onPriceChange(prixTotalCommande);
                ListCommadesAdapter.this.notifyDataSetChanged();
            }
        });
        holder.moinsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (commandeBurger.getNbrDeBurger() != 1) {
                    // remove a burger
                    commandeBurger.setNbrDeBurger(commandeBurger.getNbrDeBurger() - 1);
                    prixTotalCommande = prixTotalCommande - commandeBurger.getBurgerSelectionner().getPrice();
                    // update the total price
                    priceChangeListenr.onPriceChange(prixTotalCommande);
                    ListCommadesAdapter.this.notifyDataSetChanged();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return commandeBurgers.size();
    }

      static class ViewHolder extends RecyclerView.ViewHolder {
          TextView title;
          TextView prixCommade;
          TextView quantityCommade;
          TextView plusBtn;
          TextView moinsBtn;


          ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.titleCommade);
            quantityCommade = (TextView) itemView.findViewById(R.id.quantity);
            prixCommade = (TextView) itemView.findViewById(R.id.prixCommande);
            plusBtn = (TextView) itemView.findViewById(R.id.plusBtn);
            moinsBtn = (TextView) itemView.findViewById(R.id.moinsBtn);

        }
    }
}
