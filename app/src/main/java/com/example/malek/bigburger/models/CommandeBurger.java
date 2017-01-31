package com.example.malek.bigburger.models;

import java.io.Serializable;

/**
 * Created by malek on 29/01/2017.
 *
 */

public class CommandeBurger implements Serializable {
    private Burger burgerSelectionner;
    private int nbrDeBurger;
    private float prixCommande ;

    public CommandeBurger() {
        nbrDeBurger=1;
         }

    public float getPrixCommande() {
        return prixCommande;
    }

    public void setPrixCommande(float prixCommande) {
        this.prixCommande = prixCommande;
    }

    public int getNbrDeBurger() {
        return nbrDeBurger;
    }

    public void setNbrDeBurger(int nbrDeBurger) {
        this.nbrDeBurger = nbrDeBurger;
        this.prixCommande=nbrDeBurger*burgerSelectionner.getPrice();
    }

    public Burger getBurgerSelectionner() {
        return burgerSelectionner;
    }

    public void setBurgerSelectionner(Burger burgerSelectionner) {
        this.burgerSelectionner = burgerSelectionner;
        prixCommande=burgerSelectionner.getPrice()*nbrDeBurger;
    }
}
