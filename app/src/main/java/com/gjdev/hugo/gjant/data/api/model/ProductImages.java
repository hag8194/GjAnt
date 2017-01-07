package com.gjdev.hugo.gjant.data.api.model;

import java.io.Serializable;

/**
 * Created by Hugo on 04/01/2017.
 */
public class ProductImages implements Serializable {
    /**
     * id : 1
     * path : http://127.0.0.1/GjAntWebAPI/backend/web/img/NN-TG7bN2wdVF53gvExAr-RH6mQTQJQm.png
     * product_id : 3
     */

    private int id;
    private String path;
    private int product_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
