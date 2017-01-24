package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;
import android.widget.ImageView;

import com.gjdev.hugo.gjant.data.api.model.ClientWallet;

import java.util.List;

@UiThread
public interface SelectClientView {

    void showProgressBar();

    void hideProgressBar();

    void showSnackbar(String message);

    void setExpandedAppBarLayout(boolean expanded);

    void setupRecyclerView();

    void setupAdapter(List<ClientWallet> clientWallets);

    void loadSelectedAddImage(int adapterPosition);

    void changeSelectedAddImage(ImageView imageView);
}