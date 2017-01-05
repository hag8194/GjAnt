package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.event.SelectProductImage;
import com.gjdev.hugo.gjant.data.event.product.SuccessProductRetrieve;
import com.gjdev.hugo.gjant.data.model.Product;
import com.gjdev.hugo.gjant.interactor.ProductDetailInteractor;
import com.gjdev.hugo.gjant.presenter.ProductDetailPresenter;
import com.gjdev.hugo.gjant.view.ProductDetailView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public final class ProductDetailPresenterImpl extends BasePresenterImpl<ProductDetailView> implements ProductDetailPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final ProductDetailInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public ProductDetailPresenterImpl(@NonNull ProductDetailInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        EventBus.getDefault().register(this);
        mView.setupRecyclerViews();

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
    public void onReceiveProductEvent(int id) {
        mInteractor.retrieveProductDetail(id);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessProductRetrieve(SuccessProductRetrieve retrieve) {
        Product product = retrieve.getProduct();
        mView.setProductData(product);
        mView.setupAdapters(product.getProductImages(), product.getChildren());
        mView.hideProgressBar();
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectProductImage(SelectProductImage productImage) {
        mView.changeProductPoster(productImage.getImageDrawable());
    }
}