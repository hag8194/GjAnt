package com.gjdev.hugo.gjant.data.event.product;

import com.gjdev.hugo.gjant.data.event.base.FailRetrieve;

/**
 * Created by Hugo on 04/01/2017.
 */

public class FailProductRetrieve extends FailRetrieve {
    public FailProductRetrieve(Throwable throwable) {
        super(throwable);
    }
}
