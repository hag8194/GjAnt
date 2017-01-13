package com.gjdev.hugo.gjant.interactor;

import com.gjdev.hugo.gjant.data.api.model.ClientWallet;

import java.util.List;

public interface SelectClientInteractor extends BaseInteractor {

    void retrieveClientWallet();

    ClientWallet getClientWallet(int position);
}