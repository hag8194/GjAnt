package com.gjdev.hugo.gjant.presenter;

import com.gjdev.hugo.gjant.data.model.ApiError;
import com.gjdev.hugo.gjant.data.model.User;
import com.gjdev.hugo.gjant.view.LoginView;

public interface LoginPresenter extends BasePresenter<LoginView> {
    void onRetrieveUserDataSuccess(User user);

    void onRetrieveUserDataError(ApiError error);

    void onRetrieveUserDataFail(Throwable t);

    void onSubmit(String username, String password);
}