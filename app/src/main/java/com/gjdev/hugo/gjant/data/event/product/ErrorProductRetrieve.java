package com.gjdev.hugo.gjant.data.event.product;

import com.gjdev.hugo.gjant.data.event.base.ErrorRetrieve;
import com.gjdev.hugo.gjant.data.model.ApiError;

/**
 * Created by Hugo on 04/01/2017.
 */

public class ErrorProductRetrieve extends ErrorRetrieve {
    public ErrorProductRetrieve(ApiError apiError) {
        super(apiError);
    }
}
