package com.gjdev.hugo.gjant.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.view.CreateOrderView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.CreateOrderPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.CreateOrderViewModule;
import com.gjdev.hugo.gjant.injection.DaggerCreateOrderViewComponent;
import com.gjdev.hugo.gjant.view.impl.adapter.CreateOrderStepperAdapter;
import com.stepstone.stepper.StepperLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class CreateOrderActivity extends BaseActivity<CreateOrderPresenter, CreateOrderView> implements CreateOrderView {
    @Inject
    PresenterFactory<CreateOrderPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    @BindView(R.id.stepperLayout)
    StepperLayout mStepperLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        ButterKnife.bind(this);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart or onPostCreate.
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

    @Override
    public void setupToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Create Order");
    }

    @Override
    public void setupStepper() {
        mStepperLayout.setAdapter(new CreateOrderStepperAdapter(getSupportFragmentManager()));
    }
}
