package com.gjdev.hugo.gjant.util;

import com.gjdev.hugo.gjant.data.model.ApiError;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Hugo on 27/12/2016.
 * Project: GjAnt2
 */

public class ApiErrorHandler {

    private Retrofit mApiAdapter;
    private Converter<ResponseBody, ApiError> converter;

    @Inject
    public ApiErrorHandler(Retrofit apiAdapter) {
        mApiAdapter = apiAdapter;
        converter = mApiAdapter.responseBodyConverter(ApiError.class, new Annotation[0]);
    }

    public ApiError parseError(Response<?> response){
        ApiError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ApiError();
        }

        return error;
    }
}