package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.api.event.createorder.SuccessCreateOrder;
import com.gjdev.hugo.gjant.data.event.NotifyChangeOfFragment;
import com.gjdev.hugo.gjant.presenter.MainPresenter;
import com.gjdev.hugo.gjant.view.MainView;
import com.gjdev.hugo.gjant.interactor.MainInteractor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        EventBus.getDefault().register(this);

        mView.setupToolbar();
        mView.setupDrawerToggle();
        mView.setupNavigationView();
        mView.setupNavigationHeader(mInteractor.getUser());
        mView.loadHomeFragment(false);
    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()
        EventBus.getDefault().unregister(this);
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
        mView.loadHomeFragment(false);
    }

    @Override
    public void onCatalogOptionSelected() {
        mView.loadCatalogFragment(false);
    }

    @Override
    public void onOrdersOptionSelected() {
        mView.loadOrdersFragment(false);
    }

    @Override
    public void onCartOptionSelected() {
        mView.loadCartFragment(false);
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

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNotifyChangeOfFragment(NotifyChangeOfFragment changeOfFragment) {
        switch (changeOfFragment.getFragment()) {
            case NotifyChangeOfFragment.PRODUCT_DETAIL_FRAGMENT:
                mView.loadProductDetailFragment(true);
                break;
            case NotifyChangeOfFragment.CART_FRAGMENT:
                mView.loadCartFragment(true);
                break;
            case NotifyChangeOfFragment.CREATE_ORDER_FRAGMENT:
                mView.loadCreateOrderFragment(true);
                break;
        }
    }

    @Override
    @Subscribe(sticky = true)
    public void onSuccessCreateOrder(SuccessCreateOrder successCreateOrder) {
        EventBus.getDefault().removeAllStickyEvents();
        mView.showSnackbar(successCreateOrder.getResponseMessage().getMessage());
    }
}