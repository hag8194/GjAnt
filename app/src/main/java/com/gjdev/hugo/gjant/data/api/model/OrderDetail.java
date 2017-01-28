package com.gjdev.hugo.gjant.data.api.model;

/**
 * Created by Hugo on 28/01/2017.
 */

public class OrderDetail {
    private int quantity;
    private Product product;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
