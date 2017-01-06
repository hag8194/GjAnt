package com.gjdev.hugo.gjant.presenter;

import com.gjdev.hugo.gjant.data.api.event.user.ErrorUserRetrieve;
import com.gjdev.hugo.gjant.data.api.event.user.FailUserRetrieve;
import com.gjdev.hugo.gjant.data.api.event.user.SuccessUserRetrieve;
import com.gjdev.hugo.gjant.view.LoginView;

public interface LoginPresenter extends BasePresenter<LoginView> {
    void onSuccessUserRetrieve(SuccessUserRetrieve userRetrieve);

    void onErrorUserRetrieve(ErrorUserRetrieve userRetrieve);

    void onFailUserRetrieve(FailUserRetrieve userRetrieve);

    void onSubmit(String username, String password);
}