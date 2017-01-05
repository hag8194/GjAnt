package com.gjdev.hugo.gjant.data.event.base;

/**
 * Created by Hugo on 04/01/2017.
 */

public abstract class FailRetrieve {
    private Throwable throwable;

    public FailRetrieve(Throwable throwable) {
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
