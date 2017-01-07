package com.gjdev.hugo.gjant.data.api.event.products;

import com.gjdev.hugo.gjant.data.api.event.base.ErrorRetrieve;
import com.gjdev.hugo.gjant.data.api.model.ApiError;

/**
 * Created by Hugo on 04/01/2017.
 */

public class ErrorProductsRetrieve extends ErrorRetrieve {
    public ErrorProductsRetrieve(ApiError apiError) {
        super(apiError);
    }
}
