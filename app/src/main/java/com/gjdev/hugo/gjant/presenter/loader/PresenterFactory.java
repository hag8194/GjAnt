package com.gjdev.hugo.gjant.presenter.loader;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.presenter.BasePresenter;

/**
 * Factory to implement to create a presenter
 */
public interface PresenterFactory<T extends BasePresenter> {
    @NonNull
    T create();
}
