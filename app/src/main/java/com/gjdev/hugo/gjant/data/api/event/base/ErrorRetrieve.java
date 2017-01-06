package com.gjdev.hugo.gjant.data.api.event.base;

import com.gjdev.hugo.gjant.data.model.ApiError;

/**
 * Created by Hugo on 04/01/2017.
 */

public abstract class ErrorRetrieve {
    private ApiError apiError;

    public ErrorRetrieve(ApiError apiError) {
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }
}
