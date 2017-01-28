package com.gjdev.hugo.gjant.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.view.ClientDetailView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.ClientDetailPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.ClientDetailViewModule;
import com.gjdev.hugo.gjant.injection.DaggerClientDetailViewComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;

public final class ClientDetailFragment extends BaseFragment<ClientDetailPresenter, ClientDetailView> implements ClientDetailView {
    @Inject
    PresenterFactory<ClientDetailPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    private MainActivity mainActivity;

    public ClientDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_client_detail, container, false);
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
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerClientDetailViewComponent.builder()
                .appComponent(parentComponent)
                .clientDetailViewModule(new ClientDetailViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<ClientDetailPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void setAppBarExpanded(boolean expanded) {
        mainActivity.mAppBarLayout.setExpanded(expanded, true);
    }

    @Override
    public void setTitle(String title) {
        mainActivity.mCollapsingToolbarLayout.setTitle(title);
    }

}
