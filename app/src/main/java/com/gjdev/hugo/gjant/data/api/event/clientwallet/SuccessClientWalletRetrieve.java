package com.gjdev.hugo.gjant.data.api.event.clientwallet;

import com.gjdev.hugo.gjant.data.api.model.ClientWallet;
import java.util.List;

/**
 * Created by Hugo on 04/01/2017.
 */

public class SuccessClientWalletRetrieve {
    private List<ClientWallet> clientWallet;

    public SuccessClientWalletRetrieve(List<ClientWallet> clientWallet) {
        this.clientWallet = clientWallet;
    }

    public List<ClientWallet> getClientWallet() {
        return clientWallet;
    }
}
