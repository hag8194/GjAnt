package com.gjdev.hugo.gjant.view.impl.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Hugo on 01/01/2017.
 * Project: GjAnt2
 */

public abstract class BaseAdapter <V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V>{
    protected static BaseAdapter.ItemClickListener mItemClickListener;

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View v, int position);
    }
}