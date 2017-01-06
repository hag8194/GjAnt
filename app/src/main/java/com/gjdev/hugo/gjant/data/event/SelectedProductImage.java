package com.gjdev.hugo.gjant.data.event;

import android.graphics.drawable.Drawable;

/**
 * Created by Hugo on 05/01/2017.
 */

public class SelectedProductImage {
    private Drawable imageDrawable;

    public SelectedProductImage(Drawable imageDrawable) {
        this.imageDrawable = imageDrawable;
    }

    public Drawable getImageDrawable() {
        return imageDrawable;
    }
}
