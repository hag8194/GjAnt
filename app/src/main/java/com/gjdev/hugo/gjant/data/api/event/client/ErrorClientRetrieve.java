package com.gjdev.hugo.gjant.data.api.event.client;

import com.gjdev.hugo.gjant.data.api.event.base.ErrorRetrieve;
import com.gjdev.hugo.gjant.data.api.model.ApiError;

/**
 * Created by Hugo on 04/01/2017.
 */

public class ErrorClientRetrieve extends ErrorRetrieve {
    public ErrorClientRetrieve(ApiError apiError) {
        super(apiError);
    }
}
