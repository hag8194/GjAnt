package com.gjdev.hugo.gjant.interactor.impl;

import android.content.Context;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.UserService;
import com.gjdev.hugo.gjant.data.model.User;
import com.gjdev.hugo.gjant.interactor.LoginInteractor;
import com.gjdev.hugo.gjant.presenter.LoginPresenter;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;
import com.squareup.otto.Bus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class LoginInteractorImpl implements LoginInteractor {
    private UserService mUserService;
    private ApiErrorHandler mApiErrorHandler;
    private Bus mBus;
    private InternalStorageHandler mInternalStorageHandler;

    @Inject
    public LoginInteractorImpl(UserService userService, ApiErrorHandler apiErrorHandler, Bus bus,
                               InternalStorageHandler internalStorageHandler) {
        mUserService = userService;
        mApiErrorHandler = apiErrorHandler;
        mBus = bus;
        mInternalStorageHandler = internalStorageHandler;
    }

    @Override
    public void retrieveUserData(final LoginPresenter loginPresenter, String username, String password) {

        Call<User> login = mUserService.login(username, password);

        login.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful())
                    loginPresenter.onRetrieveUserDataSuccess(response.body());
                else
                    loginPresenter.onRetrieveUserDataError(mApiErrorHandler.parseError(response));
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    @Override
    public void setAuthenticatedUser(User user) {
        mInternalStorageHandler.saveObject(R.string.user_data, user, Context.MODE_PRIVATE);
    }

    @Nullable
    @Override
    public User getAuthenticatedUser() {
        User user = (User)mInternalStorageHandler.readObject(R.string.user_data);
        return (user != null) ? user : null;
    }
}