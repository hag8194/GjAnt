package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;

@UiThread
public interface CreateOrderView {

    void showToast(String message);

    void setupToolbar();

    void setupStepper();

    void showProgressDialog(int title, int message);

    void finish();
}