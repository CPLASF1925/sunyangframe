package com.bril.base.di.component;


import com.bril.base.di.model.ApiModule;
import com.bril.base.di.model.HttpModel;
import com.bril.base.net.ApiService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 123456 on 2017/9/14.
 */
@Singleton
@Component(modules = {ApiModule.class, HttpModel.class})
public interface ApiComponent {

    ApiService getApi();

    //void inject(Object obj);
}
