package com.example.malek.bigburger.ui.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.malek.bigburger.R;
import com.example.malek.bigburger.models.Burger;
import com.example.malek.bigburger.models.CommandeBurger;
import com.example.malek.bigburger.ui.adapter.ListBurgerAdapter;

import java.util.ArrayList;

/**
 * Created by malek on 29/01/2017.
 * fragemnt for catalogue
 */

public class ListBurguerFragment extends Fragment {
    private ListBurgerAdapter listBurgerAdapter;
    private ArrayList<Burger> burgers;
    private Context context;
    private ArrayList<CommandeBurger> commandeBurgers;

    public ArrayList<Burger> getBurgers() {
        if (listBurgerAdapter != null)
            return listBurgerAdapter.getBurgerArrayList();
        else
            return burgers;
    }


    public ArrayList<CommandeBurger> getCommandeBurgers() {
        return commandeBurgers;
    }

    // Create a fragment
    public static ListBurguerFragment newInstance(ArrayList<Burger> burgers, ArrayList<CommandeBurger> commandeBurgers) {
        ListBurguerFragment listBurguerFragment = new ListBurguerFragment();
        Bundle args = new Bundle();
        args.putSerializable("burgers", burgers);
        args.putSerializable("commandeBurgers", commandeBurgers);
        listBurguerFragment.setArguments(args);
        return listBurguerFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            burgers = (ArrayList<Burger>) getArguments().getSerializable("burgers");
            commandeBurgers = (ArrayList<CommandeBurger>) getArguments().getSerializable("commandeBurgers");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Intialisation of recyclerView in a form of grid of 2 items
        View fragmentView = inflater.inflate(R.layout.recycler_burger, container, false);
        RecyclerView burgerRecycler = (RecyclerView) fragmentView.findViewById(R.id.recycler_burger);
        burgerRecycler.setLayoutManager(new GridLayoutManager(context, 2));
        burgerRecycler.setItemAnimator(new DefaultItemAnimator());
        burgerRecycler.setHasFixedSize(true);
        // creation of adapter
        listBurgerAdapter = new ListBurgerAdapter();
        listBurgerAdapter.setBurgerArrayList(burgers);
        listBurgerAdapter.setCommandeBurgers(commandeBurgers);
        listBurgerAdapter.setContext(context);
        burgerRecycler.setAdapter(listBurgerAdapter);
        return fragmentView;

    }

}
