package com.gjdev.hugo.gjant.data.api.event.createorder;

import com.gjdev.hugo.gjant.data.api.event.base.FailRetrieve;

/**
 * Created by Hugo on 04/01/2017.
 */

public class FailCreateOrder extends FailRetrieve {
    public FailCreateOrder(Throwable throwable) {
        super(throwable);
    }
}
