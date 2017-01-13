package com.gjdev.hugo.gjant.data.api.event.clientwallet;

import com.gjdev.hugo.gjant.data.api.event.base.FailRetrieve;

/**
 * Created by Hugo on 04/01/2017.
 */

public class FailClientWalletRetrieve extends FailRetrieve {
    public FailClientWalletRetrieve(Throwable throwable) {
        super(throwable);
    }
}
