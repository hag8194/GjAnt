package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;

@UiThread
public interface CreateOrderView {

    void setupToolbar();

    void setupStepper();

    void collapseAppBarLayout();

    void showProgressBar();

    void finish();
}