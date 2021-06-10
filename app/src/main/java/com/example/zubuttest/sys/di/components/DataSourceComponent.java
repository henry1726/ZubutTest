package com.example.zubuttest.sys.di.components;

import com.example.zubuttest.domain.AdressRepository;
import com.example.zubuttest.domain.MapsAcitivityRepository;
import com.example.zubuttest.sys.di.modules.DataSourceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = DataSourceModule.class)
public interface DataSourceComponent {

    void inject(AdressRepository repository);

    void inject(MapsAcitivityRepository repository);
}
