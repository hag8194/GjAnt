package com.gjdev.hugo.gjant.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.view.ReviewOrderView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.ReviewOrderPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.ReviewOrderViewModule;
import com.gjdev.hugo.gjant.injection.DaggerReviewOrderViewComponent;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import javax.inject.Inject;

import butterknife.ButterKnife;

public final class ReviewOrderFragment extends BaseFragment<ReviewOrderPresenter, ReviewOrderView> implements ReviewOrderView, Step {
    @Inject
    PresenterFactory<ReviewOrderPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public ReviewOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_review_order, container, false);
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
        DaggerReviewOrderViewComponent.builder()
                .appComponent(parentComponent)
                .reviewOrderViewModule(new ReviewOrderViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<ReviewOrderPresenter> getPresenterFactory() {
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
