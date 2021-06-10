package com.example.zubuttest.domain;

import androidx.lifecycle.LiveData;

import com.example.zubuttest.MainApplication;
import com.example.zubuttest.data.datasources.db.AdressDAO;
import com.example.zubuttest.data.entities.AdressEntity;
import com.example.zubuttest.sys.di.components.DaggerDataSourceComponent;
import com.example.zubuttest.sys.di.modules.ContextModule;

import java.util.List;

import javax.inject.Inject;

public class AdressRepository {

    @Inject
    AdressDAO adressDAO;

    public AdressRepository() {

        DaggerDataSourceComponent.builder()
                .contextModule(new ContextModule(MainApplication.getAppContext()))
                .build().inject(this);
    }

    public LiveData<List<AdressEntity>> getAllAdress(){
        return adressDAO.readAllAdress();
    }
}
