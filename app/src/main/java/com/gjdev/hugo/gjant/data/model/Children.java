package com.gjdev.hugo.gjant.data.model;

/**
 * Created by Hugo on 04/01/2017.
 */
public class Children {
    /**
     * id : 4
     * code : producprueba2
     * name : Producto 2
     * quantity : 50
     * price : 500000
     * status : 1
     * created_at : 1483456818
     * updated_at : 1483456818
     * updated_by : 1
     * brand : {"id":1,"name":"Addidas","description":""}
     * _links : {"poster":{"href":"http://127.0.0.1/GjAntWebAPI/backend/web/img/B16sPWpjMxSGlF7oO6fXfknojIqTPz0A.jpg"}}
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
}
