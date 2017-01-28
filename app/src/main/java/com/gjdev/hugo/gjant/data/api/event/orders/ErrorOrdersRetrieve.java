package com.gjdev.hugo.gjant.data.api.event.orders;

import com.gjdev.hugo.gjant.data.api.event.base.ErrorRetrieve;
import com.gjdev.hugo.gjant.data.api.model.ApiError;

/**
 * Created by Hugo on 04/01/2017.
 */

public class ErrorOrdersRetrieve extends ErrorRetrieve {
    public ErrorOrdersRetrieve(ApiError apiError) {
        super(apiError);
    }
}
