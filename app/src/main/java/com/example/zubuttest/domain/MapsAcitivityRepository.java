package com.example.zubuttest.domain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.zubuttest.MainApplication;
import com.example.zubuttest.data.datasources.db.AdressDAO;
import com.example.zubuttest.data.entities.AdressEntity;
import com.example.zubuttest.sys.di.components.DaggerDataSourceComponent;
import com.example.zubuttest.sys.di.modules.ContextModule;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class MapsAcitivityRepository {

    @Inject
    AdressDAO adressDAO;

    public MapsAcitivityRepository() {

        DaggerDataSourceComponent.builder()
                .contextModule(new ContextModule(MainApplication.getAppContext()))
                .build().inject(this);

    }

    public LiveData<List<String>> readAllCoordinates(){
        return  adressDAO.readAllCoordinates();
    }

    public void insertAdress(String nombre, String direccion, String coordenadas){
        try {
            MainApplication.getAppExecutors().diskIO().submit(() ->
                    adressDAO.insertAdress(new AdressEntity(nombre, direccion, coordenadas))
            ).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
