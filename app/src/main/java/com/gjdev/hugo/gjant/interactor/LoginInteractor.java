package com.gjdev.hugo.gjant.interactor;

import com.gjdev.hugo.gjant.data.model.User;
import com.gjdev.hugo.gjant.presenter.LoginPresenter;

public interface LoginInteractor extends BaseInteractor {
    void retrieveUserData(LoginPresenter loginPresenter, String username, String password);

    void setAuthenticatedUser(User user);

    User getAuthenticatedUser();
}