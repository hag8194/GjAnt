package com.gjdev.hugo.gjant.data.api.model;

public class ClientWallet implements java.io.Serializable {
    private static final long serialVersionUID = 7935206882607371178L;
    private Client client;
    private int id;

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
