package com.gjdev.hugo.gjant.data.api.model;

import java.io.Serializable;

public class Address implements Serializable {
    private double lng;
    private String name;
    private int id;
    private double lat;

    public double getLng() {
        return this.lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
