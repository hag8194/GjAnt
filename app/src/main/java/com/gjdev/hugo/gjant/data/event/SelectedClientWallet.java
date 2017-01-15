package com.gjdev.hugo.gjant.data.event;

import com.gjdev.hugo.gjant.data.api.model.ClientWallet;

/**
 * Created by Hugo on 14/01/2017.
 */

public class SelectedClientWallet {
    private ClientWallet clientWallet;

    public SelectedClientWallet(ClientWallet clientWallet) {
        this.clientWallet = clientWallet;
    }

    public ClientWallet getClientWallet() {
        return clientWallet;
    }
}
