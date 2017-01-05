package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.event.products.ErrorProductsRetrieve;
import com.gjdev.hugo.gjant.data.event.products.FailProductsRetrieve;
import com.gjdev.hugo.gjant.data.event.products.SuccessProductsRetrieve;
import com.gjdev.hugo.gjant.presenter.CatalogPresenter;
import com.gjdev.hugo.gjant.util.Messages;
import com.gjdev.hugo.gjant.view.CatalogView;
import com.gjdev.hugo.gjant.interactor.CatalogInteractor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public final class CatalogPresenterImpl extends BasePresenterImpl<CatalogView> implements CatalogPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final CatalogInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public CatalogPresenterImpl(@NonNull CatalogInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        EventBus.getDefault().register(this);

        if(!mView.isRecyclerViewActivated()){
            mView.setupRecyclerView();
            mInteractor.retrieveProducts(this);
        }

        // Your code here. Your view is available using mView and will not be null until next onStop()
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
    public void onClickProductItem(int position) {
        mView.sendProductEvent(mInteractor.getProduct(position).getId());
        mView.startDetailProductActivity();
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessProductsRetrieve(SuccessProductsRetrieve productsRetrieve) {
        if(mView != null) {
            mView.setupAdapter(productsRetrieve.getProducts());
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorProductsRetrieve(ErrorProductsRetrieve productsRetrieve) {
        mView.showSnackbar(Messages.errorMessage(productsRetrieve.getApiError()));
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailProductsRetrieve(FailProductsRetrieve productsRetrieve) {
        mView.showSnackbar(Messages.failureMessage(productsRetrieve.getThrowable()));
    }
}