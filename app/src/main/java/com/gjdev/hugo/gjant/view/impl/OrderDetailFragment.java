package com.gjdev.hugo.gjant.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.view.OrderDetailView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.OrderDetailPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.OrderDetailViewModule;
import com.gjdev.hugo.gjant.injection.DaggerOrderDetailViewComponent;

import javax.inject.Inject;

public final class OrderDetailFragment extends BaseFragment<OrderDetailPresenter, OrderDetailView> implements OrderDetailView {
    @Inject
    PresenterFactory<OrderDetailPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public OrderDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerOrderDetailViewComponent.builder()
                .appComponent(parentComponent)
                .orderDetailViewModule(new OrderDetailViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<OrderDetailPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
