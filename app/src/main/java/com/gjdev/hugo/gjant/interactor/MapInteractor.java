package com.gjdev.hugo.gjant.interactor;

import com.gjdev.hugo.gjant.data.api.model.ClientWallet;
import com.gjdev.hugo.gjant.data.api.model.User;

import java.util.List;

public interface MapInteractor extends BaseInteractor {

    User getUser();

    void setClientWalletList(List<ClientWallet> clientWalletList);

    List<ClientWallet> getClientWalletList();

}