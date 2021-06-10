package com.example.zubuttest.sys.di.modules;

import com.example.zubuttest.domain.AdressRepository;
import com.example.zubuttest.domain.MapsAcitivityRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
@Module
public class RepositoryModule {

    @Singleton
    @Provides
    AdressRepository provideAdressRepository() {
        return new AdressRepository();
    }

    @Singleton
    @Provides
    MapsAcitivityRepository provideMapsAcitivityRepository() {
        return new MapsAcitivityRepository();
    }
}
