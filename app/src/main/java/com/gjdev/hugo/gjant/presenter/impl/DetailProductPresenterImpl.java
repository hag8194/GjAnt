package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.presenter.DetailProductPresenter;
import com.gjdev.hugo.gjant.view.DetailProductView;
import com.gjdev.hugo.gjant.interactor.DetailProductInteractor;

import javax.inject.Inject;

public final class DetailProductPresenterImpl extends BasePresenterImpl<DetailProductView> implements DetailProductPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final DetailProductInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public DetailProductPresenterImpl(@NonNull DetailProductInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        // Your code here. Your view is available using mView and will not be null until next onStop()
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