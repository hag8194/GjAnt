package com.gjdev.gjant.data.repository.datasource;

import com.gjdev.gjant.data.api.Entity.MangaEntity;

import java.util.List;

import rx.Observable;


public interface MangaService {

  Observable<List<MangaEntity>> mangaEntityList(String siteId);
}
