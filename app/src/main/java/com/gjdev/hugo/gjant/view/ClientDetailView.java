package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;

@UiThread
public interface ClientDetailView {

    void setAppBarExpanded(boolean expanded);

    void setTitle(String title);

    void setupCollapsingToolbarLayout();

    void setImageHeader(String url);

    void setClientData(String[] data);
}