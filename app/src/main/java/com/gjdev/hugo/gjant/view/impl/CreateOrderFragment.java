package com.gjdev.hugo.gjant.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.view.CreateOrderView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.CreateOrderPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.CreateOrderViewModule;
import com.gjdev.hugo.gjant.injection.DaggerCreateOrderViewComponent;

import javax.inject.Inject;

public final class CreateOrderFragment extends BaseFragment<CreateOrderPresenter, CreateOrderView> implements CreateOrderView {
    @Inject
    PresenterFactory<CreateOrderPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public CreateOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_order, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerCreateOrderViewComponent.builder()
                .appComponent(parentComponent)
                .createOrderViewModule(new CreateOrderViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<CreateOrderPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
