package com.gjdev.gjant.data.api;

import com.gjdev.gjant.data.api.Entity.MangaEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ApiService {

  @GET("/{siteid}/")
  Observable<List<MangaEntity>> getMangaList(@Path("siteid") String siteId);

}
