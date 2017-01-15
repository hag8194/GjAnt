package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.gjdev.hugo.gjant.data.event.SelectedClientWallet;
import com.gjdev.hugo.gjant.presenter.ReviewOrderPresenter;
import com.gjdev.hugo.gjant.view.ReviewOrderView;
import com.gjdev.hugo.gjant.interactor.ReviewOrderInteractor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public final class ReviewOrderPresenterImpl extends BasePresenterImpl<ReviewOrderView> implements ReviewOrderPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final ReviewOrderInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public ReviewOrderPresenterImpl(@NonNull ReviewOrderInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);
        EventBus.getDefault().register(this);
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
    @Subscribe(sticky = true, threadMode = ThreadMode.ASYNC)
    public void onSelectedClientWallet(SelectedClientWallet selectedClientWallet) {
        Log.d(getClass().getName(), selectedClientWallet.getClientWallet().getClient().getFullname());
    }
}