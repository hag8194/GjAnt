package com.gjdev.hugo.gjant.interactor;

import com.gjdev.hugo.gjant.data.api.model.User;

public interface LoginInteractor extends BaseInteractor {
    void retrieveUserData(String username, String password);

    void setAuthenticatedUser(User user);

    User getAuthenticatedUser();
}