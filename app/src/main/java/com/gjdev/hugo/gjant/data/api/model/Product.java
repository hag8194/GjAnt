package com.gjdev.hugo.gjant.data.api.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hugo on 04/01/2017.
 */

public class Product implements Serializable {

    /**
     * id : 3
     * code : producprueba1
     * name : Producto 1
     * quantity : 200
     * price : 100000
     * status : 1
     * created_at : 1482560950
     * updated_at : 1483457178
     * updated_by : 1
     * brand : {"id":1,"name":"Addidas","description":""}
     * tags : [{"id":1,"name":"Etiqueta1","description":""},{"id":3,"name":"Etiqueta2","description":"Esta es la etiqueta2"},{"id":4,"name":"Etiqueta4","description":"Esta es la etiqueta4"},{"id":6,"name":"Etiqueta6","description":"Etiqueta6!!!"},{"id":8,"name":"Etiqueta8","description":"Esta es la etiqueta8"}]
     * children : [{"id":4,"code":"producprueba2","name":"Producto 2","quantity":"50","price":500000,"status":1,"created_at":1483456818,"updated_at":1483456818,"updated_by":1,"brand":{"id":1,"name":"Addidas","description":""},"_links":{"poster":{"href":"http://127.0.0.1/GjAntWebAPI/backend/web/img/B16sPWpjMxSGlF7oO6fXfknojIqTPz0A.jpg"}}},{"id":5,"code":"producprueba3","name":"Producto 3","quantity":"33350","price":150,"status":0,"created_at":1483457199,"updated_at":1483457199,"updated_by":1,"brand":{"id":1,"name":"Addidas","description":""},"_links":{"poster":{"href":null}}}]
     * productImages : [{"id":1,"path":"http://127.0.0.1/GjAntWebAPI/backend/web/img/NN-TG7bN2wdVF53gvExAr-RH6mQTQJQm.png","product_id":3},{"id":2,"path":"http://127.0.0.1/GjAntWebAPI/backend/web/img/Qrh5NvZuA5uGym0oRFgc8ZXkTE0qb0rf.png","product_id":3},{"id":3,"path":"http://127.0.0.1/GjAntWebAPI/backend/web/img/k9e00i5tFero-mvHHr4UhFv8l1CDK-6k.jpg","product_id":3}]
     * _links : {"poster":{"href":"http://127.0.0.1/GjAntWebAPI/backend/web/img/NN-TG7bN2wdVF53gvExAr-RH6mQTQJQm.png"}}
     */

    private int id;
    private String code;
    private String name;
    private String quantity;
    private int price;
    private int status;
    private int created_at;
    private int updated_at;
    private int updated_by;
    private Brand brand;
    private Links _links;
    private List<Tags> tags;
    private List<Children> children;
    private List<ProductImages> productImages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public int getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(int updated_by) {
        this.updated_by = updated_by;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    public List<ProductImages> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImages> productImages) {
        this.productImages = productImages;
    }
}
