package com.gjdev.hugo.gjant.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.event.ProductEvent;
import com.gjdev.hugo.gjant.view.DetailProductView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.DetailProductPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.DetailProductViewModule;
import com.gjdev.hugo.gjant.injection.DaggerDetailProductViewComponent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public final class DetailProductFragment extends BaseFragment<DetailProductPresenter, DetailProductView> implements DetailProductView {
    @Inject
    PresenterFactory<DetailProductPresenter> mPresenterFactory;

    @Inject
    EventBus mBus;

    // Your presenter is available using the mPresenter variable

    public DetailProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_product, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onProductEvent(ProductEvent productEvent){
        showSnackbar(String.valueOf(productEvent.getId()));
    }

    @Override
    public void onStart() {
        super.onStart();
        mBus.register(this);
    }

    @Override
    public void onStop() {
        mBus.unregister(this);
        super.onStop();
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerDetailProductViewComponent.builder()
                .appComponent(parentComponent)
                .detailProductViewModule(new DetailProductViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<DetailProductPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }
}
