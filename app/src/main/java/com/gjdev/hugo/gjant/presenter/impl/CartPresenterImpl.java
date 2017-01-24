package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.event.ClickedProductListItem;
import com.gjdev.hugo.gjant.data.event.LongClickedProductListItem;
import com.gjdev.hugo.gjant.data.event.NotifyChangeOfFragment;
import com.gjdev.hugo.gjant.data.event.RefreshedList;
import com.gjdev.hugo.gjant.data.event.RemoveCartItem;
import com.gjdev.hugo.gjant.data.sql.event.SuccessCartProductsRetrieve;
import com.gjdev.hugo.gjant.presenter.CartPresenter;
import com.gjdev.hugo.gjant.view.CartView;
import com.gjdev.hugo.gjant.interactor.CartInteractor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public final class CartPresenterImpl extends BasePresenterImpl<CartView> implements CartPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final CartInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public CartPresenterImpl(@NonNull CartInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        EventBus.getDefault().register(this);

        mView.setupFloatingActionButton();
        mView.setTitle(R.string.cart);
        mView.setupRecyclerView();
        mInteractor.retrieveProductsInCart();

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
    @Subscribe
    public void onSuccessCartProductsRetrieve(SuccessCartProductsRetrieve productsRetrieve) {
        mView.setupAdapter(productsRetrieve.getProducts());
        mView.hideProgressBar();
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onClickedProductListItem(ClickedProductListItem listItem) {
        mInteractor.postSelectedProduct(mInteractor.getProduct(listItem.getAdapterPosition()).getKey());
        EventBus.getDefault().post(new NotifyChangeOfFragment(NotifyChangeOfFragment.PRODUCT_DETAIL_FRAGMENT));
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLongClickedProductListItem(LongClickedProductListItem listItem) {
        mView.showSnackbar(String.valueOf(listItem.getAdapterPosition()));
    }

    @Override
    public void onClickCreateOrderButton() {
        EventBus.getDefault().post(new NotifyChangeOfFragment(NotifyChangeOfFragment.CREATE_ORDER_FRAGMENT));
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onRemoveCartItem(RemoveCartItem removeCartItem) {
        mInteractor.removeCartItem(removeCartItem.getProduct());
    }
}