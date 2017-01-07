package com.gjdev.hugo.gjant.data.api.model;

import java.io.Serializable;

public class Employer implements Serializable {
    private String identification;
    private Address address;
    private int updated_at;
    private Zone zone;
    private String name;
    private int created_at;
    private int id;
    private String lastname;

    public String getIdentification() {
        return this.identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public Zone getZone() {
        return this.zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
