package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;

import com.gjdev.hugo.gjant.data.api.model.Product;
import com.gjdev.hugo.gjant.data.sql.model.SQLProduct;

import java.util.List;

@UiThread
public interface CartView {
    void showProgressBar();

    void hideProgressBar();

    void showSnackbar(String message);

    void setupSwipeRefreshLayout();

    void setupRecyclerView();

    void setupAdapter(List<SQLProduct> products);

    void notifyDataChanged();

    void stopRefreshing();

    void startProductDetailFragment();
}