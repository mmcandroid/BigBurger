package com.example.malek.bigburger.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.malek.bigburger.R;
import com.example.malek.bigburger.models.Burger;
import com.example.malek.bigburger.models.CommandeBurger;
import com.example.malek.bigburger.tools.PriceChangeListener;
import com.example.malek.bigburger.ui.adapter.ListCommadesAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by malek on 29/01/2017.
 * fragment for commande
 */

public class ListCommadeFragment extends Fragment implements PriceChangeListener {
    private TextView prixCommande;
    private ListCommadesAdapter listCommadesAdapter;

    public ArrayList<CommandeBurger> getCommandeBurgers() {
        return commandeBurgers;
    }

    private ArrayList<CommandeBurger>commandeBurgers;

    public ArrayList<Burger> getBurgers() {
        return burgers;
    }

    private ArrayList<Burger> burgers;
    private Context context;
    private float prixTotalCommande=0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
    // Create a new fragment
    public static ListCommadeFragment newInstance( ArrayList<CommandeBurger> commandeBurgers,ArrayList<Burger> burgers) {
        ListCommadeFragment listCommadeFragment = new ListCommadeFragment();
        Bundle args = new Bundle();
         args.putSerializable("commandeBurgers", commandeBurgers);
         args.putSerializable("burgers", burgers);
        listCommadeFragment.setArguments(args);
        return listCommadeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
             commandeBurgers = (ArrayList<CommandeBurger>) getArguments().getSerializable("commandeBurgers");
            burgers = (ArrayList<Burger>) getArguments().getSerializable("burgers");
            if (commandeBurgers != null) {
                // total price of commande
                for (CommandeBurger commandeBurger:commandeBurgers){
                    prixTotalCommande=commandeBurger.getPrixCommande()+prixTotalCommande;
                }
            }
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.commande_fragment, container, false);
        // Intialisation of recyclerView in a form of list
          prixCommande=(TextView)fragmentView.findViewById(R.id.prixCommande);
        prixCommande.setText(new DecimalFormat("#.###").format(prixTotalCommande)+" €");
        RecyclerView commandeList = (RecyclerView) fragmentView.findViewById(R.id.recycler_burger);
        commandeList.setLayoutManager(new LinearLayoutManager(context));
        commandeList.setItemAnimator(new DefaultItemAnimator());
        commandeList.setHasFixedSize(true);
        // creation of adapter
        listCommadesAdapter = new ListCommadesAdapter();
        listCommadesAdapter.setCommandeBurgers(commandeBurgers);
        listCommadesAdapter.setPrixTotalCommande(prixTotalCommande);
        listCommadesAdapter.setPriceChangeListenr(this);
        commandeList.setAdapter(listCommadesAdapter);
        // Detect swipe in commande recyclerView
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //remove commande
                CommandeBurger commandeBurger = commandeBurgers.get(viewHolder.getAdapterPosition());
                Log.d("prixCommande",prixTotalCommande+"");
                Log.d("prixCommandeRemove",commandeBurger.getPrixCommande()+"");
                prixTotalCommande=prixTotalCommande-commandeBurger.getPrixCommande();
                commandeBurgers.remove(commandeBurger);
                onPriceChange(prixTotalCommande);
                for (Burger burger:burgers){
                    if(burger.getRef()==commandeBurger.getBurgerSelectionner().getRef()){
                        burger.setChossed(false);
                    break;}
                }
                listCommadesAdapter.setPrixTotalCommande(prixTotalCommande);
                listCommadesAdapter.notifyDataSetChanged();

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(commandeList);

        return fragmentView;

    }

    @Override
    public void onPriceChange(float total) {
        prixTotalCommande=total;
        // Force to zero
        if(total<0)
            total=0;
        prixCommande.setText(new DecimalFormat("#.###").format(total)+" €");

    }
}
