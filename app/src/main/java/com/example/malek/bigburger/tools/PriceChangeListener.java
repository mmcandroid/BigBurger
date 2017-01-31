package com.example.malek.bigburger.tools;

/**
 * Created by malek on 29/01/2017.
 * a listener to catch the change in the total price of the order
 */

public interface PriceChangeListener {
    void onPriceChange(float total);
}
