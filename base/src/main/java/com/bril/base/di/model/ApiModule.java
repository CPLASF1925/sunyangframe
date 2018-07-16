package com.bril.base.di.model;


import com.bril.base.net.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by 123456 on 2017/9/6.
 */
@Module
public class ApiModule {
    @Singleton
    @Provides
    public ApiService provideApiService(OkHttpClient okHttpClient, Retrofit.Builder retroBuild) {
        return retroBuild.client(okHttpClient).baseUrl(ApiService.Base_URL).build().create(ApiService.class);
    }
}
