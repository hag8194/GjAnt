package com.gjdev.hugo.gjant.data.event;

import android.view.View;

import java.io.Serializable;

/**
 * Created by Hugo on 14/01/2017.
 */

public class ClickedClientWalletListItem extends ClickedListItem implements Serializable{
    public ClickedClientWalletListItem(View clickedView, int adapterPosition) {
        super(clickedView, adapterPosition);
    }
}
