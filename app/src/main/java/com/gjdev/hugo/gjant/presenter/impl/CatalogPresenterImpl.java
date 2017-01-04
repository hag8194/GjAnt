package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.event.ProductEvent;
import com.gjdev.hugo.gjant.data.model.Product;
import com.gjdev.hugo.gjant.presenter.CatalogPresenter;
import com.gjdev.hugo.gjant.view.CatalogView;
import com.gjdev.hugo.gjant.interactor.CatalogInteractor;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

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

        mView.setupRecyclerView();
        mInteractor.retrieveProducts(this);

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
    public void onRetrieveProductListSuccess(List<Product> products) {
        if(mView != null) {
            mView.setupAdapter(products);
        }
    }

    @Override
    public void onClickProductItem(int position) {
        mView.sendProductEvent(mInteractor.getProduct(position).getId());
        mView.startDetailProductActivity();
    }
}