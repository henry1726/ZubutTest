package com.example.zubuttest;

import android.app.Application;
import android.content.Context;

import com.example.zubuttest.sys.di.components.DaggerUtilComponent;
import com.example.zubuttest.sys.di.components.UtilComponent;
import com.example.zubuttest.sys.di.modules.ContextModule;
import com.example.zubuttest.sys.providers.AppExecutors;

public class MainApplication extends Application {

    public static UtilComponent utilComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        utilComponent = DaggerUtilComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .build();

    }

    public static Context getAppContext() {
        return utilComponent.getAppContext();
    }

    public static AppExecutors getAppExecutors() {
        return utilComponent.getAppExecutors();
    }
}
