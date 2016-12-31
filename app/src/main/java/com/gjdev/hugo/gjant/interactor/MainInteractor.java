package com.gjdev.hugo.gjant.interactor;

import com.gjdev.hugo.gjant.data.model.User;

public interface MainInteractor extends BaseInteractor {
    User getUser();

    boolean deleteUserFileData();
}