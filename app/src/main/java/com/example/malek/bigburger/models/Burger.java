package com.example.malek.bigburger.models;

import java.io.Serializable;

/**
 * Created by malek on 29/01/2017.
 */

public class Burger implements Serializable {
    private long ref ;
    private String title ;
    private String description;
    private String thumbnail;
    private float price ;
    // true if the burger is ordered
    private Boolean chossed=false;

    public Boolean getIconLoaded() {
        return iconLoaded;
    }

    public void setIconLoaded(Boolean iconLoaded) {
        this.iconLoaded = iconLoaded;
    }

    private Boolean iconLoaded=false;

    public Boolean getChossed() {
        return chossed;
    }

    public void setChossed(Boolean chossed) {
        this.chossed = chossed;
    }

    public Burger() {
    }

    public Burger(long ref, float price, String thumbnail, String description, String title) {
        this.ref = ref;
        this.price = price;
        this.thumbnail = thumbnail;
        this.description = description;
        this.title = title;
    }

    public long getRef() {
        return ref;
    }

    public void setRef(long ref) {
        this.ref = ref;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public float getPrice() {
        return price ;

    }

    public void setPrice(float price) {
        this.price = price;
    }
}
