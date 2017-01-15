package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.presenter.OrderFormPresenter;
import com.gjdev.hugo.gjant.view.OrderFormView;
import com.gjdev.hugo.gjant.interactor.OrderFormInteractor;
import com.stepstone.stepper.VerificationError;

import javax.inject.Inject;

public final class OrderFormPresenterImpl extends BasePresenterImpl<OrderFormView> implements OrderFormPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final OrderFormInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public OrderFormPresenterImpl(@NonNull OrderFormInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        mView.setupSpinner();

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

    @Override
    public void onHasError(VerificationError error) {
        mView.showSnackbar(error.getErrorMessage());
    }
}