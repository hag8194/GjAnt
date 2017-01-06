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

public final class ClientDetailFragment extends BaseFragment<ClientDetailPresenter, ClientDetailView> implements ClientDetailView {
    @Inject
    PresenterFactory<ClientDetailPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public ClientDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_client_detail, container, false);
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
}
