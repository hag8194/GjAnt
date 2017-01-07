package com.gjdev.hugo.gjant.data.event;

import com.gjdev.hugo.gjant.data.api.model.User;

/**
 * Created by Hugo on 04/01/2017.
 */

public class AuthenticatedEvent {
    private User user;

    public AuthenticatedEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
