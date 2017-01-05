package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.event.products.ErrorProductsRetrieve;
import com.gjdev.hugo.gjant.data.event.products.FailProductsRetrieve;
import com.gjdev.hugo.gjant.data.event.products.SuccessProductsRetrieve;
import com.gjdev.hugo.gjant.data.model.User;
import com.gjdev.hugo.gjant.presenter.MainPresenter;
import com.gjdev.hugo.gjant.view.MainView;
import com.gjdev.hugo.gjant.interactor.MainInteractor;

import javax.inject.Inject;

public final class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final MainInteractor mInteractor;

    private User user;

    // The view is available using the mView variable

    @Inject
    public MainPresenterImpl(@NonNull MainInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        // Your code here. Your view is available using mView and will not be null until next onStop()
        user = mInteractor.getUser();
        //mView.toggleFieldsState(user.getEmployer().getName());
        mView.setupToolbar();
        mView.setupDrawerToggle();
        mView.setupNavigationView();
        mView.setupNavigationHeader(user);
        mView.loadCatalogFragment();
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
    public void onCatalogOptionSelected() {
        mView.loadCatalogFragment();
    }

    @Override
    public void onOrdersOptionSelected() {
        mView.loadOrdersFragment();
    }

    @Override
    public void onLogoutOptionSelected() {
        if(mInteractor.deleteUserFileData()){
            mView.startLoginActivity();
            mView.finish();
        }
    }
}