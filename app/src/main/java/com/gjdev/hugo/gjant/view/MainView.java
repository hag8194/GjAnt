package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;
import android.support.v4.widget.DrawerLayout;

import com.gjdev.hugo.gjant.data.model.User;

@UiThread
public interface MainView {
    void setupNavigationView();

    void setupNavigationHeader(User user);

    void setupToolbar();

    void setupDrawerToggle();

    void setTextView(String test);

    void showSnackbar(String message);

    void loadCatalogFragment();

    void loadOrdersFragment();

    void startLoginActivity();

    void finish();
}