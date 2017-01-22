package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;
import android.view.View;

import com.gjdev.hugo.gjant.data.api.model.User;
import com.gjdev.hugo.gjant.data.event.NotifyChangeOfFragment;
import com.gjdev.hugo.gjant.data.event.UI.FloatingActionButtonChanges;

@UiThread
public interface MainView {

    void setupNavigationView();

    void setupNavigationHeader(User user);

    void setupToolbar();

    void setupDrawerToggle();

    void resetImageHeader();

    void resetFloatingActionButton();

    void setTextView(String test);

    void showSnackbar(String message);

    void loadHomeFragment(boolean addToBackTrace);

    void loadCatalogFragment(boolean addToBackTrace);

    void loadOrdersFragment(boolean addToBackTrace);

    void loadCartFragment(boolean addToBackTrace);

    void loadProductDetailFragment(boolean addToBackStack);

    void loadCreateOrderFragment(boolean addToBackStack);

    void startLoginActivity();

    void finish();
}