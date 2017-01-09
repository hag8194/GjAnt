package com.gjdev.hugo.gjant.data.sql.model;

import android.support.annotation.NonNull;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by Hugo on 07/01/2017.
 */

@Entity(nameInDb = "cart_product")
public class SQLProduct {
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    @Unique
    private int key;

    @NonNull
    private String poster_url;

    @NotNull
    private String name;

    @NotNull
    private double price;

    @NonNull
    private int quantity;

    @Generated(hash = 1080617591)
    public SQLProduct(Long id, int key, @NotNull String poster_url,
            @NotNull String name, double price, int quantity) {
        this.id = id;
        this.key = key;
        this.poster_url = poster_url;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Generated(hash = 123468293)
    public SQLProduct() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getPoster_url() {
        return this.poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
