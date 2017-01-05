package com.gjdev.hugo.gjant.data.event.user;

import com.gjdev.hugo.gjant.data.event.base.FailRetrieve;

/**
 * Created by Hugo on 04/01/2017.
 */

public class FailUserRetrieve extends FailRetrieve{
    public FailUserRetrieve(Throwable throwable) {
        super(throwable);
    }
}
