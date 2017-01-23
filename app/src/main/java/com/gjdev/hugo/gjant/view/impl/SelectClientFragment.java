package com.gjdev.hugo.gjant.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.model.ClientWallet;
import com.gjdev.hugo.gjant.view.SelectClientView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.SelectClientPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.SelectClientViewModule;
import com.gjdev.hugo.gjant.injection.DaggerSelectClientViewComponent;
import com.gjdev.hugo.gjant.view.impl.adapter.ClientListAdapter;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class SelectClientFragment extends BaseFragment<SelectClientPresenter, SelectClientView> implements SelectClientView, Step {
    @Inject
    PresenterFactory<SelectClientPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    @BindView(R.id.client_wallet_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private ClientListAdapter mAdapter;

    private ImageView mOldSelectedAddImage;

    public SelectClientFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_select_client, container, false);
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
        DaggerSelectClientViewComponent.builder()
                .appComponent(parentComponent)
                .selectClientViewModule(new SelectClientViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<SelectClientPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public VerificationError verifyStep() {
        return mOldSelectedAddImage == null ? new VerificationError(getString(R.string.you_must_select_a_customer)) : null;
    }

    @Override
    public void onSelected() {
        mPresenter.onSelected();
    }

    @Override
    public void onError(@NonNull VerificationError error) {
        mPresenter.onHasError(error);
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
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void setupAdapter(List<ClientWallet> clientWallets) {
        mAdapter = new ClientListAdapter(clientWallets);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void loadSelectedAddImage(int adapterPosition) {
        mRecyclerView.findViewHolderForAdapterPosition(adapterPosition);
    }

    @Override
    public void changeSelectedAddImage(ImageView imageView) {
        if(mOldSelectedAddImage != null)
            mOldSelectedAddImage.setImageResource(R.drawable.ic_add_black_24dp);

        imageView.setImageResource(R.drawable.ic_add_color_primary_24dp);
        mOldSelectedAddImage = imageView;
    }

}
