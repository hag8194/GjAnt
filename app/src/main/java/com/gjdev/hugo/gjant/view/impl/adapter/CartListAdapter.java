package com.gjdev.hugo.gjant.view.impl.adapter;

import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.event.ClickedProductListItem;
import com.gjdev.hugo.gjant.data.event.LongClickedProductListItem;
import com.gjdev.hugo.gjant.data.event.RemoveCartItem;
import com.gjdev.hugo.gjant.data.sql.model.SQLProduct;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by Hugo on 07/01/2017.
 */

public class CartListAdapter extends BaseAdapter <CartListAdapter.ViewHolder> {
    private static final int PENDING_REMOVAL_TIMEOUT = 3000; // 3sec
    private List<SQLProduct> mProducts;
    private List<SQLProduct> itemsPendingRemoval;

    private Handler handler = new Handler(); // hanlder for running delayed runnables
    private HashMap<SQLProduct, Runnable> pendingRunnables = new HashMap<>(); // map of items to pending runnables, so we can cancel a removal if need be

    public CartListAdapter(List<SQLProduct> mProducts) {
        this.mProducts = mProducts;
        itemsPendingRemoval = new ArrayList<>();
    }

    static class ViewHolder extends ProductListAdapter.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        @BindView(R.id.product_poster)
        ImageView productPoster;

        @BindView(R.id.product_name)
        TextView productName;

        @BindView(R.id.product_total)
        TextView productTotal;

        @BindView(R.id.product_price)
        TextView productPrice;

        @BindView(R.id.product_quantity)
        TextView productQuantity;

        @BindView(R.id.undo)
        Button undoButton;

        @BindViews({R.id.bs1, R.id.bs2, R.id.und})
        List<TextView> prefixes;

        static final ButterKnife.Setter<View, Integer> VISIBILITY = new ButterKnife.Setter<View, Integer>() {
            @Override public void set(View view, Integer visibility, int index) {
                view.setVisibility(visibility);
            }
        };

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setProductDataVisibility(int visibility){
            ButterKnife.apply(prefixes, VISIBILITY, visibility);
            productPoster.setVisibility(visibility);
            productName.setVisibility(visibility);
            productTotal.setVisibility(visibility);
            productPrice.setVisibility(visibility);
            productQuantity.setVisibility(visibility);
        }

        @Override
        public void onClick(View v) {
            EventBus.getDefault().post(new ClickedProductListItem(v, getAdapterPosition()));
        }

        @Override
        public boolean onLongClick(View v) {
            EventBus.getDefault().post(new LongClickedProductListItem(v, getAdapterPosition()));
            return true;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart_list, parent, false);

        return new CartListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SQLProduct product = mProducts.get(position);
        String poster_url = product.getPoster_url();

        if(itemsPendingRemoval.contains(product)) {
            holder.itemView.setBackgroundResource(R.color.colorAccent);
            holder.itemView.setOnClickListener(null);
            holder.setProductDataVisibility(View.GONE);
            holder.undoButton.setVisibility(View.VISIBLE);
            holder.undoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // user wants to undo the removal, let's cancel the pending task
                    Runnable pendingRemovalRunnable = pendingRunnables.get(product);
                    pendingRunnables.remove(product);
                    if (pendingRemovalRunnable != null) handler.removeCallbacks(pendingRemovalRunnable);
                    itemsPendingRemoval.remove(product);
                    // this will rebind the row in "normal" state
                    notifyItemChanged(mProducts.indexOf(product));

                }
            });
        }
        else {
            // we need to show the "normal" state
            holder.itemView.setBackgroundColor(Color.WHITE);
            holder.itemView.setOnClickListener(holder);
            Picasso.with(holder.itemView.getContext())
                    .load(poster_url)
                    .placeholder(R.drawable.ic_image_black_24dp)
                    .error(R.drawable.ic_broken_image_black_24dp)
                    .into(holder.productPoster);

            holder.productName.setText(product.getName());
            holder.productTotal.setText(String.valueOf((product.getQuantity() * product.getPrice())));
            holder.productPrice.setText(String.valueOf(product.getPrice()));
            holder.productQuantity.setText(String.valueOf(product.getQuantity()));

            holder.undoButton.setVisibility(View.GONE);
            holder.undoButton.setOnClickListener(null);
        }

    }

    public void pendingRemoval(final int position) {
        final SQLProduct product = mProducts.get(position);
        if (!itemsPendingRemoval.contains(product)) {
            itemsPendingRemoval.add(product);
            // this will redraw row in "undo" state
            notifyItemChanged(position);
            // let's create, store and post a runnable to remove the item
            Runnable pendingRemovalRunnable = new Runnable() {
                @Override
                public void run() {
                    remove(mProducts.indexOf(product));
                    EventBus.getDefault().post(new RemoveCartItem(product));
                }
            };
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
            pendingRunnables.put(product, pendingRemovalRunnable);
        }
    }

    private void remove(int position) {
        SQLProduct product = mProducts.get(position);
        if (itemsPendingRemoval.contains(product)) {
            itemsPendingRemoval.remove(product);
        }
        if (mProducts.contains(product)) {
            mProducts.remove(position);
            notifyItemRemoved(position);
        }
    }

    public boolean isPendingRemoval(int position) {
        SQLProduct product = mProducts.get(position);
        return itemsPendingRemoval.contains(product);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}
