package com.gjdev.hugo.gjant.data.sql.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Hugo on 07/01/2017.
 */

@Entity(nameInDb = "Product")
public class SQLProduct {

    @Id(autoincrement = true)
    private Long id;

    @Index(unique = true)
    private String key;

    @Unique
    @NotNull
    private String code;

    @NotNull
    private String name;

    @NotNull
    private String quantity;

    @NotNull
    private int price;
    
    private String brand;

    private int status;
    private int created_at;
    private int updated_at;
    private int updated_by;

    @Generated(hash = 2057630697)
    public SQLProduct(Long id, String key, @NotNull String code,
            @NotNull String name, @NotNull String quantity, int price, String brand,
            int status, int created_at, int updated_at, int updated_by) {
        this.id = id;
        this.key = key;
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.updated_by = updated_by;
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
    public String getKey() {
        return this.key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getQuantity() {
        return this.quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public int getPrice() {
        return this.price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getBrand() {
        return this.brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getCreated_at() {
        return this.created_at;
    }
    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }
    public int getUpdated_at() {
        return this.updated_at;
    }
    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }
    public int getUpdated_by() {
        return this.updated_by;
    }
    public void setUpdated_by(int updated_by) {
        this.updated_by = updated_by;
    }

}
