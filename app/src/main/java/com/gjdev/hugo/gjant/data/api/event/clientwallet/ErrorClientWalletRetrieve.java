package com.gjdev.hugo.gjant.data.api.event.clientwallet;

import com.gjdev.hugo.gjant.data.api.event.base.ErrorRetrieve;
import com.gjdev.hugo.gjant.data.api.model.ApiError;

/**
 * Created by Hugo on 04/01/2017.
 */

public class ErrorClientWalletRetrieve extends ErrorRetrieve {
    public ErrorClientWalletRetrieve(ApiError apiError) {
        super(apiError);
    }
}
