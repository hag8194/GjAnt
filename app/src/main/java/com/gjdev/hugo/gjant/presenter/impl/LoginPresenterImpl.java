package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.api.event.user.ErrorUserRetrieve;
import com.gjdev.hugo.gjant.data.api.event.user.FailUserRetrieve;
import com.gjdev.hugo.gjant.data.api.event.user.SuccessUserRetrieve;
import com.gjdev.hugo.gjant.presenter.LoginPresenter;
import com.gjdev.hugo.gjant.util.Messages;
import com.gjdev.hugo.gjant.view.LoginView;
import com.gjdev.hugo.gjant.interactor.LoginInteractor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public final class LoginPresenterImpl extends BasePresenterImpl<LoginView> implements LoginPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final LoginInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public LoginPresenterImpl(@NonNull LoginInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        // Your code here. Your view is available using mView and will not be null until next onStop()

        EventBus.getDefault().register(this);
        if(mInteractor.getAuthenticatedUser() != null){
            mView.finish();
            mView.startMainActivity();
        }
        else
            mView.hideProgressBar();
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
    public void onSubmit(String username, String password) {
        mView.toggleFieldsState();
        mView.showProgressBar();
        mInteractor.retrieveUserData(username, password);
    }


    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessUserRetrieve(SuccessUserRetrieve userRetrieve) {
        mInteractor.setAuthenticatedUser(userRetrieve.getUser());
        mView.startMainActivity();
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorUserRetrieve(ErrorUserRetrieve userRetrieve) {
        mView.hideProgressBar();
        mView.toggleFieldsState();
        mView.showSnackbar(Messages.errorMessage(userRetrieve.getApiError()));
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailUserRetrieve(FailUserRetrieve userRetrieve) {
        mView.hideProgressBar();
        mView.toggleFieldsState();
        mView.showSnackbar(Messages.failureMessage(userRetrieve.getThrowable()));
    }
}