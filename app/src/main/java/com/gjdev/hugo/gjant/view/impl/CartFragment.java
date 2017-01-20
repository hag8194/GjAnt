package com.gjdev.hugo.gjant.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.sql.model.SQLProduct;
import com.gjdev.hugo.gjant.view.CartView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.CartPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.CartViewModule;
import com.gjdev.hugo.gjant.injection.DaggerCartViewComponent;
import com.gjdev.hugo.gjant.view.impl.adapter.CartListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public final class CartFragment extends BaseFragment<CartPresenter, CartView> implements CartView {
    @Inject
    PresenterFactory<CartPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @BindView(R.id.product_list)
    RecyclerView mRecyclerViewProductList;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @OnClick(R.id.create_order)
    void onClickActionButton() {
        mPresenter.onClickCreateOrderButton();
    }

    private CartListAdapter adapter;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        unbinder = ButterKnife.bind(this, rootView);
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
        DaggerCartViewComponent.builder()
                .appComponent(parentComponent)
                .cartViewModule(new CartViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<CartPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setupSwipeRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onRefreshRequest();
            }
        });
    }

    @Override
    public void setupRecyclerView() {
        mRecyclerViewProductList.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerViewProductList.setLayoutManager(mLayoutManager);
    }

    @Override
    public void setupAdapter(List<SQLProduct> products) {
        adapter = new CartListAdapter(products);
        mRecyclerViewProductList.setAdapter(adapter);
    }

    @Override
    public void notifyDataChanged() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void stopRefreshing() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void startProductDetailFragment() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new ProductDetailFragment(), ProductDetailFragment.class.getName())
                .addToBackStack(null)
                .commit();
    }
}
