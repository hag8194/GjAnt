package com.gjdev.hugo.gjant.interactor.impl;

import android.content.Context;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.UserService;
import com.gjdev.hugo.gjant.data.event.user.ErrorUserRetrieve;
import com.gjdev.hugo.gjant.data.event.user.FailUserRetrieve;
import com.gjdev.hugo.gjant.data.event.user.SuccessUserRetrieve;
import com.gjdev.hugo.gjant.data.model.ApiError;
import com.gjdev.hugo.gjant.data.model.User;
import com.gjdev.hugo.gjant.interactor.LoginInteractor;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class LoginInteractorImpl implements LoginInteractor {
    private UserService mUserService;
    private ApiErrorHandler mApiErrorHandler;
    private InternalStorageHandler mInternalStorageHandler;

    @Inject
    public LoginInteractorImpl(UserService userService, ApiErrorHandler apiErrorHandler,
                               InternalStorageHandler internalStorageHandler) {
        mUserService = userService;
        mApiErrorHandler = apiErrorHandler;
        mInternalStorageHandler = internalStorageHandler;
    }

    @Override
    public void retrieveUserData(String username, String password) {

        Call<User> loginCall = mUserService.login(username, password);

        loginCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful())
                    postEvent(SUCCESS_EVENT, response.body());
                else
                    postEvent(ERROR_EVENT, mApiErrorHandler.parseError(response));
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                postEvent(FAILURE_EVENT, t);
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

    @Override
    public void postEvent(int kindOfEvent, Object object) {
        EventBus eventBus = EventBus.getDefault();
        switch (kindOfEvent) {
            case FAILURE_EVENT:
                eventBus.post(new FailUserRetrieve((Throwable) object));
                break;
            case SUCCESS_EVENT:
                eventBus.post(new SuccessUserRetrieve((User) object));
                break;
            case ERROR_EVENT:
                eventBus.post(new ErrorUserRetrieve((ApiError) object));
                break;
        }
    }

}