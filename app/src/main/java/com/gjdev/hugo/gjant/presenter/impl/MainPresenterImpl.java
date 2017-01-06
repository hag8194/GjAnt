package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;

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

    // The view is available using the mView variable

    @Inject
    public MainPresenterImpl(@NonNull MainInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        // Your code here. Your view is available using mView and will not be null until next onStop()

        mView.setupToolbar();
        mView.setupDrawerToggle();
        mView.setupNavigationView();
        mView.setupNavigationHeader(mInteractor.getUser());
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
    public void onHomeOptionSelected() {
        mView.loadHomeFragment();
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
    public void onCartOptionSelected() {
        mView.loadCartFragment();
    }

    @Override
    public void onLogoutOptionSelected() {
        if(mInteractor.deleteUserFileData()){
            mView.startLoginActivity();
            mView.finish();
        }
        else
            mView.showSnackbar("Cannot logout");
    }

    @Override
    public void onSettingsOptionSelected() {
        mView.showSnackbar("Settings!!");
    }


}