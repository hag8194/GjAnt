package com.gjdev.hugo.gjant.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.view.OrderFormView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.OrderFormPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.OrderFormViewModule;
import com.gjdev.hugo.gjant.injection.DaggerOrderFormViewComponent;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class OrderFormFragment extends BaseFragment<OrderFormPresenter, OrderFormView> implements OrderFormView, BlockingStep {
    @Inject
    PresenterFactory<OrderFormPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    @BindView(R.id.order_type)
    Spinner mSpinner;

    @BindView(R.id.order_description)
    EditText mEditText;

    private int spinnerSelectedPosition = -1;

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
    public VerificationError verifyStep() {
        return spinnerSelectedPosition == -1 ? new VerificationError(getString(R.string.you_must_select_type)) : null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {
        mPresenter.onHasError(error);
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        mPresenter.onNextClicked(mSpinner.getSelectedItemPosition(), mEditText.getText().toString());
        callback.goToNextStep();
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }

    @Override
    public void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.order_type, android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerSelectedPosition = position - 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }
}
