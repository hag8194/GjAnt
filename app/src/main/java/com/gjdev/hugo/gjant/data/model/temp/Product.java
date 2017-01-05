package com.gjdev.hugo.gjant.data.model.temp;

public class Product implements java.io.Serializable {
    private static final long serialVersionUID = -4578474164514838434L;
    private String code;
    private String quantity;
    private Product_links _links;
    private int created_at;
    private Tags[] tags;
    private ProductImages[] productImages;
    private int updated_at;
    private Product[] children;
    private int price;
    private String name;
    private int updated_by;
    private int id;
    private Brand brand;
    private int status;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Product_links get_links() {
        return this._links;
    }

    public void set_links(Product_links _links) {
        this._links = _links;
    }

    public int getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public Tags[] getTags() {
        return this.tags;
    }

    public void setTags(Tags[] tags) {
        this.tags = tags;
    }

    public ProductImages[] getProductImages() {
        return this.productImages;
    }

    public void setProductImages(ProductImages[] productImages) {
        this.productImages = productImages;
    }

    public int getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public Product[] getChildren() {
        return this.children;
    }

    public void setChildren(Product[] children) {
        this.children = children;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUpdated_by() {
        return this.updated_by;
    }

    public void setUpdated_by(int updated_by) {
        this.updated_by = updated_by;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Brand getBrand() {
        return this.brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
