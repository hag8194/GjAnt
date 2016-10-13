package com.gjdev.gjant.data.repository.provider;

import com.gjdev.gjant.data.api.Entity.mapper.MangaEntityMapper;
import com.gjdev.gjant.data.repository.datasource.MangaService;
import com.gjdev.gjant.data.repository.datasource.strategy.MangaServiceFactory;
import com.gjdev.gjant.domain.model.Manga;
import com.gjdev.gjant.domain.repository.MangaRepository;

import java.util.List;

import rx.Observable;

public class MangaDataRepository implements MangaRepository {

  private final MangaServiceFactory mangaServiceFactory;
  private final MangaEntityMapper mangaEntityMapper;

  public MangaDataRepository(MangaServiceFactory mangaServiceFactory,
                             MangaEntityMapper mangaEntityMapper) {
    this.mangaServiceFactory = mangaServiceFactory;
    this.mangaEntityMapper = mangaEntityMapper;
  }

  @Override
  public Observable<List<Manga>> mangas() {
    final MangaService userDataStore = this.mangaServiceFactory.createCloudMangaService();
    return userDataStore.mangaEntityList("mangareader.net")
        .map(userEntities -> this.mangaEntityMapper.transform(userEntities));
  }

}
