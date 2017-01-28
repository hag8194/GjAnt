package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.event.clientwallet.ErrorClientWalletRetrieve;
import com.gjdev.hugo.gjant.data.api.event.clientwallet.FailClientWalletRetrieve;
import com.gjdev.hugo.gjant.data.api.event.clientwallet.SuccessClientWalletRetrieve;
import com.gjdev.hugo.gjant.data.event.ClickedClientWalletListItem;
import com.gjdev.hugo.gjant.presenter.HomePresenter;
import com.gjdev.hugo.gjant.util.Messages;
import com.gjdev.hugo.gjant.view.HomeView;
import com.gjdev.hugo.gjant.interactor.HomeInteractor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public final class HomePresenterImpl extends BasePresenterImpl<HomeView> implements HomePresenter {
    /**
     * The interactor
     */
    @NonNull
    private final HomeInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public HomePresenterImpl(@NonNull HomeInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        EventBus.getDefault().register(this);
        mView.resetFloatingActionButton();
        mView.setAppBarExpanded(true);
        mView.setTitle(R.string.home);

        mInteractor.retrieveClientWallet();

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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessClientWalletRetrieve(SuccessClientWalletRetrieve retrieve) {
        mView.setupAdapter(retrieve.getClientWallet());
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorClientWalletRetrieve(ErrorClientWalletRetrieve retrieve) {
        mView.showSnackbar(Messages.errorMessage(retrieve.getApiError()));
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailClientWalletRetrieve(FailClientWalletRetrieve retrieve) {
        mView.showSnackbar(Messages.failureMessage(retrieve.getThrowable()));
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onClickedClientWalletListItem(ClickedClientWalletListItem listItem) {
        mInteractor.postSelectedClientWalletList(listItem);
        mView.loadClientDetailFragment();
    }
}