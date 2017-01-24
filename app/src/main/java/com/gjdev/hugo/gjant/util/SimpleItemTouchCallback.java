package com.gjdev.hugo.gjant.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.view.impl.adapter.CartListAdapter;

/**
 * Created by Hugo on 24/01/2017.
 */

public class SimpleItemTouchCallback extends ItemTouchHelper.SimpleCallback{
    // we want to cache these and not allocate anything repeatedly in the onChildDraw method
    private Drawable background;
    private Drawable xMark;
    private int xMarkMargin;
    private boolean initiated;

    private Context context;
    private RecyclerView recyclerView;

    public SimpleItemTouchCallback(Context context, RecyclerView recyclerView, int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
        this.context = context;
        this.recyclerView = recyclerView;
    }

    private void init() {
        background = new ColorDrawable(ContextCompat.getColor(context, R.color.colorAccent));
        xMark = ContextCompat.getDrawable(context, R.drawable.ic_delete_white_24dp);
        xMark.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        xMarkMargin = (int) context.getResources().getDimension(R.dimen.ic_clear_margin);
        initiated = true;
    }

    // not important, we don't want drag & drop
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int position = viewHolder.getAdapterPosition();
        CartListAdapter adapter = (CartListAdapter)recyclerView.getAdapter();
        if (adapter.isPendingRemoval(position)) {
            return 0;
        }

        return super.getSwipeDirs(recyclerView, viewHolder);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
        int swipedPosition = viewHolder.getAdapterPosition();
        CartListAdapter adapter = (CartListAdapter)recyclerView.getAdapter();
        adapter.pendingRemoval(swipedPosition);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View itemView = viewHolder.itemView;

        // not sure why, but this method get's called for viewholder that are already swiped away
        if (viewHolder.getAdapterPosition() == -1) {
            // not interested in those
            return;
        }

        if (!initiated) {
            init();
        }

        // draw red background
        background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
        background.draw(c);

        // draw x mark
        int itemHeight = itemView.getBottom() - itemView.getTop();
        int intrinsicWidth = xMark.getIntrinsicWidth();
        int intrinsicHeight = xMark.getIntrinsicWidth();

        int xMarkLeft = itemView.getRight() - xMarkMargin - intrinsicWidth;
        int xMarkRight = itemView.getRight() - xMarkMargin;
        int xMarkTop = itemView.getTop() + (itemHeight - intrinsicHeight)/2;
        int xMarkBottom = xMarkTop + intrinsicHeight;
        xMark.setBounds(xMarkLeft, xMarkTop, xMarkRight, xMarkBottom);

        xMark.draw(c);

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
