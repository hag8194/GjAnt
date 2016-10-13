package com.gjdev.gjant.data.api.Entity.mapper;

import com.gjdev.gjant.data.api.Entity.MangaEntity;
import com.gjdev.gjant.domain.model.Manga;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MangaEntityMapper {

  public Manga transform(MangaEntity mangaEntity) {
    Manga manga = null;
    if (mangaEntity != null) {
      manga = new Manga();
      manga.setName(mangaEntity.getName());
    }

    return manga;
  }

  public List<Manga> transform(Collection<MangaEntity> mangaEntityCollection) {
    List<Manga> userList = new ArrayList<>(20);
    Manga manga;
    for (MangaEntity mangaEntity : mangaEntityCollection) {
      manga = transform(mangaEntity);
      if (manga != null) {
        userList.add(manga);
      }
    }

    return userList;
  }
}
