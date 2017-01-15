package com.gjdev.hugo.gjant.data.api.model;

public class Client implements java.io.Serializable {
    private static final long serialVersionUID = -5468383894018647434L;
    private String identification;
    private double credit_use;
    private int updated_at;
    private String phone2;
    private double credit_limit;
    private int created_at;
    private int assigned;
    private int id;
    private String fullname;
    private String phone1;
    private Address address;
    private Links _links;

    public String getIdentification() {
        return this.identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public double getCredit_use() {
        return this.credit_use;
    }

    public void setCredit_use(double credit_use) {
        this.credit_use = credit_use;
    }

    public int getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public String getPhone2() {
        return this.phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public double getCredit_limit() {
        return this.credit_limit;
    }

    public void setCredit_limit(double credit_limit) {
        this.credit_limit = credit_limit;
    }

    public int getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public int getAssigned() {
        return this.assigned;
    }

    public void setAssigned(int assigned) {
        this.assigned = assigned;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone1() {
        return this.phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }
}
