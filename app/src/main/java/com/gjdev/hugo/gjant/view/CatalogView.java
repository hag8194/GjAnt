package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;

@UiThread
public interface CatalogView {
    void showSnackbar(String message);
}