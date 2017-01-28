package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;

import com.gjdev.hugo.gjant.data.api.model.ClientWallet;

import java.util.List;

@UiThread
public interface HomeView {

    void resetFloatingActionButton();

    void setAppBarExpanded(boolean expanded);

    void setTitle(int resString);

    void showSnackbar(String message);

    void setupAdapter(List<ClientWallet> clientWallets);

    void loadClientDetailFragment();
}