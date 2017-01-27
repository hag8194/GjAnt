package com.gjdev.hugo.gjant.data.api.event.enterprise;

import com.gjdev.hugo.gjant.data.api.event.base.FailRetrieve;

/**
 * Created by Hugo on 04/01/2017.
 */

public class FailEnterpriseRetrieve extends FailRetrieve {
    public FailEnterpriseRetrieve(Throwable throwable) {
        super(throwable);
    }
}
