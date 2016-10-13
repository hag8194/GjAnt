package com.gjdev.gjant.data.api.interceptor;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;


public class ApiInterceptor {

  private static okhttp3.Interceptor interceptor;

  public static okhttp3.Interceptor getInstance() {

    interceptor = new okhttp3.Interceptor() {
      @Override
      public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request request = original.newBuilder()
            .header("X-Mashape-Key", "CucMVBY49UmshCVniALZPqDIHqI8p1bkNW7jsnMrJXwesy79ii")
            .header("Accept", "text/plain")
            .method(original.method(), original.body())
            .build();

        return chain.proceed(request);
      }
    };

    return interceptor;
  }
}
