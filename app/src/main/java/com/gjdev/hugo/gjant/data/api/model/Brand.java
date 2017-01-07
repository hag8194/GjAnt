package com.gjdev.hugo.gjant.data.api.model;

import java.io.Serializable;

/**
 * Created by Hugo on 04/01/2017.
 */
public class Brand implements Serializable {
    /**
     * id : 1
     * name : Addidas
     * description :
     */

    private int id;
    private String name;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
