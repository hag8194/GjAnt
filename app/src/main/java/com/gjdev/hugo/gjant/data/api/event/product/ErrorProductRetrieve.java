package com.gjdev.hugo.gjant.data.api.event.product;

import com.gjdev.hugo.gjant.data.api.event.base.ErrorRetrieve;
import com.gjdev.hugo.gjant.data.api.model.ApiError;

/**
 * Created by Hugo on 04/01/2017.
 */

public class ErrorProductRetrieve extends ErrorRetrieve {
    public ErrorProductRetrieve(ApiError apiError) {
        super(apiError);
    }
}
