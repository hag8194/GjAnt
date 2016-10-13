package com.gjdev.gjant.data.repository.datasource.strategy;

import android.content.Context;

import com.gjdev.gjant.data.api.ApiAdapter;
import com.gjdev.gjant.data.api.ApiConstants;
import com.gjdev.gjant.data.api.ApiService;
import com.gjdev.gjant.data.repository.datasource.MangaService;
import com.gjdev.gjant.data.repository.datasource.cloud.CloudMangaService;

import retrofit2.Retrofit;

/**
 * Created by Pedro on 25/7/2016.
 */
public class MangaServiceFactory {

    private final Context context;
    private final Object userCache;

    public MangaServiceFactory(Context context, Object userCache) {
        this.context = context.getApplicationContext();
        this.userCache = userCache;
    }

    public MangaService createCloudMangaService() {

        Retrofit apiAdapter = ApiAdapter.getInstance(ApiConstants.BASE_URL);
        ApiService apiService = apiAdapter.create(ApiService.class);

        return new CloudMangaService(apiService, this.userCache);
    }
}
