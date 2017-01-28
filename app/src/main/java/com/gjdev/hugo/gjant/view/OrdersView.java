package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;

import com.gjdev.hugo.gjant.data.api.model.Order;

import java.util.List;

@UiThread
public interface OrdersView {

    void resetFloatingActionButton();

    void setTitle(int resString);

    void setAppBarExpanded(boolean expanded);

    void showSnackbar(String message);

    void setupSwipeRefreshLayout();

    void setupRecyclerView();

    void setAdapter(List<Order> orderList);

    void setRefreshed(boolean refreshed);

}