package com.gjdev.hugo.gjant.data.api.model;

public class Order implements java.io.Serializable {
    private static final long serialVersionUID = -553941675801668465L;
    private String code;
    private int updated_at;
    private String description;
    private int created_at;
    private int id;
    private int type;
    private int status;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
