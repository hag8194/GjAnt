package com.gjdev.hugo.gjant.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.view.OrderFormView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.OrderFormPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.OrderFormViewModule;
import com.gjdev.hugo.gjant.injection.DaggerOrderFormViewComponent;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import javax.inject.Inject;

import butterknife.ButterKnife;

public final class OrderFormFragment extends BaseFragment<OrderFormPresenter, OrderFormView> implements OrderFormView, Step {
    @Inject
    PresenterFactory<OrderFormPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public OrderFormFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_order_form, container, false);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerOrderFormViewComponent.builder()
                .appComponent(parentComponent)
                .orderFormViewModule(new OrderFormViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<OrderFormPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public int getName() {
        return R.string.review_order;
    }

    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}
