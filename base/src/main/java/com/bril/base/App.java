package com.bril.base;

import android.app.Application;

import com.bril.base.di.component.AppComponent;
import com.bril.base.di.component.DaggerApiComponent;
import com.bril.base.di.component.DaggerAppComponent;
import com.bril.base.di.model.AppModule;

/**
 * App
 */

public class App extends Application {
    private static App sInstance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initCompoent();

    }


    private void initCompoent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiComponent(DaggerApiComponent.create())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }



    public static App getInstance() {
        return sInstance;
    }


}
