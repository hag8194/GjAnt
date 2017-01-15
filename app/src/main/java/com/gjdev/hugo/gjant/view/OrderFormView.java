package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;

@UiThread
public interface OrderFormView {

    void setupSpinner();

    void showSnackbar(String message);
}