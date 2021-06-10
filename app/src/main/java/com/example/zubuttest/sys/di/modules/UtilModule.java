package com.example.zubuttest.sys.di.modules;

import android.content.Context;

import com.example.zubuttest.sys.providers.AppExecutors;
import com.example.zubuttest.sys.providers.ResourceProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class UtilModule {

    @Provides
    @Singleton
    public AppExecutors provideAppExecutors() {
        return new AppExecutors();
    }

    @Provides
    @Singleton
    public ResourceProvider provideResourceProvider(Context context) {
        return new ResourceProvider(context);
    }
}
