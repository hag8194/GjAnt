package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;

import com.gjdev.hugo.gjant.data.api.model.Product;

import java.util.List;

@UiThread
public interface CatalogView {

    void showProgressBar();

    void hideProgressBar();

    void showSnackbar(String message);

    void setupSwipeRefreshLayout();

    void setupRecyclerView();

    void setupAdapter(List<Product> products);

    void notifyDataChanged();

    void stopRefreshing();

    void startDetailProductFragment();
}