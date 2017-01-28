package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.event.createorder.ErrorCreateOrder;
import com.gjdev.hugo.gjant.data.api.event.createorder.FailCreateOrder;
import com.gjdev.hugo.gjant.data.api.event.createorder.SuccessCreateOrder;
import com.gjdev.hugo.gjant.data.event.CollapseAppBarLayout;
import com.gjdev.hugo.gjant.data.event.SelectedClientWallet;
import com.gjdev.hugo.gjant.data.event.ValidOrderForm;
import com.gjdev.hugo.gjant.data.sql.event.SuccessCartProductsRetrieve;
import com.gjdev.hugo.gjant.presenter.CreateOrderPresenter;
import com.gjdev.hugo.gjant.util.Messages;
import com.gjdev.hugo.gjant.view.CreateOrderView;
import com.gjdev.hugo.gjant.interactor.CreateOrderInteractor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public final class CreateOrderPresenterImpl extends BasePresenterImpl<CreateOrderView> implements CreateOrderPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final CreateOrderInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public CreateOrderPresenterImpl(@NonNull CreateOrderInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        EventBus.getDefault().register(this);

        mView.setupToolbar();
        mView.setupStepper();
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
    @Subscribe(sticky = true, threadMode = ThreadMode.ASYNC)
    public void onSelectedClientWallet(SelectedClientWallet selectedClientWallet) {
        mInteractor.setSelectedClientWallet(selectedClientWallet);
    }

    @Override
    @Subscribe(sticky = true, threadMode = ThreadMode.ASYNC)
    public void onValidOrderForm(ValidOrderForm validOrderForm) {
        mInteractor.setValidOrderForm(validOrderForm);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onSuccessCartProductsRetrieve(SuccessCartProductsRetrieve retrieve) {
        mInteractor.postOrderParams();
    }

    @Override
    public void onSelectedReviewOrderStep() {
        mInteractor.createOrderCode();
        mInteractor.retrieveProductsInCart();
    }

    @Override
    public void onCompletedSteps() {
        mView.showProgressDialog(R.string.create_order, R.string.creating_order);
        mInteractor.createOrder();
    }

    @Override
    @Subscribe(sticky = true, threadMode = ThreadMode.ASYNC)
    public void onSuccessCreateOrder(SuccessCreateOrder successCreateOrder) {
        mInteractor.deleteProductsInCart();
        mView.finish();
    }

    @Override
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onErrorCreateOrder(ErrorCreateOrder errorCreateOrder) {
        mView.showToast(Messages.errorMessage(errorCreateOrder.getApiError()));
    }

    @Override
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onFailCreateOrder(FailCreateOrder failCreateOrder) {
        mView.showToast(Messages.failureMessage(failCreateOrder.getThrowable()));
    }
}