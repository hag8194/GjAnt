package com.gjdev.hugo.gjant.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.model.Product;
import com.gjdev.hugo.gjant.view.CatalogView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.CatalogPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.CatalogViewModule;
import com.gjdev.hugo.gjant.injection.DaggerCatalogViewComponent;
import com.gjdev.hugo.gjant.view.impl.adapter.ProductListAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;
import butterknife.BindView;

public final class CatalogFragment extends BaseFragment<CatalogPresenter, CatalogView> implements CatalogView {

    @Inject
    PresenterFactory<CatalogPresenter> mPresenterFactory;

    @Inject
    Picasso mPicasso;

    // Your presenter is available using the mPresenter variable

    @BindView(R.id.product_list)
    RecyclerView mRecyclerViewProductList;

    public CatalogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerCatalogViewComponent.builder()
                .appComponent(parentComponent)
                .catalogViewModule(new CatalogViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<CatalogPresenter> getPresenterFactory() {
        return mPresenterFactory;
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

    public void setupAdapter(List<Product> products) {
        ProductListAdapter adapter = new ProductListAdapter(products, mPicasso);
        adapter.setOnItemClickListener(new ProductListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                mPresenter.onClickProductItem(position);
            }
        });
        mRecyclerViewProductList.setAdapter(adapter);
    }
}
