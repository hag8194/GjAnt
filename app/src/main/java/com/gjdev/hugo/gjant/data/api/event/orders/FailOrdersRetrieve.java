package com.gjdev.hugo.gjant.data.api.event.orders;

import com.gjdev.hugo.gjant.data.api.event.base.FailRetrieve;

/**
 * Created by Hugo on 04/01/2017.
 */

public class FailOrdersRetrieve extends FailRetrieve {
    public FailOrdersRetrieve(Throwable throwable) {
        super(throwable);
    }
}
