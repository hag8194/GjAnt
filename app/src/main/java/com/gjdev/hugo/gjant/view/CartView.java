package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;

import com.gjdev.hugo.gjant.data.api.model.Product;
import com.gjdev.hugo.gjant.data.sql.model.SQLProduct;

import java.util.List;

@UiThread
public interface CartView {

    void setTitle(int resString);

    void setAppBarExpanded(boolean expanded);

    void showSnackbar(String message);

    void showToast(String message);

    void setupRecyclerView();

    void setupAdapter(List<SQLProduct> products);

    void setupSwipeRefreshLayout();

    void setupFloatingActionButton();

    void setRefreshing(boolean refreshing);
}