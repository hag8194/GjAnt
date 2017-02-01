package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.presenter.MapPresenter;
import com.gjdev.hugo.gjant.view.MapView;
import com.gjdev.hugo.gjant.interactor.MapInteractor;

import javax.inject.Inject;

public final class MapPresenterImpl extends BasePresenterImpl<MapView> implements MapPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final MapInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public MapPresenterImpl(@NonNull MapInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        // Your code here. Your view is available using mView and will not be null until next onStop()

        mView.setupMapFragment();
    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()

        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        /*
         * Your code here. After this method, your presenter (and view) will be completely destroyed
         * so make sure to cancel any HTTP call or database connection
         */

        super.onPresenterDestroyed();
    }
}