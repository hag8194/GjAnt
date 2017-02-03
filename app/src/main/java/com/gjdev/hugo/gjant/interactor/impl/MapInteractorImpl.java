package com.gjdev.hugo.gjant.interactor.impl;

import javax.inject.Inject;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.model.ClientWallet;
import com.gjdev.hugo.gjant.data.api.model.User;
import com.gjdev.hugo.gjant.interactor.MapInteractor;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import java.util.List;

public final class MapInteractorImpl implements MapInteractor {

    private InternalStorageHandler mInternalStorageHandler;
    private List<ClientWallet> clientWalletList;

    @Inject
    public MapInteractorImpl(InternalStorageHandler internalStorageHandler) {
        mInternalStorageHandler = internalStorageHandler;
    }

    @Override
    public User getUser() {
        return (User)mInternalStorageHandler.readObject(R.string.user_data);
    }

    @Override
    public void setClientWalletList(List<ClientWallet> clientWalletList) {
        this.clientWalletList = clientWalletList;
    }

    @Override
    public List<ClientWallet> getClientWalletList() {
        return clientWalletList;
    }

    @Override
    public void postEvent(int kindOfEvent, Object object) {

    }
}