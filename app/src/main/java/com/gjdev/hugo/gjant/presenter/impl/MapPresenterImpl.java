package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.api.event.clientwallet.SuccessClientWalletRetrieve;
import com.gjdev.hugo.gjant.presenter.MapPresenter;
import com.gjdev.hugo.gjant.view.MapView;
import com.gjdev.hugo.gjant.interactor.MapInteractor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public final class MapPresenterImpl extends BasePresenterImpl<MapView> implements MapPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final MapInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public MapPresenterImpl(@NonNull MapInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        // Your code here. Your view is available using mView and will not be null until next onStop()
        EventBus.getDefault().register(this);
        mView.setupActivity();
        mView.setupMapFragment();

    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        /*
         * Your code here. After this method, your presenter (and view) will be completely destroyed
         * so make sure to cancel any HTTP call or database connection
         */

        super.onPresenterDestroyed();
    }

    @Override
    public void onMapReady() {
        mView.addMarkers(mInteractor.getClientWalletList());
        mView.setupCamera(mInteractor.getUser().getEmployer().getZone());
    }

    @Override
    @Subscribe(sticky = true, threadMode = ThreadMode.BACKGROUND)
    public void onSuccessClientWalletRetrieve(SuccessClientWalletRetrieve retrieve) {
        mInteractor.setClientWalletList(retrieve.getClientWallet());
    }
}