package com.gjdev.hugo.gjant.data.model.temp;

public class ProductImages implements java.io.Serializable {
    private static final long serialVersionUID = 7590923494794871050L;
    private String path;
    private int product_id;
    private int id;

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getProduct_id() {
        return this.product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
