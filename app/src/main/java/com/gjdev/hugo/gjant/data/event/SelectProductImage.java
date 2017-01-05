package com.gjdev.hugo.gjant.data.event;

import android.graphics.drawable.Drawable;

/**
 * Created by Hugo on 05/01/2017.
 */

public class SelectProductImage {
    private Drawable imageDrawable;

    public SelectProductImage(Drawable imageDrawable) {
        this.imageDrawable = imageDrawable;
    }

    public Drawable getImageDrawable() {
        return imageDrawable;
    }
}
