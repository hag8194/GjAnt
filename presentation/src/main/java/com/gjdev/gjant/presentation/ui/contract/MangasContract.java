package com.gjdev.gjant.presentation.ui.contract;

import com.gjdev.gjant.domain.model.Manga;
import com.gjdev.gjant.presentation.ui.contract.LoadDataView;

import java.util.Collection;

public class MangasContract {

  public interface View extends LoadDataView {

    void renderMangaList(Collection<Manga> mangas);

    void viewManga(Manga manga);
  }

  public interface UserActionListener {
    void requestMangas();

    void MangaDetail(Manga item);
  }
}
