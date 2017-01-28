package com.gjdev.hugo.gjant.data.api.event.client;

import com.gjdev.hugo.gjant.data.api.event.base.FailRetrieve;

/**
 * Created by Hugo on 04/01/2017.
 */

public class FailClientRetrieve extends FailRetrieve {
    public FailClientRetrieve(Throwable throwable) {
        super(throwable);
    }
}
