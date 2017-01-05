package com.gjdev.hugo.gjant.data.model.temp;

public class Tags implements java.io.Serializable {
    private static final long serialVersionUID = 677106822900682691L;
    private String name;
    private String description;
    private int id;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
