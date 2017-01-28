package com.gjdev.hugo.gjant.data.api.event.client;

import com.gjdev.hugo.gjant.data.api.model.Client;

/**
 * Created by Hugo on 04/01/2017.
 */

public class SuccessClientRetrieve {
    private Client client;

    public SuccessClientRetrieve(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
}
