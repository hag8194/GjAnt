package com.gjdev.hugo.gjant.data.event;

import android.view.View;

/**
 * Created by Hugo on 08/01/2017.
 */

public class LongClickedProductListItem {
    private View itemView;
    private int adapterPosition;

    public LongClickedProductListItem(View itemView, int adapterPosition) {
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
