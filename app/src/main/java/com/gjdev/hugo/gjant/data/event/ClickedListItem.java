package com.gjdev.hugo.gjant.data.event;

import android.view.View;

import java.io.Serializable;

/**
 * Created by Hugo on 14/01/2017.
 */

public class ClickedListItem implements Serializable {
    private View clickedView;
    private int adapterPosition;

    public ClickedListItem(View clickedView, int adapterPosition) {
        this.clickedView = clickedView;
        this.adapterPosition = adapterPosition;
    }

    public View getClickedView() {
        return clickedView;
    }

    public int getAdapterPosition() {
        return adapterPosition;
    }
}
