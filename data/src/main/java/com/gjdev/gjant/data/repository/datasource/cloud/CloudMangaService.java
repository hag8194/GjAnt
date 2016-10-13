package com.gjdev.gjant.data.repository.datasource.cloud;

import com.gjdev.gjant.data.api.ApiService;
import com.gjdev.gjant.data.api.Entity.MangaEntity;
import com.gjdev.gjant.data.repository.datasource.MangaService;

import java.util.List;

import rx.Observable;

public class CloudMangaService implements MangaService {

  private final ApiService apiService;
  private final Object userCache;

  public CloudMangaService(ApiService apiService, Object userCache) {
    this.apiService = apiService;
    this.userCache = userCache;
  }

  @Override
  public Observable<List<MangaEntity>> mangaEntityList(String siteId) {
    return this.apiService.getMangaList(siteId);
  }
}