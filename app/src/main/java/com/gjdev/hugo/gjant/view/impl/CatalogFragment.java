package com.gjdev.hugo.gjant.view.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.view.CatalogView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.CatalogPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.CatalogViewModule;
import com.gjdev.hugo.gjant.injection.DaggerCatalogViewComponent;
import com.gjdev.hugo.gjant.view.MainView;
import com.gjdev.hugo.gjant.view.impl.adapter.ProductListAdapter;

import javax.inject.Inject;
import butterknife.BindView;

public final class CatalogFragment extends BaseFragment<CatalogPresenter, CatalogView> implements CatalogView {
    @Inject
    PresenterFactory<CatalogPresenter> mPresenterFactory;

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
}
