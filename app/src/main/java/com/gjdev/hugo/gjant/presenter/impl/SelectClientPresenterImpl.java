package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.gjdev.hugo.gjant.data.api.event.clientwallet.ErrorClientWalletRetrieve;
import com.gjdev.hugo.gjant.data.api.event.clientwallet.FailClientWalletRetrieve;
import com.gjdev.hugo.gjant.data.api.event.clientwallet.SuccessClientWalletRetrieve;
import com.gjdev.hugo.gjant.data.event.ClickedClientWalletListItem;
import com.gjdev.hugo.gjant.data.event.SelectedClientWallet;
import com.gjdev.hugo.gjant.presenter.SelectClientPresenter;
import com.gjdev.hugo.gjant.util.Messages;
import com.gjdev.hugo.gjant.view.SelectClientView;
import com.gjdev.hugo.gjant.interactor.SelectClientInteractor;
import com.stepstone.stepper.VerificationError;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public final class SelectClientPresenterImpl extends BasePresenterImpl<SelectClientView> implements SelectClientPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final SelectClientInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public SelectClientPresenterImpl(@NonNull SelectClientInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);
        EventBus.getDefault().register(this);

        mView.setupRecyclerView();
        mInteractor.retrieveClientWallet();
        int adapterPosition = mInteractor.retrieveSelectedClientWalletPosition();

        if(adapterPosition != -1){
            mView.loadSelectedAddImage(adapterPosition);
        }
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
    public void onSelected() {

    }

    @Override
    public void onHasError(VerificationError error) {
        mView.showSnackbar(error.getErrorMessage());
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessClientWalletRetrieve(SuccessClientWalletRetrieve retrieve) {
        mView.setupAdapter(retrieve.getClientWallet());
        mView.hideProgressBar();
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorClientWalletRetrieve(ErrorClientWalletRetrieve retrieve) {
        mView.showSnackbar(Messages.errorMessage(retrieve.getApiError()));
        mView.hideProgressBar();
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailClientWalletRetrieve(FailClientWalletRetrieve retrieve) {
        mView.showSnackbar(Messages.failureMessage(retrieve.getThrowable()));
        mView.hideProgressBar();
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onClickedClientWalletListItem(ClickedClientWalletListItem clickedItem) {
        EventBus.getDefault().postSticky(new SelectedClientWallet(mInteractor.getClientWallet(clickedItem.getAdapterPosition())));
        mInteractor.saveSelectedClientWalletPosition(clickedItem.getAdapterPosition());

        mView.changeSelectedAddImage((ImageView)clickedItem.getClickedView());
        mView.setExpandedAppBarLayout(false);
    }
}