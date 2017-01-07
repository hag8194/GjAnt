package com.gjdev.hugo.gjant.util;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.api.model.ApiError;

/**
 * Created by Hugo on 04/01/2017.
 */

public class Messages {
    @NonNull
    public static String errorMessage(ApiError error) {
        return "Error: " + error.getName() + "\nStatus: " + error.getStatus() + ", " + error.getMessage();
    }

    @NonNull
    public static String failureMessage(Throwable t) {
        return "Error: " + t.getMessage();
    }
}
