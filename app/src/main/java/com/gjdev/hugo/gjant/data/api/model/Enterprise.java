package com.gjdev.hugo.gjant.data.api.model;

/**
 * Created by Hugo on 24/01/2017.
 */

public class Enterprise {

    /**
     * id : 1
     * name : INVERSIONESGONMART C.A.
     * rif : J-123654158122
     * phone : 0241-8530707
     * address : Carialinda 1era Etapa, Naguanagua, Carabobo,
     * founded_date : 2011-11-17
     */

    private int id;
    private String name;
    private String rif;
    private String phone;
    private String address;
    private String founded_date;

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

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFounded_date() {
        return founded_date;
    }

    public void setFounded_date(String founded_date) {
        this.founded_date = founded_date;
    }
}
