package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;

import com.gjdev.hugo.gjant.data.api.model.ClientWallet;

import java.util.List;

@UiThread
public interface SelectClientView {

    void showProgressBar();

    void hideProgressBar();

    void showSnackbar(String message);

    void setupRecyclerView();

    void setupAdapter(List<ClientWallet> clientWallets);
}