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

    public static class ProductCart {
        public static final String NO_ACTION_MESSAGE = "El producto junto a la cantidad ya se encuentran almacenadas";
        public static final String SUCCESS_MESSAGE = "El producto se ha guardado en el carrito" ;
        public static final String UPDATE_MESSAGE = "La cantidad del producto se ha actualizado";
    }
}
