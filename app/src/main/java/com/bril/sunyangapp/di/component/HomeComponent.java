package com.bril.sunyangapp.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.bril.sunyangapp.di.module.HomeModule;

import com.bril.sunyangapp.mvp.ui.activity.HomeActivity;

@ActivityScope
@Component(modules = HomeModule.class, dependencies = AppComponent.class)
public interface HomeComponent {
    void inject(HomeActivity activity);
}