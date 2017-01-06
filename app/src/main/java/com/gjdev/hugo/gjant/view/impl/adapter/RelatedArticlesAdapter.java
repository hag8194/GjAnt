package com.gjdev.hugo.gjant.view.impl.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.model.Children;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hugo on 05/01/2017.
 */

public class RelatedArticlesAdapter extends BaseAdapter<RelatedArticlesAdapter.ViewHolder> {
    private List<Children> childArticleList;

    public RelatedArticlesAdapter(List<Children> childArticleList) {
        this.childArticleList = childArticleList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.children_product_image)
        ImageView childrenProductImage;

        @BindView(R.id.children_product_name)
        TextView childrenProductName;

        @BindView(R.id.children_product_price)
        TextView childrenProductPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.related_articles_item, parent, false);

        return new RelatedArticlesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Children children = childArticleList.get(position);

        Picasso.with(holder.itemView.getContext())
                .load(children.get_links().getPoster().getHref())
                .into(holder.childrenProductImage);

        holder.childrenProductName.setText(children.getName());
        holder.childrenProductPrice.setText(String.valueOf(children.getPrice()));
    }

    @Override
    public int getItemCount() {
        return childArticleList.size();
    }
}
