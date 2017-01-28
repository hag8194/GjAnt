package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;

@UiThread
public interface ClientDetailView {

    void setAppBarExpanded(boolean expanded);

    void setTitle(String title);
}