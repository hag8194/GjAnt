package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.event.client.ErrorClientRetrieve;
import com.gjdev.hugo.gjant.data.api.event.client.FailClientRetrieve;
import com.gjdev.hugo.gjant.data.api.event.client.SuccessClientRetrieve;
import com.gjdev.hugo.gjant.data.event.SelectedClientWallet;
import com.gjdev.hugo.gjant.presenter.ClientDetailPresenter;
import com.gjdev.hugo.gjant.view.ClientDetailView;
import com.gjdev.hugo.gjant.interactor.ClientDetailInteractor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public final class ClientDetailPresenterImpl extends BasePresenterImpl<ClientDetailView> implements ClientDetailPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final ClientDetailInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public ClientDetailPresenterImpl(@NonNull ClientDetailInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        EventBus.getDefault().register(this);

        mView.setAppBarExpanded(true);

        // Your code here. Your view is available using mView and will not be null until next onStop()
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
    @Subscribe(sticky = true)
    public void onSelectedClientWallet(SelectedClientWallet clientWallet) {
        mInteractor.retrieveClient(clientWallet.getClientWallet().getClient().getId());
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessClientRetrieve(SuccessClientRetrieve retrieve) {
        mView.setTitle(retrieve.getClient().getFullname());
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorClientRetrieve(ErrorClientRetrieve retrieve) {
        Log.d(getClass().getName(), retrieve.getApiError().getMessage());
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailClientRetrieve(FailClientRetrieve retrieve) {
        Log.d(getClass().getName(), retrieve.getThrowable().getMessage());
    }
}