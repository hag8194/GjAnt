package com.gjdev.hugo.gjant.data.api.event.products;

import com.gjdev.hugo.gjant.data.api.event.base.FailRetrieve;

/**
 * Created by Hugo on 04/01/2017.
 */

public class FailProductsRetrieve extends FailRetrieve {
    public FailProductsRetrieve(Throwable throwable) {
        super(throwable);
    }
}
