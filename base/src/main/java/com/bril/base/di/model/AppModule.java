package com.bril.base.di.model;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 123456 on 2017/9/6.
 */
@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }
}
