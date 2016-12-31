package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.gjdev.hugo.gjant.data.model.ApiError;
import com.gjdev.hugo.gjant.data.model.User;
import com.gjdev.hugo.gjant.presenter.LoginPresenter;
import com.gjdev.hugo.gjant.view.LoginView;
import com.gjdev.hugo.gjant.interactor.LoginInteractor;

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
        if(mInteractor.getAuthenticatedUser() != null){
            mView.startMainActivity();
            mView.finish();
            Log.d(this.getClass().getName(), "Yeii");
        }
        else
        {
            mView.hideProgressBar();
            Log.d(this.getClass().getName(), "Not Yeii");
        }

    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()

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
        mInteractor.retrieveUserData(this, username, password);
    }

    @Override
    public void onRetrieveUserDataSuccess(User user) {
        //mView.hideProgressBar();
        mInteractor.setAuthenticatedUser(user);
        mView.startMainActivity();
    }

    @Override
    public void onRetrieveUserDataError(ApiError error) {
        String message = "Error: " + error.getName() + "\nStatus: " + error.getStatus() + ", " + error.getMessage();
        mView.hideProgressBar();
        mView.toggleFieldsState();
        mView.showSnackbar(message);
    }
}