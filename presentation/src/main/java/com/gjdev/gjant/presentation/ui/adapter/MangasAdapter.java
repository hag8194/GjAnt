package com.gjdev.gjant.presentation.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gjdev.hugo.gjant.R;
import com.gjdev.gjant.domain.model.Manga;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MangasAdapter extends RecyclerView.Adapter<MangasAdapter.MangaHolder> {

  private List<Manga> mMangas;
  private Context mContext;

  public MangasAdapter() {
    mMangas = new ArrayList<>();
  }

  public MangasAdapter(List<Manga> mangas) {
    mMangas = mangas;
  }

  @Override
  public MangaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (mContext == null)
      mContext = parent.getContext();

    View root = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_manga, parent, false);

    return new MangaHolder(root);
  }

  @Override
  public void onBindViewHolder(MangaHolder holder, int position) {
    Manga manga = mMangas.get(position);
    holder.nameLabel.setText(manga.getName());
  }

  @Override
  public int getItemCount() {
    return mMangas.size();
  }

  public void setItems(List<Manga> items) {
    mMangas = items;
    notifyDataSetChanged();
  }

  public void clear() {
    mMangas.clear();
    notifyDataSetChanged();
  }

  public class MangaHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.label_name)
    TextView nameLabel;

    public MangaHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

  }
}
