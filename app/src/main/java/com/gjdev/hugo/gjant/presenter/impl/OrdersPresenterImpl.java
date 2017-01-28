package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.event.orders.ErrorOrdersRetrieve;
import com.gjdev.hugo.gjant.data.api.event.orders.FailOrdersRetrieve;
import com.gjdev.hugo.gjant.data.api.event.orders.SuccessOrdersRetrieve;
import com.gjdev.hugo.gjant.presenter.OrdersPresenter;
import com.gjdev.hugo.gjant.util.Messages;
import com.gjdev.hugo.gjant.view.OrdersView;
import com.gjdev.hugo.gjant.interactor.OrdersInteractor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public final class OrdersPresenterImpl extends BasePresenterImpl<OrdersView> implements OrdersPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final OrdersInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public OrdersPresenterImpl(@NonNull OrdersInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        EventBus.getDefault().register(this);

        mView.resetFloatingActionButton();
        mView.setAppBarExpanded(true);
        mView.setTitle(R.string.orders);

        mView.setupSwipeRefreshLayout();
        mView.setupRecyclerView();
        mInteractor.retrieveOrders(false);

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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessOrdersRetrieve(SuccessOrdersRetrieve retrieve) {
        mView.setAdapter(retrieve.getOrderList());
        mView.setRefreshed(false);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorOrdersRetrieve(ErrorOrdersRetrieve retrieve) {
        mView.showSnackbar(Messages.errorMessage(retrieve.getApiError()));
        mView.setRefreshed(false);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailOrdersRetrieve(FailOrdersRetrieve retrieve) {
        mView.showSnackbar(Messages.failureMessage(retrieve.getThrowable()));
        mView.setRefreshed(false);
    }

    @Override
    public void onRefreshRequest() {
        mInteractor.retrieveOrders(true);
    }
}