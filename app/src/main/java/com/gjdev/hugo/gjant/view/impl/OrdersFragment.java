package com.gjdev.hugo.gjant.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.view.OrdersView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.OrdersPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.OrdersViewModule;
import com.gjdev.hugo.gjant.injection.DaggerOrdersViewComponent;

import javax.inject.Inject;

public final class OrdersFragment extends BaseFragment<OrdersPresenter, OrdersView> implements OrdersView {
    @Inject
    PresenterFactory<OrdersPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerOrdersViewComponent.builder()
                .appComponent(parentComponent)
                .ordersViewModule(new OrdersViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<OrdersPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
