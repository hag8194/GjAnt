package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;

@UiThread
public interface LoginView {
    void toggleFieldsState();

    void showProgressBar();

    void hideProgressBar();

    void showSnackbar(String error);

    void startMainActivity();

    void finish();
}