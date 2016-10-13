package com.gjdev.gjant.domain.repository;

import com.gjdev.gjant.domain.model.Manga;

import java.util.List;

import rx.Observable;

public interface MangaRepository {

  Observable<List<Manga>> mangas();
}
