package com.gjdev.hugo.gjant.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.view.CreateOrderView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.CreateOrderPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.CreateOrderViewModule;
import com.gjdev.hugo.gjant.injection.DaggerCreateOrderViewComponent;
import com.gjdev.hugo.gjant.view.impl.adapter.CreateOrderStepperAdapter;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class CreateOrderFragment extends BaseFragment<CreateOrderPresenter, CreateOrderView> implements CreateOrderView,
        StepperLayout.StepperListener {
    @Inject
    PresenterFactory<CreateOrderPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    @BindView(R.id.create_order_stepper_layout)
    StepperLayout mStepperLayout;

    private CreateOrderStepperAdapter mAdapter;
    private static final String CURRENT_STEP_POSITION_KEY = "position";

    public CreateOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_create_order, container, false);
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
    public void setupStepper() {
        mAdapter = new CreateOrderStepperAdapter(getFragmentManager());
        mStepperLayout.setAdapter(mAdapter);

        mStepperLayout.setListener(this);
    }

    @Override
    public void onCompleted(View completeButton) {

    }

    @Override
    public void onError(VerificationError verificationError) {

    }

    @Override
    public void onStepSelected(int newStepPosition) {
        Log.d(getClass().getName(), String.valueOf(newStepPosition));
        //Toast.makeText(getContext(),String.valueOf(newStepPosition), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onReturn() {

    }
}
