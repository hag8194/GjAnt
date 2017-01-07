package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;

import com.gjdev.hugo.gjant.data.api.model.User;

@UiThread
public interface MainView {

    void setupNavigationView();

    void setupNavigationHeader(User user);

    void setupToolbar();

    void setupDrawerToggle();

    void setTextView(String test);

    void showSnackbar(String message);

    void loadHomeFragment();

    void loadCatalogFragment();

    void loadOrdersFragment();

    void loadCartFragment();

    void startLoginActivity();

    void finish();
}