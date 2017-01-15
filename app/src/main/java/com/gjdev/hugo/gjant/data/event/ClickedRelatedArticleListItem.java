package com.gjdev.hugo.gjant.data.event;

import android.view.View;

/**
 * Created by Hugo on 05/01/2017.
 */

public class ClickedRelatedArticleListItem extends ClickedListItem {
    public ClickedRelatedArticleListItem(View clickedView, int adapterPosition) {
        super(clickedView, adapterPosition);
    }
}
