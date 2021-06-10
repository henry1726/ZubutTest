package com.example.zubuttest.sys.di.modules;

import com.example.zubuttest.data.datasources.db.AdressDAO;
import com.example.zubuttest.data.datasources.db.declaration.AppDataBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = DatabaseModule.class)
public class DataSourceModule {

    @Singleton
    @Provides
    AdressDAO provideAdressDAO(AppDataBase database) {
        return database.getAdressDAO();
    }
}
