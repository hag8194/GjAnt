package com.gjdev.hugo.gjant.data.event;

import android.view.View;

/**
 * Created by Hugo on 05/01/2017.
 */

public class ClickedProductListItem {
    private View itemView;
    private int adapterPosition;

    public ClickedProductListItem(View itemView, int adapterPosition) {
        this.itemView = itemView;
        this.adapterPosition = adapterPosition;
    }

    public View getItemView() {
        return itemView;
    }

    public int getAdapterPosition() {
        return adapterPosition;
    }
}
