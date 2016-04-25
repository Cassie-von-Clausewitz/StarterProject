package com.kyleriedemann.starterproject.network;

import com.github.simonpercic.oklog3.OkLogInterceptor;
import com.google.gson.Gson;
import com.kyleriedemann.starterproject.BuildConfig;
import com.kyleriedemann.starterproject.StarterApplication;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Generates instances of Retrofit API implementations, and adding interceptors for debugging and caching
 * Retrofit instances also support rx by default.
 *
 * @author kylealanr
 */
public class ServiceGenerator {
    public static final String BASE_URL = BuildConfig.API_URL;
    private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    public static <T> T createService(Class<T> serviceClass) {
        if (BuildConfig.DEBUG){
            OkLogInterceptor okLogInterceptor = OkLogInterceptor.builder().build();
            httpClient.addInterceptor(okLogInterceptor);
        }

        File cacheDir = new File(StarterApplication.instance().getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        httpClient.cache(cache);

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}