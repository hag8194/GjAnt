package com.gjdev.hugo.gjant.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private CartListAdapter adapter;
    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private FloatingActionButton mFloatingActionButton;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        mAppBarLayout = ButterKnife.findById(getActivity(), R.id.app_bar_layout);
        mCollapsingToolbarLayout = ButterKnife.findById(getActivity(), R.id.collapsing_toolbar_layout);
        mFloatingActionButton = ButterKnife.findById(getActivity(), R.id.floating_action_button);

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
    public void setTitle(int resString) {
        mCollapsingToolbarLayout.setTitle(getString(resString));
    }

    @Override
    public void setAppBarExpanded(boolean expanded) {
        mAppBarLayout.setExpanded(expanded, true);
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
    public void setupFloatingActionButton() {
        if(mFloatingActionButton.getVisibility() == View.GONE)
            mFloatingActionButton.setVisibility(View.VISIBLE);
        mFloatingActionButton.setImageResource(R.drawable.ic_add_white_24dp);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onClickCreateOrderButton();
            }
        });
    }

    @Override
    public void notifyDataChanged() {
        adapter.notifyDataSetChanged();
    }


    @Override
    public void startProductDetailFragment() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new ProductDetailFragment(), ProductDetailFragment.class.getName())
                .addToBackStack(null)
                .commit();
    }
}
