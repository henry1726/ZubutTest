package com.example.zubuttest.sys.di.components;

import android.content.Context;

import com.example.zubuttest.sys.di.modules.UtilModule;
import com.example.zubuttest.sys.providers.AppExecutors;
import com.example.zubuttest.sys.providers.ResourceProvider;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = UtilModule.class)
public interface UtilComponent {

    AppExecutors getAppExecutors();

    Context getAppContext();

    ResourceProvider getResourceProvider();
}
