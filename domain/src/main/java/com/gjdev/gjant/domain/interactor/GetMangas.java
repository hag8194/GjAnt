package com.gjdev.gjant.domain.interactor;

import com.gjdev.gjant.domain.executor.PostExecutionThread;
import com.gjdev.gjant.domain.executor.ThreadExecutor;
import com.gjdev.gjant.domain.repository.MangaRepository;
;

import rx.Observable;


public class GetMangas extends BaseInteractor {

  private final MangaRepository mangaRepository;

  public GetMangas(MangaRepository userRepository, ThreadExecutor threadExecutor,
                   PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.mangaRepository = userRepository;
  }

  @Override
  public Observable buildInteractorObservable() {
    return this.mangaRepository.mangas();
  }
}
