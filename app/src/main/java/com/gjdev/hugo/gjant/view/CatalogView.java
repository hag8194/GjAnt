package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;

import com.gjdev.hugo.gjant.data.model.Product;

import java.util.List;

@UiThread
public interface CatalogView {
    void showSnackbar(String message);
    void setupRecyclerView();
    void setupAdapter(List<Product> products);
    boolean isRecyclerViewActivated();
    void sendProductEvent(int id);
    void startDetailProductActivity();
}