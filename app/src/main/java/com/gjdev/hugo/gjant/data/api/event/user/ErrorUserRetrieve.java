package com.gjdev.hugo.gjant.data.api.event.user;

import com.gjdev.hugo.gjant.data.api.event.base.ErrorRetrieve;
import com.gjdev.hugo.gjant.data.model.ApiError;

/**
 * Created by Hugo on 04/01/2017.
 */

public class ErrorUserRetrieve extends ErrorRetrieve {
    public ErrorUserRetrieve(ApiError apiError) {
        super(apiError);
    }
}
