package com.gjdev.hugo.gjant.view.impl.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.model.ClientWallet;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hugo on 12/01/2017.
 */

public class ClientListAdapter extends BaseAdapter<ClientListAdapter.ViewHolder> {
    private List<ClientWallet> mClientWalletList;

    public ClientListAdapter(List<ClientWallet> mClientWalletList) {
        this.mClientWalletList = mClientWalletList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.client_avatar)
        ImageButton client_avatar;

        @BindView(R.id.client_fullname)
        TextView client_fullname;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_client_list, parent, false);

        return new ClientListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ClientWallet clientWallet = mClientWalletList.get(position);

        holder.client_fullname.setText(clientWallet.getClient().getFullname());
    }

    @Override
    public int getItemCount() {
        return mClientWalletList.size();
    }
}
