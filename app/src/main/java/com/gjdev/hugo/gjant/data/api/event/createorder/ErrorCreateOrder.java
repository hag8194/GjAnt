package com.gjdev.hugo.gjant.data.api.event.createorder;

import com.gjdev.hugo.gjant.data.api.event.base.ErrorRetrieve;
import com.gjdev.hugo.gjant.data.api.model.ApiError;

/**
 * Created by Hugo on 04/01/2017.
 */

public class ErrorCreateOrder extends ErrorRetrieve {
    public ErrorCreateOrder(ApiError apiError) {
        super(apiError);
    }
}
