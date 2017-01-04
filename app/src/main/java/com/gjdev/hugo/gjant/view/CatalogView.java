package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;
import android.view.View;

import com.gjdev.hugo.gjant.data.model.Product;

import java.util.List;

@UiThread
public interface CatalogView {
    void showSnackbar(String message);
    void setupRecyclerView();
    void setupAdapter(List<Product> products);
    void sendProductEvent(int id);
    void startDetailProductActivity();
}