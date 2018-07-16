package com.bril.base.di.component;

import android.content.Context;


import com.bril.base.di.AppScope;
import com.bril.base.di.model.AppModule;
import com.bril.base.ui.activity.BaseActivity;
import com.bril.base.ui.activity.BaseTabActivity;
import com.bril.base.ui.fragment.BaseFragment;

import dagger.Component;

/**
 * Created by 123456 on 2017/9/6.
 */
@AppScope
@Component(modules = {AppModule.class}, dependencies = {ApiComponent.class})
public interface AppComponent {
    Context getContext();
    void inject(BaseActivity baseActivity);

    void inject(BaseTabActivity baseTabActivity);

    void inject(BaseFragment baseFragment);

}
