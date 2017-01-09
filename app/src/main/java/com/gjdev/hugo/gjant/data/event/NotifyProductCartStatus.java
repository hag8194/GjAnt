package com.gjdev.hugo.gjant.data.event;

/**
 * Created by Hugo on 07/01/2017.
 */

public class NotifyProductCartStatus {
    private String message;

    public NotifyProductCartStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
