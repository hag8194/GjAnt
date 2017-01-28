package com.gjdev.hugo.gjant.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.model.Order;
import com.gjdev.hugo.gjant.view.OrdersView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.OrdersPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.OrdersViewModule;
import com.gjdev.hugo.gjant.injection.DaggerOrdersViewComponent;
import com.gjdev.hugo.gjant.view.impl.adapter.OrderListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class OrdersFragment extends BaseFragment<OrdersPresenter, OrdersView> implements OrdersView {
    @Inject
    PresenterFactory<OrdersPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.order_list)
    RecyclerView mRecyclerView;

    private MainActivity mainActivity;
    private OrderListAdapter adapter;

    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_orders, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        mainActivity = (MainActivity)getActivity();

        return rootView;
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

    @Override
    public void setupSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onRefreshRequest();
            }
        });
    }

    @Override
    public void resetFloatingActionButton() {
        mainActivity.resetFloatingActionButton();
    }

    @Override
    public void setTitle(int resString) {
        mainActivity.mCollapsingToolbarLayout.setTitle(getResources().getString(resString));
    }

    @Override
    public void setAppBarExpanded(boolean expanded) {
        mainActivity.mAppBarLayout.setExpanded(expanded, true);
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setupRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void setAdapter(List<Order> orderList) {
        adapter = new OrderListAdapter(orderList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setRefreshed(boolean refreshed) {
        mSwipeRefreshLayout.setRefreshing(refreshed);
    }
}
