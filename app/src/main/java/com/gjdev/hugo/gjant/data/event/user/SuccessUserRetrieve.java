package com.gjdev.hugo.gjant.data.event.user;

import com.gjdev.hugo.gjant.data.model.User;

/**
 * Created by Hugo on 04/01/2017.
 */

public class SuccessUserRetrieve {
    private User user;

    public SuccessUserRetrieve(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
