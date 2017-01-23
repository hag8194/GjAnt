package com.gjdev.hugo.gjant.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.model.ClientWallet;
import com.gjdev.hugo.gjant.view.HomeView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.HomePresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.HomeViewModule;
import com.gjdev.hugo.gjant.injection.DaggerHomeViewComponent;
import com.gjdev.hugo.gjant.view.impl.adapter.ClientListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class HomeFragment extends BaseFragment<HomePresenter, HomeView> implements HomeView {
    @Inject
    PresenterFactory<HomePresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    @BindView(R.id.client_wallet_list)
    RecyclerView mRecyclerView;

    private MainActivity mainActivity;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        mainActivity = (MainActivity)getActivity();

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
        DaggerHomeViewComponent.builder()
                .appComponent(parentComponent)
                .homeViewModule(new HomeViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<HomePresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void resetFloatingActionButton() {
        mainActivity.resetFloatingActionButton();
    }

    @Override
    public void setAppBarExpanded(boolean expanded) {
        mainActivity.mAppBarLayout.setExpanded(expanded, true);
    }

    @Override
    public void setTitle(int resString) {
        mainActivity.mCollapsingToolbarLayout.setTitle(getString(resString));
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setupAdapter(List<ClientWallet> clientWallets) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new ClientListAdapter(clientWallets));
    }
}
